<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/modify_form_theater.css">
<script type="text/javascript" src="/theater/common/js/registration_theater.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getTheaterName();
	});
	
	function getTheaterName(){
		
		$.post("TheaterServlet?command=theater_all_list", {},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#theater_name_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var theater_name = jsonArray.items[i].item;
					
					$("#theater_name_list").append("<option value='"+theater_name+"'>"+theater_name+"</option>");
				}
				
				getTheaterInfo();
			}
		);
	}
	
	function getTheaterInfo(){
		
		$.post("TheaterServlet?command=theater_info", 
				{"theater_name":$("#theater_name_list").val()},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				var theater_name = jsonArray.items[0].item;
				var theater_location = jsonArray.items[1].item;
				
				$("#input_theater_name").val(theater_name);
				$("#input_theater_location").val(theater_location);
			}
		);
	}
	
	function checkModifyInputData(){
		
		var originTheaterName = $("#theater_name_list").val();
		var newTheaterName = $("#input_theater_name").val();
		var theaterLocation = $("#input_theater_location").val();
		
		var result = false;
		
		for(var i=0; i<$("#theater_name_list option").size(); i++){
			
			if($("#theater_name_list").val() != newTheaterName && $("#theater_name_list option").get(i).text == newTheaterName){
				
				alert("이미 등록된 티켓 타입 이름입니다.");
				return;
			}
		}
		
		$.post("TheaterServlet?command=modify_theater", 
				
			{"origin_theater_name":originTheaterName,
			 "new_theater_name":newTheaterName,
			 "theater_location":theaterLocation			
			},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
						
				if(jsonArray.items.length == 0){
							
					return false;
				}
				
				for (var i = 0; i < jsonArray.items.length; i++) {
							
					var result = jsonArray.items[i].item;
							
					if(result == "true"){
								
						alert("변경이 완료되었습니다.");
						initComponent();
						return false;
					}else{
								
						alert("변경에 실패했습니다.");
						return false;
					}
				}
			}
		);
	}

	function initComponent(){
		
		getTheaterName();
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<div id="theater_name" class="modify_form_theater">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onchange="getTheaterInfo()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 이름 -->
		<div id="theater_name" class="modify_form_theater">
			<div style="font-weight: bold;">지점 이름</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_theater_name" class="input_component" type="text" placeHolder="지점 이름"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 위치 -->
		<div id="theater_name" class="modify_form_theater">
			<div style="font-weight: bold;">지점 위치</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_theater_location" class="input_component" type="text" placeHolder="지점 위치"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<div id="modify_form" class="modify_form_theater" style="float: left; margin-right: 10px;">
			<div id="modify_button" class="input_component" onclick="checkModifyInputData()">수정</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
	</div>
</body>
</html>