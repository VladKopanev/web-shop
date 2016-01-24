<%@ tag body-content="empty" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${empty shopItems}">
        <h2 class="title text-center">Items not found</h2>
    </c:when>
    <c:otherwise>
    <c:forEach items="${shopItems}" var="toy">
        <div class="col-sm-4">
            <div class="product-image-wrapper">
                <div class="single-products">
                    <div class="productinfo text-center">
                        <img src="${toy.image}" alt="" width="268" height="249"/>
                        <h2>$ ${toy.price}</h2>
                        <p>${toy.name}</p>
                        <a href="/cart.do?action=add&id=${toy.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart
                        </a>
                    </div>
                    <div class="product-overlay">
                        <div class="overlay-content">
                             <h2>$ ${toy.price}</h2>
                             <p>${toy.name}</p>
                             <p>by ${toy.brand}</p>
                             <p>${toy.weight} kg</p>
                             <c:forEach items="${toy.options}" var="opt">
                                 <p>${opt.group} ${opt.name}</p>
                             </c:forEach>
                            <a href="/cart.do?action=add&id=${toy.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart
                            </a>
                        </div>
                    </div>
                </div>
                <div class="choose">
                    <ul class="nav nav-pills nav-justified">
                        <li>
                            <a href="#"><i class="fa fa-plus-square"></i>Add to wishlist
                            </a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-plus-square"></i>Add to compare
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </c:forEach>
    </c:otherwise>
</c:choose>

