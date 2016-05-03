jQuery.fn.extend({
    getPrice: function() {
        return parseFloat(this.text().replace(/{$}/g, ""))
    },
    setPrice: function(price) {
        this.text(price);
        this.append(" $")
    }
});

//COUNT UP CART ITEM QUANTITY
$('.cart_quantity_up').click(function () {
    var link = $(this);
    var itemCount = link.parent().find('#itemCount');
    var count = parseInt(itemCount.text());
    var id = link.attr("itemId");

    if (count < 5) {
        $.ajax({
            type: "PUT",
            url: "/cart/countUp/{id}".supplant({id: id}),
            success: function (result) {
                count = count + 1;
                itemCount.text(count);
                calculatePrice(id, count)
            }
        })
    } else {
        alert("You can't order more than 4 items of one product!");
    }
});

//COUNT DOWN CART ITEM QUANTITY
$('.cart_quantity_down').click(function () {
    var link = $(this);
    var itemCount = link.parent().find('#itemCount');
    var count = parseInt(itemCount.text());
    var id = link.attr("itemId");
    if (count > 1) {
        $.ajax({
            type: "PUT",
            url: "/cart/countDown/{id}".supplant({id: id}),
            success: function (result) {
                count = count - 1;
                itemCount.text(count);
                calculatePrice(id, count)
            }
        })
    }
});

function calculatePrice(id, count) {
    var itemRow = $('tr[itemId="'+id+'"]');
    var itemPrice = itemRow.find('.cart_price > p').getPrice();
    var orderedItemsSumContainer = itemRow.find('.cart_total_price');
    var orderedItemsSum = orderedItemsSumContainer.getPrice();
    var newOrderedItemsSum = itemPrice * count;
    orderedItemsSumContainer.setPrice(newOrderedItemsSum);
    var cartSumContainer = $('#cartSum');
    var totalPrice = cartSumContainer.getPrice();
    cartSumContainer.setPrice(totalPrice - orderedItemsSum + newOrderedItemsSum)
}

//ADD TO CART
$('[name=addToCart]').click(function () {
    var btn = $(this);
    var id = btn.attr("itemId");
    $.ajax({
        type: "POST",
        url: "/cart/add/{id}".supplant({id: id}),
        success: function (result) {
            alert("New product has been added to your cart!!")
        }
    })
});

//CLEAR CART
$('#clear').click(function () {
    var cartItems = $('#cartItems tbody');
    $.ajax({
        type: "DELETE",
        url: "/cart/clear",
        success: function (result) {
            cartItems.remove();
        }
    })
});

String.prototype.supplant = function (o) {
    return this.replace(/{([^{}]*)}/g,
        function (a, b) {
            var r = o[b];
            return typeof r === 'string' || typeof r === 'number' ? r : a;
        }
    );
};