<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>
<div id="Catalog">

<div id="Cart">

<h2>Shopping Cart</h2>
<form action="updateCartQuantities" method="post">
	<table id="listOfCart">
		<tr>
			<th><b>Item ID</b></th>
			<th><b>Product ID</b></th>
			<th><b>Description</b></th>
			<th><b>In Stock?</b></th>
			<th><b>Quantity</b></th>
			<th><b>List Price</b></th>
			<th><b>Total Cost</b></th>
			<th>&nbsp;</th>
		</tr>

		<c:if test="${sessionScope.cart.numberOfItems == 0}">
			<tr>
				<td colspan="8"><b>Your cart is empty.</b></td>
			</tr>
		</c:if>


		<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
			<tr>
				<td>
					<a href="viewItem?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
				</td>
				<td>${cartItem.item.product.productId}</td>
				<td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
				${cartItem.item.attribute3} ${cartItem.item.attribute4}
				${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
				<td>${cartItem.inStock}</td>
				<td>
					<input type="text" name="${cartItem.item.itemId}"  value="${cartItem.quantity}" onkeyup="updateQuantity()"/>
				</td>
				<td><fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00" />
				</td>
				<td><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" />
				</td>
				<td>
					<a class="Button" href="removeItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" id="totalPrice">
				Sub Total:$ ${sessionScope.cart.getSubTotal()}
			</td>
			<td><input type="submit" name="updateCartQuantities" value="Update Cart" /></td>

			<td>&nbsp;</td>
		</tr>
	</table>

</form><c:if test="${sessionScope.cart.numberOfItems > 0}">
	<a href="newOrderForm" class="Button">Proceed to Checkout</a>
</c:if></div>


<div id="Separator">&nbsp;</div>
</div>
<script>var xhr;
function updateQuantity(){
	var total=0;
	var table=document.getElementById("listOfCart");
	for(var i=1;i<table.rows.length-1;i++){
		var price=table.rows[i].cells[5].innerText;
		var qty=document.getElementsByTagName("input").item(i+1).value;
		var prices=price.split("$");
		table.rows[i].cells[6].innerText="$"+prices[1]*qty;
		total+=prices[1]*qty;
	}
	document.getElementById("totalPrice").innerText="Sub Total:$"+total;
	xhr=new XMLHttpRequest();
	xhr.open("GET","updateByjs?itemId="+qty,true);
	xhr.send(null);
}
</script>
<%@ include file="../common/IncludeBottom.jsp"%>