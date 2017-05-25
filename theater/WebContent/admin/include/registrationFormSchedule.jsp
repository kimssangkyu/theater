<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/registration_movie_genre.css">
<script type="text/javascript" src="/theater/common/js/registration_movie_genre.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getMovieName();
	});
	
	function getMovieName(){
		
		$.post("TheaterServlet?command=premiere_movie_name_list", {},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#movie_name_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var movie_name = jsonArray.items[i].item;
					
					$("#movie_name_list").append("<option value='"+movie_name+"'>"+movie_name+"</option>");
				}
				
				getTheaterName();
			}
		);
	}
		
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
				
				getScreenName();
			}
		);
	}
	
	function getScreenName(){
		
		$.post("TheaterServlet?command=each_screen_name_list", 
				{"theater_name":$("#theater_name_list").val(),
				 },
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
							
				if(jsonArray.items.length == 0){
								
					return false;
				}
							
				$("#screen_name_list option").remove();
							
				for (var i = 0; i < jsonArray.items.length; i++) {
								
					var screen_name = jsonArray.items[i].item;
										
					$("#screen_name_list").append("<option value='"+screen_name+"'>"+screen_name+"</option>");
				}
			}
		);
	}
	
	function checkInputData(){
		
		var input_schedule_viewing_day = $("#input_schedule_viewing_day").val();
		var input_schedule_viewing_start_time = $("#input_schedule_viewing_start_time").val();
		var input_schedule_viewing_end_time = $("#input_schedule_viewing_end_time").val();
		var movie_name_list = $("#movie_name_list").val();
		var theater_name_list = $("#theater_name_list").val();
		var screen_name_list = $("#screen_name_list").val();
		
		if(input_schedule_viewing_day == ""){
			
			alert("상영일을 입력해주세요.");
			return;
		}else if(input_schedule_viewing_start_time == ""){
			
			alert("상영 시작 시간을 입력해주세요.");
			return;
		}else if(input_schedule_viewing_end_time == ""){
			
			alert("상영 종료 시간을 입력해주세요.");
			return;
		}
		
		$.post("TheaterServlet?command=registration_schedule", 
				{"schedule_viewing_day":input_schedule_viewing_day,
			 	 "schedule_viewing_start_time":input_schedule_viewing_start_time,
			 	 "schedule_viewing_end_time":input_schedule_viewing_end_time,
				 "movie_name":movie_name_list,
				 "theater_name":theater_name_list,
			 	 "screen_name":screen_name_list
			 },
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
								
					return false;
				}
							
				var result = jsonArray.items[0].item;
				
				if(result == "true"){
					
					alert("스케쥴 등록 완료!");
					
					initComponent();
				}else{
					
					alert("스케쥴 등록 실패!");
				}
			}
		);
	}
	
	function initComponent(){
		
		$("#input_schedule_viewing_day").val("");
		$("#input_schedule_viewing_start_time").val("");
		$("#input_schedule_viewing_end_time").val("");
		
		getMovieName();
	}
	
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 상영일 -->
		<div id="schedule_viewing_day" class="registration_form_schedule">
			<div style="font-weight: bold;">상영일</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_schedule_viewing_day" class="input_component" type="date" placeHolder="상영일"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영 시작시간 -->
		<div id="schedule_viewing_start_time" class="registration_form_schedule">
			<div style="font-weight: bold;">상영 시작시간</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_schedule_viewing_start_time" class="input_component" type="text" placeHolder="상영 시작시간"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영 종료시간 -->
		<div id="schedule_viewing_end_time" class="registration_form_schedule">
			<div style="font-weight: bold;">상영 종료시간</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_schedule_viewing_end_time" class="input_component" type="text" placeHolder="상영 종료시간"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 영화 -->
		<div id="theater_name" class="registration_form_schedule">
			<div style="font-weight: bold;">영화</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_name_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 -->
		<div id="theater_name" class="registration_form_schedule">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onchange="getScreenName()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영관 -->
		<div id="theater_name" class="registration_form_schedule">
			<div style="font-weight: bold;">상영관</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="screen_name_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>		
		<div id="registration_form" class="registration_form_schedule">
			<div id="registration_button" class="input_component" onclick="checkInputData()">등록</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
		
	</div>
</body>
</html>