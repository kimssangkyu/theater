<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/registration_movie_ticket_type.css">
<script type="text/javascript" src="/theater/common/js/registration_movie_ticket_type.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getScheduleViewingDay();
	});
	
	function getScheduleViewingDay(){
		
		$.post("TheaterServlet?command=schedule_viewing_day_list", {},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#schedule_viewing_day option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var schedule_viewing_day = jsonArray.items[i].item;
					
					$("#schedule_viewing_day_list").append("<option value='"+schedule_viewing_day+"'>"+schedule_viewing_day+"</option>");
				}
				
				getScheduleViewingStartTime();
			}
		);
	}
	
	function getScheduleViewingStartTime(){
		
		$.post("TheaterServlet?command=ticket_type_schedule_viewing_start_time_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val()},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#schedule_viewing_start_time option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var schedule_viewing_start_time = jsonArray.items[i].item;
					
					$("#schedule_viewing_start_time_list").append("<option value='"+schedule_viewing_start_time+"'>"+schedule_viewing_start_time+"</option>");
				}
				
				getTheaterName();
			}
		);
	}
	
	function getTheaterName(){
		
		$.post("TheaterServlet?command=ticket_type_theater_all_list", 
				
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(),
				 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val()
				},
			
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
		
		$.post("TheaterServlet?command=ticket_type_screen_name_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(),
			 	 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val(),
			 	 "theater_name":$("#theater_name_list").val(),
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
				
				getMovieName();
			}
		);
	}
	
	function getMovieName(){
		
		$.post("TheaterServlet?command=ticket_type_movie_name_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(),
		 	 	 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val(),
				 "theater_name":$("#theater_name_list").val(),
		 		 "screen_name":$("#screen_name_list").val()
			},
			
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
				
				getTicketType();
			}
		);
	}
	
	function getTicketType(){
		
		$.post("TheaterServlet?command=ticket_type_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(),
		 	 	 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val(),
				 "theater_name":$("#theater_name_list").val(),
		 		 "screen_name":$("#screen_name_list").val(),
		 		 "movie_name":$("#movie_name_list").val()
			},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#ticket_type_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var ticket_type = jsonArray.items[i].item;
					
					$("#ticket_type_list").append("<option value='"+ticket_type+"'>"+ticket_type+"</option>");
				}
			}
		);
	}
	
	function checkInputData(){
		
		var schedule_viewing_day_list = $("#schedule_viewing_day_list").val();
		var schedule_viewing_start_time_list = $("#schedule_viewing_start_time_list").val();
		var theater_name_list = $("#theater_name_list").val();
		var screen_name_list = $("#screen_name_list").val();
		var movie_name_list = $("#movie_name_list").val();
		var input_ticket_type = $("#input_ticket_type").val();
		var input_ticket_price = $("#input_ticket_price").val();
		var ticket_type_list = $("#ticket_type_list").val();
		
		for(var i=0; i<$("#ticket_type_list option").size(); i++){
			
			if($("#ticket_type_list option").get(i).text == input_ticket_type){
				
				alert("이미 등록된 티켓 타입 이름입니다.");
				return;
			}	
		}
		
		if(input_ticket_type == ""){
			
			alert("티켓 타입을 입력해주세요.");
			return;
		}else if(input_ticket_price == ""){
			
			alert("티켓 타입 별 가격을 입력해주세요.");
			return;
		}
		
		$.post("TheaterServlet?command=registration_ticket_type", 
				{"schedule_viewing_day":schedule_viewing_day_list,
			 	 "schedule_viewing_start_time":schedule_viewing_start_time_list,
			 	 "schedule_viewing_end_time":theater_name_list,
			 	 "theater_name":theater_name_list,
			 	 "screen_name":screen_name_list,
				 "movie_name":movie_name_list,
				 "ticket_type":input_ticket_type,
				 "ticket_price":input_ticket_price,
			 },
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
								
					return false;
				}
							
				var result = jsonArray.items[0].item;
				
				if(result == "true"){
					
					alert("티켓 타입 등록 완료!");
					
					initComponent();
				}else{
					
					alert("티켓 타입 등록 실패!");
				}
			}
		);
	}
	
	function initComponent(){
		
		$("#input_ticket_type").val("");
		$("#input_ticket_price").val("");
		
		getScheduleViewingDay();
	}
	
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 스케쥴 -->
		<div id="schedule_viewing_day" class="registration_form_ticket_type">
			<div style="font-weight: bold;">상영일</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="schedule_viewing_day_list" onchange="getScheduleViewingStartTime()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영 시작시간 -->
		<div id="schedule_viewing_start_time" class="registration_form_ticket_type">
			<div style="font-weight: bold;">상영 시작시간</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="schedule_viewing_start_time_list" onchange="getTheaterName"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 지점 -->
		<div id="theater_name" class="registration_form_ticket_type">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onchange="getScreenName()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영관 -->
		<div id="theater_name" class="registration_form_ticket_type">
			<div style="font-weight: bold;">상영관</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="screen_name_list" onchange=""></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 영화 -->
		<div id="theater_name" class="registration_form_ticket_type">
			<div style="font-weight: bold;">영화</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_name_list" onchange="getMovieName"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 등록된 티켓 타입 -->
		<div id="ticket_type" class="registration_form_ticket_type">
			<div style="font-weight: bold;">등록된 티켓 타입</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="ticket_type_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>		
		<!-- 티켓 타입 -->
		<div id="ticket_type" class="registration_form_ticket_type">
			<div style="font-weight: bold;">티켓 타입</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_ticket_type" class="input_component" type="text" placeHolder="티켓 타입"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 티켓 타입 별 가격 -->
		<div id="ticket_price" class="registration_form_ticket_type">
			<div style="font-weight: bold;">티켓 타입 별 가격</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_ticket_price" class="input_component" type="text" placeHolder="티켓 타입 별 가격"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<div id="registration_form" class="registration_form_ticket_type">
			<div id="registration_button" class="input_component" onclick="checkInputData()">등록</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
	</div>
</body>
</html>