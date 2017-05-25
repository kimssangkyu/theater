<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/modify_movie.css">
<script type="text/javascript" src="/theater/common/js/modify_movie.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		initMovieAgeLimit();
		
		getMovieName();
	});
	
	function initMovieAgeLimit(){
		
		var list = $("#movie_age_limit_list");
		var itemArr = ["19세미만 관람불가", "15세 미만 관람불가", "12세 미만 관람불가", "전체 관람가"];
		for (var i = 0; i < 4; i++) {
								
			list.append("<option value='"+itemArr[i]+"'>"+itemArr[i]+"</option>");
		}
		
	}
	
	function getMovieName(){
		
		$.post("TheaterServlet?command=all_movie_name_list", 
				{},
			
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
				
				getMovieGenre();
			}
		);
	}
	
	function getMovieGenre(){
		
		$.post("TheaterServlet?command=movie_genre_list", 
				{},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#movie_genre_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var movie_genre = jsonArray.items[i].item;
					
					$("#movie_genre_list").append("<option value='"+movie_genre+"'>"+movie_genre+"</option>");
				}
				
				getMovieManufacturedCountry();
			}
		);
	}
	
	function getMovieManufacturedCountry(){
		
		$.post("TheaterServlet?command=movie_manufactured_country_list", 
				{},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				$("#movie_manufactured_country_list option").remove();
				
				for (var i = 0; i < jsonArray.items.length; i++) {
					
					var movie_manufactured_country = jsonArray.items[i].item;
					
					$("#movie_manufactured_country_list").append("<option value='"+movie_manufactured_country+"'>"+movie_manufactured_country+"</option>");
				}
				
				initComponentData();
			}
		);
	}
	
	function initComponentData(){
		
		var input_movie_name = $("#input_movie_name");
		var input_movie_runningtime = $("#input_movie_runningtime");
		var input_movie_synopsis = $("#input_movie_synopsis");
		var input_movie_director = $("#input_movie_director");
		var input_movie_actor = $("#input_movie_actor");
		var input_movie_playdate = $("#input_movie_playdate");
		var input_movie_poster = $("#input_movie_poster");
		var input_movie_trailer = $("#input_movie_trailer");
		var movie_age_limit_list = $("#movie_age_limit_list");
		var movie_genre_list = $("#movie_genre_list");
		var movie_manufactured_country_list = $("#movie_manufactured_country_list");
		
		$.post("TheaterServlet?command=select_one_movie", 
				{"movie_name":$("#movie_name_list").val()},
			
				function(data) {
					
					var jsonArray = eval("(" + data + ")");
					
					if(jsonArray.items.length == 0){
						
						return false;
					}
					
					var component = jsonArray.items[0].item.split(",");
					
					input_movie_name.val(component[0]);
					input_movie_runningtime.val(component[1]);
					input_movie_synopsis.val(component[2]);
					input_movie_director.val(component[3]);
					input_movie_actor.val(component[4]);
					input_movie_playdate.val(component[5]);
					input_movie_poster.val(component[6]);
					input_movie_trailer.val(component[7]);
					movie_age_limit_list.val(component[8]);
					movie_genre_list.val(component[9]);
					movie_manufactured_country_list.val(component[10]);
				}
		);
	}
	
	function checkInputData(){
		
		var input_movie_name = $("#input_movie_name").val();
		var input_movie_runningtime = $("#input_movie_runningtime").val();
		var input_movie_synopsis = $("#input_movie_synopsis").val();
		var input_movie_director = $("#input_movie_director").val();
		var input_movie_actor = $("#input_movie_actor").val();
		var input_movie_playdate = $("#input_movie_playdate").val();
		var input_movie_poster = $("#input_movie_poster").val();
		var input_movie_trailer = $("#input_movie_trailer").val();
		var movie_age_limit_list = $("#movie_age_limit_list").val();
		var movie_genre_list = $("#movie_genre_list").val();
		var movie_manufactured_country_list = $("#movie_manufactured_country_list").val();
		
		if(input_movie_name == ""){
			
			alert("영화 이름을 입력해주세요.");
			return;
		}else if(input_movie_runningtime == 0){
			
			alert("이름을 입력해주세요.");
			return;
		}else if(input_movie_synopsis == ""){
			
			alert("줄거리를 입력해주세요.");
			return;
		}else if(input_movie_director == ""){
			
			alert("감독 이름을 입력해주세요.");
			return;
		}else if(input_movie_actor == ""){
			
			alert("배우 이름을 입력해주세요.");
			return;
		}else if(input_movie_trailer == ""){
			
			alert("예고편을 등록해주세요.");
			return;
		}else{
			
			$.post("TheaterServlet?command=registration_movie", 
					{
					 "movie_name":input_movie_name,
					 "movie_runningtime":input_movie_runningtime,
					 "movie_synopsis":input_movie_synopsis,
					 "movie_director":input_movie_director,
					 "movie_actor":input_movie_actor,
					 "movie_playdate":input_movie_playdate,
					 "movie_poster":input_movie_poster,
					 "movie_trailer":input_movie_trailer,
					 "movie_age_limit":movie_age_limit_list,
					 "movie_genre":movie_genre_list,
					 "movie_manufactured_country":movie_manufactured_country_list
					},
				
				function(data) {
					
					var jsonArray = eval("(" + data + ")");
					
					if(jsonArray.items.length == 0){
						
						return false;
					}
					
					var result = jsonArray.items[0].item;
					
					if(result == "true"){
						
						alert("영화 등록 완료!");
						
						initComponent();
					}else{
						
						alert("영화 등록 실패!");
					}
				}
			);
		}
	}
	
	function initComponent(){
		
		$("#input_movie_name").val("");
		$("#input_movie_runningtime").val("");
		$("#input_movie_synopsis").val("");
		$("#input_movie_director").val("");
		$("#input_movie_actor").val("");
		$("#input_movie_playdate").val("");
		$("#input_movie_poster").val("");
		$("#input_movie_trailer").val("");
		
		$("#movie_age_limit_list option").remove();
		
		initMovieAgeLimit();
		
		getMovieGenre();
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 영화 -->
		<div id="movie_name_select" class="modify_form_movie">
			<div style="font-weight: bold;">영화</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_name_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>	
		<!-- 영화이름 -->
		<div id="movie_name" class="modify_form_movie">
			<div style="font-weight: bold;">영화이름</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_name" class="input_component" type="text" placeHolder="영화이름"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 상영시간 -->
		<div id="movie_runningtime" class="modify_form_movie">
			<div style="font-weight: bold;">상영시간</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_runningtime" class="input_component" type="number" min="1" placeHolder="100"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 줄거리 -->
		<div id="movie_synopsis" class="modify_form_movie">
			<div style="font-weight: bold;">줄거리</div>
			<div class='clear' style='height: 10px;'></div>
			<textarea id="input_movie_synopsis" class="input_component" placeHolder="줄거리"></textarea>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 영화감독 -->
		<div id="movie_director" class="modify_form_movie">
			<div style="font-weight: bold;">영화감독</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_director" class="input_component" type="text" placeHolder="영화감독"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 출연배우 -->
		<div id="movie_actor" class="modify_form_movie">
			<div style="font-weight: bold;">출연배우</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_actor" class="input_component" type="text" placeHolder="출연배우"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 개봉일 -->
		<div id="movie_playdate" class="modify_form_movie">
			<div style="font-weight: bold;">개봉일</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_playdate" class="input_component" type="date" placeHolder="개봉일"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 포스터  -->
		<div id="movie_poster" class="modify_form_movie">
			<div style="font-weight: bold;">포스터</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_poster" class="input_component" type="file" placeHolder="포스터"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 예고편 -->
		<div id="movie_trailer" class="modify_form_movie">
			<div style="font-weight: bold;">예고편</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_trailer" class="input_component" type="url" placeHolder="예고편"/>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 등급 -->
		<div id="movie_age_limit" class="modify_form_movie">
			<div style="font-weight: bold;">등급</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_age_limit_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 장르 -->
		<div id="movie_genre" class="modify_form_movie">
			<div style="font-weight: bold;">장르</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_genre_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 제작국가 -->
		<div id="movie_manufactured_country" class="modify_form_movie">
			<div style="font-weight: bold;">제작국가</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_manufactured_country_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<div id="modify_form" class="modify_form_movie">
			<div id="registration_button" class="input_component" onclick="checkInputData()">수정</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
		
	</div>
</body>
</html>