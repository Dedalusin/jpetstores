<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="confirmOrder" method="post">
	<table>
		<tr>
			<th colspan=2>Payment Details</th>
		</tr>
		<tr>
			<td>Card Type:</td>
			<td>
				<select name="order.cardType">
				<option value="Visa" selected="selected">Visa</option><option value="MasterCard">MasterCard</option><option value="American Express">American Express</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Card Number:</td>
			<td>
				<input type="text" name="creditCard">* Use a fake number!
			</td>
		</tr>
		<tr>
			<td>Expiry Date (MM/YYYY):</td>
			<td><input type="text" name="expiryDate"></td>
		</tr>
		<tr>
			<th colspan=2>Billing Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td><input type="text" name="billToFirstName"></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input name="billToLastName" type="text"></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" name="billAddress1" size="40"></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" name="billAddress2" size="40"></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input name="billCity" type="text"></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input name="billState" type="text" size="4"></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input name="billZip" type="text" size="10"></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input name="billCountry" type="text" size="15"></td>
		</tr>

		<tr>
			<td colspan=2><input name="shippingAddressRequired" type="checkbox"  />
			Ship to different address...</td>
		</tr>

	</table>
		<input type="submit" name="newOrder" value="Continue">

</form></div>

<%@ include file="../common/IncludeBottom.jsp"%>