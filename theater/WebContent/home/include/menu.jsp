<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function(){
		
		$('#voucher_step1').bind('click', function(){
			
			if('${sessionScope.id}' == ""){
				
				alert("로그인 해주세요.");
			}else{
				
				
			}
		});
		
		$('#update_member_form_step1').bind('click', function(){
			
			if('${sessionScope.id}' == ""){
				
				alert("로그인 해주세요.");
			}else{
				
				location.replace("TheaterServlet?command=update_member_form_step1?id=${sessionScope.id }");
			}
		});
	});
</script>
<div id="menu">
	<div style="float:left"><a href="TheaterServlet?command=index"><img src="/theater/common/images/home/mu00.jpg" class="menu"></a></div>
	<div style="float:left"><a href="TheaterServlet?command="><img src="/theater/common/images/home/mu01.jpg" class="menu"></a></div>
	<div style="float:left"><a href="TheaterServlet?command=reservation_list"><img src="/theater/common/images/home/mu02.jpg" class="menu"></a></div>
	<div style="float:left"><a href="TheaterServlet?command="><img src="/theater/common/images/home/mu06.jpg" class="menu"></a></div>
	<div style="float:left"><a href="TheaterServlet?command=" id="voucher_step1"><img src="/theater/common/images/home/mu03.jpg" class="menu"></a></div>
	<c:if test="${sessionScope.id != 'admin' }">
		<div style="float:left"><a href="#" id="update_member_form_step1"><img src="/theater/common/images/home/mu04.jpg" class="menu"></a></div>
	</c:if>
	<c:if test="${sessionScope.id == 'admin' }">
		<div style="float:left"><a href="TheaterServlet?command=list"><img src="/theater/common/images/home/mu05.jpg" class="menu"></a></div>
	</c:if>
	<div style="float:left"><img src="/theater/common/images/home/blank.jpg" class="menu" style="width: 454px; height: 30px;"></div>
</div>