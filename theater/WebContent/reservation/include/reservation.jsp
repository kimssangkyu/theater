<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/reservation.css">
<script type="text/javascript" src="/theater/common/js/reservation.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getScheduleViewingDayList();
		
		display_all_none();
	});
	
	function getScheduleViewingDayList() {
		
		$.post("TheaterServlet?command=schedule_viewing_day_list", {},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					$('#schedule_viewing_day_list').css("display", "none");
					
					return false;
				}
				
				$("#schedule_viewing_day_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {

					var schedule_viewing_day = jsonArray.items[i].item;
					
					$("#schedule_viewing_day_list").append("<option value='"+schedule_viewing_day+"'>"+schedule_viewing_day+"</option>");
				}
				
				display_all_none();
			}
		);
	};
	
	function getTheaterName(){
		
		$.post("TheaterServlet?command=theater_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val()},
			
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
				
				display_5_none();
			}
		);
	}
	
	function getMovieName(){
		
		$.post("TheaterServlet?command=movie_name_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(), 
				 "theater_name":$("#theater_name_list").val()},
			
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
				
				display_4_none();
			}
		);
	}
	
	function getScheduleViewingStartTime(){
		
		$.post("TheaterServlet?command=schedule_viewing_start_time_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(), 
				 "movie_name":$("#movie_name_list").val(), 
				 "theater_name":$("#theater_name_list").val()},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
							
				if(jsonArray.items.length == 0){
								
					return false;
				}
							
				$("#schedule_viewing_start_time_list option").remove();
							
				for (var i = 0; i < jsonArray.items.length; i++) {
								
					var schedule_viewing_start_time = jsonArray.items[i].item;
										
					$("#schedule_viewing_start_time_list").append("<option value='"+schedule_viewing_start_time+"'>"+schedule_viewing_start_time+"</option>");
				}
				
				display_3_none();
			}
		);
	}
	
	function getScreenName(){
		
		$.post("TheaterServlet?command=screen_name_list", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(), 
				 "movie_name":$("#movie_name_list").val(), 
				 "theater_name":$("#theater_name_list").val(), 
				 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val()},
			
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
				
				display_2_none();
			}
		);
	}
	
	function getScreenSeat(){
		
		$.post("TheaterServlet?command=screen_seat_count", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(), 
			 	 "movie_name":$("#movie_name_list").val(), "theater_name":$("#theater_name_list").val(), 
			 	 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val(), 
				 "screen_name":$("#screen_name_list").val()},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
							
				if(jsonArray.items.length == 0){
								
					return false;
				}
							
				$("#screen_seat_list").html("");
				
				var count = jsonArray.items[0].item;
				
				
				for (var i = 1; i <= count; i++) {
					
					var itemArr = jsonArray.items[i].item.split("#");
					
					if(itemArr[0] == "true"){
						
						$("#screen_seat_list").append("<div id='"+i+"#"+itemArr[1]+"#"+itemArr[2]+"' class='screen_seat_unit'></div>");
					}else{
						
						if(itemArr[1] == 'A석'){
							
							$("#screen_seat_list").append("<div id='"+i+"#"+itemArr[1]+"#"+itemArr[2]+"' class='screen_seat_unit2' onclick='getPayment(this)'></div>");
						}else if(itemArr[1] == 'B석'){
							
							$("#screen_seat_list").append("<div id='"+i+"#"+itemArr[1]+"#"+itemArr[2]+"' class='screen_seat_unit3' onclick='getPayment(this)'></div>");
						}else if(itemArr[1] == 'C석'){
							
							$("#screen_seat_list").append("<div id='"+i+"#"+itemArr[1]+"#"+itemArr[2]+"' class='screen_seat_unit4' onclick='getPayment(this)'></div>");
						}
					}
					
					if(i >= 10 && i%10==0) $("#screen_seat_list").append("<div class='clear' style='height: 10px;'></div>");
				}
				
				display_1_none();
			}
		);
	}
	
	function getPayment(item){
		
		display_0_none();
		
		var itemArr = item.id.split("#");
		
		public_seat_number = itemArr[0];
		public_ticket_price = itemArr[2];
		var ticket_type = itemArr[1];
		
		$("#span_seat_number").html(public_seat_number+"번");
		$("#span_ticket_type").html(ticket_type);
		$("#span_ticket_price").html(public_ticket_price+"원");
	}
	
	var public_seat_number;
	var public_ticket_price;
	
	function payment_button(){
		
		$.post("TheaterServlet?command=schedule_code", 
				{"schedule_viewing_day":$("#schedule_viewing_day_list").val(), 
				 "movie_name":$("#movie_name_list").val(), 
				 "theater_name":$("#theater_name_list").val(), 
				 "schedule_viewing_start_time":$("#schedule_viewing_start_time_list").val()},
				 
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
							
				if(jsonArray.items.length == 0){
								
					return false;
				}
				
				var scheduleCode = jsonArray.items[0].item;
				
				$.post("TheaterServlet?command=check_account_balance", 
						{"ticket_sale_price":public_ticket_price,
						 "customer_id":'user01'},
				
					function(data) {
						
						var jsonArray = eval("(" + data + ")");
									
						if(jsonArray.items.length == 0){
										
							return false;
						}
						
						var result = jsonArray.items[0].item;
						
						if(result == "true"){
							
							$.post("TheaterServlet?command=insert_reservation", 
									{"seat_number":public_seat_number, 
									 "ticket_sale_price":public_ticket_price,
									 "customer_id":'user01', 
									 "schedule_code":scheduleCode},
							
								function(data) {
									
									var jsonArray = eval("(" + data + ")");
												
									if(jsonArray.items.length == 0){
													
										return false;
									}
									
									var result = jsonArray.items[0].item;
									
									if(result == "true"){
										
										alert("티켓팅 완료!");
										location.href='TheaterServlet?command=reservation_list';
									}else{
										
										alert("티켓팅 실패!");
									}
									
								}
							);
						}else{
							
							alert("잔액이 부족합니다.");
						}
					}
				);
			}
		);
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 일자 -->
		<div id="schedule_viewing_day" class="reservavtion">
			<div style="font-weight: bold;">일자</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="schedule_viewing_day_list" onChange="getTheaterName()" onClick="getTheaterName()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 극장 -->
		<div id="theater_name" class="reservation">
			<div style="font-weight: bold;">지점</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="theater_name_list" onChange="getMovieName()" onclick="getMovieName()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 영화 -->
		<div id="movie_name" class="reservation">
			<div style="font-weight: bold;">영화</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_name_list" onChange="getScheduleViewingStartTime()" onClick="getScheduleViewingStartTime()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 일시 -->
		<div id="schedule_viewing_start_time" class="reservation">
			<div style="font-weight: bold;">일시</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="schedule_viewing_start_time_list" onChange="getScreenName()" onClick="getScreenName()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영관 -->
		<div id="screen_name" class="reservation">
			<div style="font-weight: bold;">상영관</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="screen_name_list" onChange="getScreenSeat()" onClick="getScreenSeat()"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 좌석 -->
		<div id="screen_seat" class="reservation"">
			<div class='clear' style='height: 10px;'></div>
			<div id="div_screen">스 크 린</div>
			<div class='clear' style='height: 10px;'></div>
			<div id="screen_seat_list" style="height: 100px;"></div>
			<div class='clear' style='height: 10px;'></div>
		</div>
		<div class='clear' style='height: 20px;'></div>
		<!-- 결제 -->
		<div id="payment" class="reservation">
			<div style="font-weight: bold;">결제</div>
			<div class='clear' style='height: 10px;'></div>
			<div id="payment" style="height: 100px;">
				<div class="payment">좌석 번호 : <span id="span_seat_number">미정</span></div>
				<div class='clear' style='height: 10px;'></div>
				<div class="payment">좌석 타입 : <span id="span_ticket_type">미정</span></div>
				<div class='clear' style='height: 10px;'></div>
				<div class="payment">좌석 가격 : <span id="span_ticket_price">미정</span></div>
				<div class='clear' style='height: 10px;'></div>
				<div id="payment_button" onclick="payment_button()">결제 버튼</div>
				<div class='clear' style='height: 20px;'></div>
			</div>
		</div>
	</div>
</body>
</html>