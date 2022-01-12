<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AppleSearch</title>
<style type="text/css">
#padding{
	padding: 0px 0px 15px 15px;
}
a {
	color: #0B173B;
	font-size: 30px;
	text-decoration: none;
}
a:hover{
text-decoration:underline;
}
.border-style {
	border-radius: 90px/90px;
}
.input{
	cursor:pointer;
	background:#F8F8FF;
	border:1 none;
	border-color: #DCDCDC;
	border-radius: 10px;
	color: #696969;
  font-size: medium;
}

.input:focus {
    border-color:#000000;
}

.text:focus{
	background:#F8F8FF;
}
</style>
</head>
<body>
<body style='background-color: #FFFFFF'>
<form action='${requestUri}' method='get'>

	<div style='vertical-align: middle;'>
		<a href="http://localhost:8080/Web/TestProject2"><img src="https://www.applesfromny.com/wp-content/uploads/2020/06/SnapdragonNEW.png" style = 'position:relative;width:153px;height:120px;vertical-align: middle;'>
		</a>
		<input type='text' class="text" id="padding"  style='font-size:120%;position:relative;width:500px;height:50px;vertical-align: middle;' name='keyword' placeholder='請輸入關鍵字'
onfocus="placeholder= '' " onblur="placeholder='請輸入關鍵字'" />
			<input type = "submit" value="搜尋" class="input" style ='position:relative;height:40px;width:130;vertical-align: middle;'>
	</div>

	<div style='position: absolute;margin-top:40px;margin-left:50px'>
		<%
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
			String s=orderList[i][1];
			s=s.substring(7);
			
		%>
		
		<a href='<%="https:/"+s%>'><%=orderList[i][0]%> </a> <br><div style='width:700px;word-wrap: break-word'><%=orderList[i][2]%></div><br>
		
		<br>
		<%
}
%>
	</div>
	
	

</form>
</body>
</html>