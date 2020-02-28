<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
	media="screen" />

<meta name="generator"
	content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
<title>MyPetStore</title>
<meta content="text/html; charset=windows-1252"
	http-equiv="Content-Type" />
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="Pragma" content="no-cache" />
</head>

<body>

	<div id="Header">

		<div id="Logo">
			<div id="LogoContent">
				<a href="main"><img src="images/logo-topbar.gif" /></a>
			</div>
		</div>
		<span id="trial"></span>
		<div id="Menu">
			<div id="MenuContent">
				<a href="viewCart"><img align="middle" name="img_cart"
					src="images/cart.gif" /></a>
				<img align="middle"
					src="images/separator.gif" />
				<c:if test="${sessionScope.account == null}">
				<a href="signonForm">Sign In</a>
				</c:if>
				 <c:if test="${sessionScope.account != null}">
					<a href="signoff">Sign Out</a>
					 <a href="gotomyAccount">My Account</a>
				</c:if>

				<img align="middle" src="images/separator.gif" />
				<a href="../help.html">?</a>
			</div>
		</div>
		<div id="Search">
			<div id="SearchContent">
				<form action="search" method="post">
				<input type="text" name="keyword" size="14" id="name" class="inputtable"onkeyup="findPet()" onblur="doblur()"/> <input type="submit" name="searchProducts" value="Search" />
					<div id="context1" style="background-color:white; border: 1px solid red;width:120px;position: absolute;top: 60px;left:1103px;display:none" >
					</div>
			</form>
			</div>
		</div>

		<div id="QuickLinks">
			<a href="viewCategory?categoryId=FISH"><img
				src="images/sm_fish.gif" /></a> <img src="images/separator.gif" />
			<a href="viewCategory?categoryId=DOGS"><img
				src="images/sm_dogs.gif" /></a> <img src="images/separator.gif" />
			<a href="viewCategory?categoryId=REPTILES"><img
				src="images/sm_reptiles.gif" /></a> <img
				src="images/separator.gif" /> <a href="viewCategory?categoryId=CATS"><img
				src="images/sm_cats.gif" /></a> <img src="images/separator.gif" />
			<a href="viewCategory?categoryId=BIRDS"><img
				src="images/sm_birds.gif" /></a>
		</div>

	</div>
<script>var xhr;
		function findPet(){
			var name=document.getElementById('name').value;
			xhr=new XMLHttpRequest();
			xhr.onreadystatechange=process;
			xhr.open("GET","findPet?name="+name,true);
			xhr.send(null);}

			function doblur(){
				document.getElementById('context1').style.display="none";
			}
			function process(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					var res=xhr.responseText;
					var res_split=res.split(",");
					var html="";
					for(var i=0;i<res_split.length&&i<5;i++){
							html+="<div style='line-height: 17px;font-size: 17px;'>"+res_split[i]+"</div>";
					}
					document.getElementById('context1').innerHTML=html;
					document.getElementById('context1').style.display=(document.getElementById('name').value!=="")?"block":"none";
				}
				}
}
</script>
	<div id="Content">