
//COUNT UP CART ITEM QUANTITY
$('.cart_quantity_up').click(function() {
    var link = $(this);
    var itemCount =  link.parent().find('#itemCount');
    var id = link.attr("itemId");
    $.ajax({
        type: "PUT",
        url: "/cart/countUp/{id}".supplant({id: id}),
        success: function (result) {
            var count = parseInt(itemCount.text()) + 1;
            itemCount.text(count);
        }
    })
});

//COUNT DOWN CART ITEM QUANTITY
$('.cart_quantity_down').click(function() {
    var link = $(this);
    var itemCount =  link.parent().find('#itemCount');
    var id = link.attr("itemId");
    $.ajax({
        type: "PUT",
        url: "/cart/countDown/{id}".supplant({id: id}),
        success: function (result) {
            var count = parseInt(itemCount.text()) - 1;
            itemCount.text(count);
        }
    })
});

//ADD TO CART
$('[name=addToCart]').click(function() {
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
$('#clear').click(function() {
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