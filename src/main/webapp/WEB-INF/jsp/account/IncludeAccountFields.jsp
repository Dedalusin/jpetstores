<h3>Account Information</h3>

<table>
	<tr>
		<td>First name:</td>
		<td><input type="text" name="firstName" value="${sessionScope.account.firstName}"></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text" name="lastName" value="${sessionScope.account.lastName}"></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td> <input type="text" name="email" value="${sessionScope.account.email}" size="40"> </td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text" name="phone" value="${sessionScope.account.phone}"></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text" name="address1" value="${sessionScope.account.address1}" size="40"></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text" name="address2" value="${sessionScope.account.address2}" size="40"></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text" name="city" value="${sessionScope.account.city}"></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" name="state" value="${sessionScope.account.state}" size="4"></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text" name="zip" value="${sessionScope.account.zip}" size="10"></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text" name="country" size="15" value="${sessionScope.account.country}" ></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="account.languagePreference">
				<option value="english" selected="selected">english</option><option value="japanese">japanese</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td><select name="account.favouriteCategoryId">
			<option value="FISH">FISH</option><option value="DOGS" selected="selected">DOGS</option><option value="REPTILES">REPTILES</option><option value="CATS">CATS</option><option value="BIRDS">BIRDS</option>
		</select></td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input name="account.listOption" checked="checked" type="checkbox" value="true" /></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input name="account.bannerOption" checked="checked" type="checkbox" value="true" /></td>
	</tr>

</table>
