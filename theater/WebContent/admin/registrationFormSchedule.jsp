<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>메인</title>
<link rel="stylesheet" href="/theater/common/css/style1.css">
<script type="text/javascript" src="/theater/common/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<!-- header -->
<div id="header">
	<%@include file="/home/include/header.jsp" %>
</div>
<!-- //header -->

<!-- navigator -->
<div id="navigator">
	<%@include file="/home/include/menu.jsp" %>
</div>
<!-- //navigator -->

<div class="clear" style="height:30px;"></div>

<!-- content -->
<div id="content">
	<%@include file="/admin/include/registrationFormSchedule.jsp" %>
</div>
<!-- //content -->

<div class="clear" style="height:20px;"></div>

<!-- footer -->
<div id="footer">
	<%@include file="/home/include/copyright.jsp" %>
</div>
<!-- //footer -->
</body>
</html>