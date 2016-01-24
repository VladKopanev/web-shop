<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="/">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
			<c:choose>
                <c:when test="${empty userCart.items}">
                    <h2 class="title text-center">You have zero items in your cart</h2>
                    <h2 class="title text-center"><a href=/shop.do>go to shop</a></h2>
                </c:when>
                <c:otherwise>
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td><a href="/cart.do?action=clear">Clear</a></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${userCart.items}" var="item">
					    <c:set var="itemCount" scope="request" value="${userCart.getCountOfItems(item)}"/>
						<tr>
							<td class="cart_product">
								<a href=""><img src="${item.image}" alt="" width="268" height="249"></a>
							</td>
							<td class="cart_description">
								<h4><a href="">${item.name}</a></h4>
								<p>ID: ${item.id}</p>
							</td>
							<td class="cart_price">
								<p>$ ${item.price}</p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_up" href="/cart.do?action=countUp&id=${item.id}"> + </a>
									<p class="cart_quantity_input" >${itemCount}</p>
									<a class="cart_quantity_down" href="/cart.do?action=countDown&id=${item.id}"> - </a>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price">$ ${itemCount * item.price}</p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" href="/cart.do?action=delete&id=${item.id}"><i class="fa fa-times"></i></a>
							</td>
						</tr>
					</c:forEach>
					    <tr>
					        <td> </td>
					        <td> </td>
					        <td> </td>
					        <td> </td>
					        <td class="cart_total">
					        <p class="cart_total_price">$ ${userCart.getSumOfItems()}</p>
					        </td>
					        <td><form method="get" action="/checkoutPage.do">
					                <input type="hidden" name="stage" value="1"/>
					                <fieldset title="You must be logged in">
                                        <button type="submit">Checkout</button>
                                    </fieldset>
                                </form></td>
			            </tr>
					</tbody>
				</table>
               </c:otherwise>
            </c:choose>
			</div>
		</div>
	</section> <!--/#cart_items-->