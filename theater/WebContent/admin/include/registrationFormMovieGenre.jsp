<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/registration_movie_genre.css">
<script type="text/javascript" src="/theater/common/js/registration_movie_genre.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getMovieGenre();
	});
	
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
			}
		);
	}
	
	function checkInputData(){
		
		var movieGenreName = $("#input_movie_genre_name").val();
		
		var result = false;
		
		for(var i=0; i<$("#movie_genre_list option").size(); i++){
			
			if(movieGenreName == $("#movie_genre_list option").get(i).text){
				
				result = true;
			}
		}
		
		if(result == true){
			
			alert("이미 등록되어있는 장르 이름입니다.");
			return;
		}
		
		$.post("TheaterServlet?command=registration_movie_genre", 
				{
				 "movie_genre_name":movieGenreName
				},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				var result = jsonArray.items[0].item;
				
				if(result == "true"){
					
					alert("장르 등록 완료!");
				}else{
					
					alert("장르 등록 실패!");
				}
			}
		);
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 장르 -->
		<div id="movie_genre" class="registration_form_genre">
			<div style="font-weight: bold;">장르</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_genre_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 장르 이름 -->
		<div id="movie_genre_name" class="registration_form_genre">
			<div style="font-weight: bold;">장르이름</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_genre_name" class="input_component" type="text" placeHolder="장르이름"/>
			<div class='clear' style='height: 20px;'></div>
		</div>		
		<div id="registration_form" class="registration_form_genre">
			<div id="registration_button" class="input_component" onclick="checkInputData()">등록</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
		
	</div>
</body>
</html>