<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/theater/common/css/registration_movie_manufactured_country.css">
<script type="text/javascript" src="/theater/common/js/registration_movie_manufactured_country.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		
		getMovieManufacturedCountry();
	});
	
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
			}
		);
	}
	
	function checkInputData(){
		
		var movieMovieManufacturedCountryName = $("#input_movie_manufactured_country_name").val();
		
		var result = false;
		
		for(var i=0; i<$("#movie_manufactured_country_list option").size(); i++){
			
			if(movieMovieManufacturedCountryName == $("#movie_manufactured_country_list option").get(i).text){
				
				result = true;
			}
		}
		
		if(result == true){
			
			alert("이미 등록되어있는 제작국가 이름입니다.");
			return;
		}
		
		$.post("TheaterServlet?command=registration_movie_manufactured_country", 
				{
				 "movie_manufactured_country_name":movieMovieManufacturedCountryName
				},
			
			function(data) {
				
				var jsonArray = eval("(" + data + ")");
				
				if(jsonArray.items.length == 0){
					
					return false;
				}
				
				var result = jsonArray.items[0].item;
				
				if(result == "true"){
					
					alert("제작국가 등록 완료!");
				}else{
					
					alert("제작국가 등록 실패!");
				}
			}
		);
	}
</script>
<body>
	<div class='clear' style='height: 20px;'></div>
	<div id="content_section">
		<!-- 장르 -->
		<div id="movie_manufactured_country" class="registration_form_movie_manufactrued_country">
			<div style="font-weight: bold;">제작국가</div>
			<div class='clear' style='height: 10px;'></div>
			<select id="movie_manufactured_country_list"></select>
			<div class='clear' style='height: 20px;'></div>
		</div>
		<!-- 제작국가 이름 -->
		<div id="movie_manufactured_country_name" class="registration_form_movie_manufactrued_country">
			<div style="font-weight: bold;">제작국가이름</div>
			<div class='clear' style='height: 10px;'></div>
			<input id="input_movie_manufactured_country_name" class="input_component" type="text" placeHolder="제작국가이름"/>
			<div class='clear' style='height: 20px;'></div>
		</div>		
		<div id="registration_form" class="registration_form_movie_manufactrued_country">
			<div id="registration_button" class="input_component" onclick="checkInputData()">등록</div>
			<div class='clear' style='height: 20px;'></div>
		</div>
		
	</div>
</body>
</html>