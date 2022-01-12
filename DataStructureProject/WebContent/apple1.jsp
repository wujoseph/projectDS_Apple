<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

.container { 
  height: 445px;
  width: 500px;
  position: relative;
  top: 70px;
  margin: auto;

}
.advance{
  height: 80px;
  width: 500px;
  position: relative;
  
  margin: auto;
  background:#C0C0C0;

  border-radius: 10px;
  display:  none;
}

.adInput{
  width: 100px;
  postion: relative;
  margin: auto;

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
<script type="text/javascript">
	function displaceAdvance(){
		if(document.getElementById("advanceSearch").style.display === 'block'){			
			document.getElementById("advanceSearch").style.display = 'none'
		}else{
			document.getElementById("advanceSearch").style.display = 'block'
		}
	}
</script>
</head>
<body style='background-color:#FFFFFF'>
<form action='${requestUri}' method='get'>



	<div class="container" align="center">
		<div>
			<img src="https://www.applesfromny.com/wp-content/uploads/2020/06/SnapdragonNEW.png" style = 'position:relative;width:350px;height:275px;'></div>
		<div>
			<input type='text' class="text" id="padding"  style='font-size:120%;position:relative;width:500px;height:50px;' name='keyword' placeholder='請輸入關鍵字'
onfocus="placeholder= '' " onblur="placeholder='請輸入關鍵字'" /></div>
			<input type = "submit" value="搜尋" class="input" style ='position:relative;height:40px;width:130px;margin-top: 5px;'>
			<button  type="button" value="進階搜尋" class="input" onclick='displaceAdvance()' style ='position:relative;height:40px;width:130px;margin-top: 5px;'>進階搜尋</button>
		<div id="advanceSearch" class="advance">
			<div>顯示數量:
				<input type='text' class="adInput" name = "displayAmount" placeholder='100' onfocus="placeholder= '' " onblur="placeholder='100'">
			</div>
			<div>搜尋網頁深度:
				<input class="adInput" name = "searchDepth" placeholder='0' onfocus="placeholder= '' " onblur="placeholder='0'">
			</div>
			<div>子網頁搜尋數量:
				<input class="adInput" name = "subWebsiteAmount" placeholder='0' onfocus="placeholder= '' " onblur="placeholder='0'">
			</div>
		</div>
	</div> 
	


</form>
</body>
</html>