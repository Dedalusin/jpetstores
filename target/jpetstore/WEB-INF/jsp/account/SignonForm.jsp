<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
	${sessionScope.message}
	<form method="post" action="signon">
		<p>Please enter your username and password.</p>
		<p>Username:<input type="text" name="username" value="j2ee"><br/>
			Password:<input type="password" name="password" value="j2ee"></p>
		<div>
			<table border="0" cellspacing="5" cellpadding="5" >
				<tr>
					<td> <div id="checkCode" class="code" name="verification" onclick="createCode(4)" style="font-family:Arial;
  font-style:italic;
  color:blue;
  font-size:30px;
  border:0;
  padding:2px 3px;
  letter-spacing:3px;
  font-weight:bolder;
  float:left;
  cursor:pointer;
  width:150px;
  height:50px;
  line-height:60px;
  text-align:center;
  vertical-align:middle;
  background-color:#D8B7E3;"></div></td>
					<td> <span onclick="createCode(4)" style="text-decoration:none;
  font-size:12px;
  color:#288bc4;
  padding-left:10px;">click to refresh the code</span></td>
				</tr>
				<tr>
					<td>verification code:</td>
					<td><input type="text" id="inputCode" name="checkcode" style="float:left;" /></td>
				</tr>
				<tr>
					<td><input type="hidden" name="content" id="content"/></td>
					<td><input type="button" onclick="validateCode()"  value="confirm" /></td>
				</tr>
			</table>
		</div>
		<input type="submit" value="Login" onclick="onSubmit()"/>
	</form>
	Need a user name and password?
	<a href="registerAccount">Register Now!</a>
</div>
<script>
	//页面加载时，生成随机验证码
	window.onload=function(){
		createCode(4);
	}

	//生成验证码的方法
	function createCode(length) {
		var code = "";
		var codeLength = parseInt(length); //验证码的长度
		var checkCode = document.getElementById("checkCode");
		////所有候选组成验证码的字符，当然也可以用中文的
		var codeChars = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
		//循环组成验证码的字符串
		for (var i = 0; i < codeLength; i++)
		{
			//获取随机验证码下标
			var charNum = Math.floor(Math.random() * 62);
			//组合成指定字符验证码
			code += codeChars[charNum];
		}
		if (checkCode)
		{
			//为验证码区域添加样式名
			checkCode.className = "code";
			//将生成验证码赋值到显示区
			checkCode.innerHTML = code;
		}
	}

	//检查验证码是否正确
	function validateCode()
	{
		//获取显示区生成的验证码
		var checkCode = document.getElementById("checkCode").innerHTML;
		//获取输入的验证码
		var inputCode = document.getElementById("inputCode").value;
		console.log(checkCode);
		console.log(inputCode);
		if (inputCode.length <= 0)
		{
			alert("please input the checkcode!");
		}
		else if (inputCode.toUpperCase() != checkCode.toUpperCase())
		{
			alert("ni shi shabi");
			createCode(4);
		}
		else
		{
			alert("ni zhen chong ming");
		}
	}
	function onSubmit() {

		var str=document.getElementById("checkCode").innerText;
		document.getElementById("content").value=str;
	}
</script>
<%@ include file="../common/IncludeBottom.jsp"%>

