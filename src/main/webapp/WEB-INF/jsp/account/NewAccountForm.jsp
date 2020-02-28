<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	<form action="newAccount" method="post">
	<h3>User Information</h3>

	<table>
		<tr>
			<td>User ID:</td>
			<td><input type="text" name="username" id="username" onblur="checkUsername();"></td>
			<td id="isExistInfo"></td>
		</tr>
		<tr>
			<td>New password:</td>
			<td><input type="text" name="password"></td>
		</tr>
		<tr>
			<td>Repeat password:</td>
			<td><input type="text" name="repeatedPassword"></td>
		</tr>
	</table>

	<%@ include file="IncludeAccountFields.jsp"%>

	<input type="submit" name="newAccount" value="Save Account Information">
	</form></div>
<script type="text/javascript">var xhr;
function checkUsername(){
	var username=document.getElementById('username').value;
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=process;
	xhr.open("GET","usernameIsExist?username="+username,true);
	xhr.send(null);
}
function process() {
	if(xhr.readyState==4){
		if(xhr.status==200){
			var responseInfo=xhr.responseText;
			var msg=document.getElementById('isExistInfo');
			if(responseInfo=='Exist'){
				msg.classList.add('errormsg');
				msg.innerText='Not Available';
			}else if(responseInfo=='Not Exist'){
				msg.classList.add('okmsg');
				msg.innerText='Available';
			}
		}
	}

}</script>
<%@ include file="../common/IncludeBottom.jsp"%>