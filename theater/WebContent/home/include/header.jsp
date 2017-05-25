<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	function checkLogin(){

		var logid = document.getElementById("logid").value;
		var logpw = document.getElementById("logpw").value;
	
		if(logid.value == ""){
			alert("아이디를 입력해주세요!");
		}else if(logpw.value == ""){
			alert("패스워드를 입력해주세요!");
		}else{
			loginform.submit();
		}
	}
</script>
<div id="h_left" style="float:left">
	<img src="/theater/common/images/home/logo.jpg">
</div>
<div id="h_right" style="float:left; height:73px">
	<%if(session.getAttribute("id") !=null){%>
		<div id="loginSuccess">
			<div class="clear" style="height:10px;"></div>
			<div style="height:30px; padding-top: 15px;"><font style="font-weight: bold; color: #a9a9a9;">${id} 님 환영합니다.</font></div>
			<div style="height:20px;">
				<a href="TheaterServlet?command=update_form&id=${sessionScope.id}"><img src="/theater/common/images/button/updateMemBtn1.jpg"/></a> 
				<a href="TheaterServlet?command=delete_member_form_step1?id=${sessionScope.id}"><img src="/theater/common/images/button/deleteMemBtn1.jpg"/></a>  
				<a href="TheaterServlet?command=logout"><img src="/theater/common/images/button/logoutBtn1.jpg"/></a>
			</div>
		</div>
	<%}else{ %>
		<div class="clear" style="height:5px;"></div>
		<div id="login">
			<form method="post" id="loginform" name="loginform" action="">
				<div style="width:1px; padding-left: 4px; padding-top: 3px; float:left;"></div>
				<div style="width:100px; float:left;">
					<input type="text" id="logid" name="logid" placeholder="아 이 디" style="width:183px; height:15px; font-weight: bold; border-radius: 5px; font-size: 11px; color: #a9a9a9; padding-left: 10px;"/>
				</div>
				<div class="clear" style="height:5px;"></div>
				<div style="width:1px; padding-left: 4px; padding-top: 3px; float:left;"></div>
				<div style="width:100px; float:left;">
					<input type="password" id="logpw" name="logpw" placeholder="패스워드" style="width:183px; height:15px; font-weight: bold; border-radius: 5px; font-size: 11px; color: #a9a9a9; padding-left: 10px;"/>
				</div>
				<div class="clear" style="height:5px;"></div>
				<div style="width:200px;" align="right">
					<a href="TheaterServlet?command=insert_member_formStep1"><img src="/theater/common/images/button/registerMemBtn1.jpg"/></a>
					<img src="/theater/common/images/button/loginBtn1.jpg" style="cursor:pointer;" onclick="checkLogin()"/>
					<a href="TheaterServlet?command=search_id"><img src="/theater/common/images/button/searchBtn1.jpg" style="width:70px; height: 17px;"/></a>
				</div>
			</form>
		</div>
	<%} %>
</div>
<div class="clear" style="background-color: #ffffff; height:5px;"></div>