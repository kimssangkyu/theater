<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/registration_theater.css">
<script type="text/javascript" src="/theater/common/js/registration_theater.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getTheaterName();
		
		incrementScreen();
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
			}
		);
	}
	
	function checkInputData(){
		
		var theaterName = $("#input_theater_name").val();
		var theaterLocation = $("#input_theater_location").val();
		
		var result = false;
		
		for(var i=0; i<$("#theater_name_list option").size(); i++){
			
			if(theaterName == $("#theater_name_list option").get(i).text){
				
				result = true;
			}
		}
		
		if(result == true){
			
			alert("이미 등록되어있는 지점 이름입니다.");
			return;
		}
		
		for(var i=1; i<=$("#input_thetaer_screen_count").val(); i++){
			
			for(var j=1; j<=$("#input_thetaer_screen_count").val(); j++){
				
				if(i!=j && $("#input_screen_name"+i).val() == $("#input_screen_name"+j).val()){
					
					alert(i+"번째 상영관 이름과 " +j+ "번째 상영관 이름이 같습니다.");
					
					return;	
				}
			}
		}
		
		var screen_name_arr;
		var screen_seat_count_arr;
		
		for(var i=1; i<= $("#input_thetaer_screen_count").val(); i++){
			
			if(i==1){
				
				screen_name_arr = $("#input_screen_name"+i).val()+",";
				screen_seat_count_arr = $("#input_screen_seat_count"+i).val()+",";	
			}else{
				
				screen_name_arr += $("#input_screen_name"+i).val()+",";
				screen_seat_count_arr += $("#input_screen_seat_count"+i).val()+",";
			}
		}
		
		if(theaterName == ""){
			
			alert("지점 이름을 정확히 입력해주세요.");
			return;
		}else if(theaterLocation == ""){
			
			alert("지점 위치를 정확히 입력해주세요.");
			return;
		}else{
			
			$.post("TheaterServlet?command=registration_theater", 
					
				{"theater_name":theaterName,
				 "theater_location":theaterLocation,
				 "theater_screen_count":$("#input_thetaer_screen_count").val(),
				 "screen_name": screen_name_arr,
				 "screen_seat_count": screen_seat_count_arr
				},
				
				function(data) {
					
					var jsonArray = eval("(" + data + ")");
							
					if(jsonArray.items.length == 0){
								
						return false;
					}
					
					for (var i = 0; i < jsonArray.items.length; i++) {
								
						var result = jsonArray.items[i].item;
								
						if(result == "true"){
									
							alert("등록이 완료되었습니다.");
							initComponent();
							return false;
						}else{
									
							alert("등록에 실패했습니다.");
							return false;
						}
					}
				}
			);
		}
	}
	
	function incrementScreen(){
		
		$("#increment_screen_area").html("");
		
		var row = $("#input_thetaer_screen_count").val();
		
		for(var i=1; i<=row; i++){
			
			var increment_screen_area = $("#increment_screen_area");
			
			increment_screen_area.append("<div id='screen"+i+"' class='screen'>");
			
			var screen = $("#screen"+i);
			
			screen.append("<div style='font-weight: bold;'>상영관 이름</div>");
			screen.append("<div class='clear' style='height: 10px;'></div>");
			screen.append("<input id='input_screen_name"+i+"' class='input_component' type='text' placeHolder='상영관 이름'/>");
			screen.append("<div class='clear' style='height: 10px;'></div>");
			screen.append("<div style='font-weight: bold; '>좌석 수</div>");
			screen.append("<div class='clear' style='height: 10px;'></div>");
			screen.append("<input id='input_screen_seat_count"+i+"' class='input_component' type='number' max='500' min='1' value='1' step='1'/>");
			increment_screen_area.append("</div>");
			increment_screen_area.append("<div class='clear' style='height: 30px;'></div>");
		}
	}
	
	function initComponent(){
		
		getTheaterName();
		
		$("#input_theater_name").val("");
		$("#input_theater_location").val("");
		$("#input_thetaer_screen_count").val("1");
		incrementScreen();
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<div id="theater_name" class="registration_form_theater">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onchange="initComponent()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 이름 -->
		<div id="theater_name" class="registration_form_theater">
			<div style="font-weight: bold;">지점 이름</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_theater_name" class="input_component" type="text" placeHolder="지점 이름"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 위치 -->
		<div id="theater_name" class="registration_form_theater">
			<div style="font-weight: bold;">지점 위치</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_theater_location" class="input_component" type="text" placeHolder="지점 위치"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영관 수 -->
		<div id="theater_name" class="registration_form_theater">
			<div style="font-weight: bold;">상영관 수</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_thetaer_screen_count" class="input_component" type="number" value="1" max="10" min="1" step="1" onchange="incrementScreen()"/>
			<div class='clear' style='height: 20px;'></div>
			<div id="increment_screen_area"></div>
		</div>
		<div id="registration_form" class="registration_form_theater">
			<div id="registration_button" class="input_component" onclick="checkInputData()">등록</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
		
	</div>
</body>
</html>