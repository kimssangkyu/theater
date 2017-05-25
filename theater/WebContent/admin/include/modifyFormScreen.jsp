<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/modify_form_screen.css">
<script type="text/javascript" src="/theater/common/js/modify_screen.js"></script>
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
				
				getScreenInfo();
			}
		);
	}
	
	function getScreenInfo(){
		
		$.post("TheaterServlet?command=screen_info", 
				{"theater_name":$("#theater_name_list").val()},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#increment_screen_area").html("");
				
				row = jsonArray.items.length;
				
				for (var i = 1; i <= row; i++) {
					
					var screen_info = jsonArray.items[i-1].item.split(",");
					
					var screen_name = screen_info[0];
					var screen_seat_count = screen_info[1];
					
					var increment_screen_area = $("#increment_screen_area");
					
					increment_screen_area.append("<div id='screen"+i+"' class='screen'>");
					
					var screen = $("#screen"+i);
					
					origin_screen_name += screen_name+",";
					
					screen.append("<div style='font-weight: bold;'>상영관 이름</div>");
					screen.append("<div class='clear' style='height: 10px;'></div>");
					screen.append("<input id='input_screen_name"+i+"' class='input_component' type='text' value='"+screen_name+"' placeHolder='상영관 이름'/>");
					screen.append("<div class='clear' style='height: 10px;'></div>");
					screen.append("<div style='font-weight: bold; '>좌석 수</div>");
					screen.append("<div class='clear' style='height: 10px;'></div>");
					screen.append("<input id='input_screen_seat_count"+i+"' class='input_component' type='number' max='500' min='1' value='"+screen_seat_count+"' step='1'/>");
					increment_screen_area.append("</div>");
					increment_screen_area.append("<div class='clear' style='height: 30px;'></div>");
				}
			}
		);
	}
	
	var row = 0;
	var origin_screen_name = "";
	function checkModifyInputData(){
		
		var theater_name = $("#theater_name_list").val();
		var screen_name = $("#input_screen_name").val();
		var screen_seat_count = $("#input_screen_seat_count").val();
		
		var new_screen_name = "";
		var new_screen_seat_count = "";
		
		var result = false;
		
		for(var i=1; i<=row; i++){
			
			new_screen_name += $("#input_screen_name"+i).val()+",";
			new_screen_seat_count += $("#input_screen_seat_count"+i).val()+",";
		}
		
		for(var i=1; i<=row; i++){
			
			for(var j=1; j<=row; j++){
				
				if(i!=j && $("#input_screen_name"+i).val() == $("#input_screen_name"+j).val()){
					
					alert(i+"번째 상영관 이름과 " +j+ "번째 상영관 이름이 같습니다.");
					
					return;	
				}
			}
		}
		
		$.post("TheaterServlet?command=modify_screen", 
				
			{"origin_theater_name":theater_name,
			 "origin_screen_name":origin_screen_name,
			 "screen_name":new_screen_name,
			 "screen_seat_count":new_screen_seat_count
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
		<div id="theater_name" class="modify_form_screen">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onchange="getScreenInfo()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영관 수 -->
		<div id="screen_name" class="modify_form_screen">
			<div id="increment_screen_area"></div>
		</div>
		<div id="modify_form" class="modify_form_screen" style="float: left; margin-right: 10px;">
			<div id="modify_button" class="input_component" onclick="checkModifyInputData()">수정</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
	</div>
</body>
</html>