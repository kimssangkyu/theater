package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dao.TheaterDao;
import model.Movie;
import model.Screen;
import model.Theater;

public class RegistrationMovieAction implements Action {

	private MovieDao movieDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = movieDao.getInstance();
		
		String movieName = req.getParameter("movie_name");
		int movieRuningtime = Integer.parseInt(req.getParameter("movie_runningtime"));
		String movieSynopsis = req.getParameter("movie_synopsis");
		String movieDirector = req.getParameter("movie_director");
		String movieActor = req.getParameter("movie_actor");
		String moviePlaydate = req.getParameter("movie_playdate");
		String moviePoster = req.getParameter("movie_poster");
		String movieTrailer = req.getParameter("movie_trailer");
		String movieAgeLimitStr = req.getParameter("movie_age_limit");
		
		int movieAgeLimit = 0;
		
		if(movieAgeLimitStr.equals("19세 미만 관람불가")){
			
			movieAgeLimit = 19;
		}else if(movieAgeLimitStr.equals("15세 미만 관람불가")){
			
			movieAgeLimit = 15;
		}else if(movieAgeLimitStr.equals("12세 미만 관람불가")){
			
			movieAgeLimit = 12;
		}if(movieAgeLimitStr.equals("전체 관람가")){
			
			movieAgeLimit = 0;
		}
		
		String movieGenreName = req.getParameter("movie_genre");
		String movieManufacturedCountryName = req.getParameter("movie_manufactured_country");
		
		try {
			
			String result = "false";
			
			int movieGenreCode = movieDao.getMovieGenreCodeByMovieGenreName(movieGenreName);
			int movieManufacturedCountryCode = movieDao.getMovieManufacturedCountryCodeByMovieManufacturedCountryName(movieManufacturedCountryName);
			
			int count = movieDao.insertMovie(new Movie(0, movieName, movieRuningtime, movieSynopsis, movieDirector, movieActor, moviePlaydate, null, moviePoster, movieTrailer, movieAgeLimit, movieGenreCode, movieManufacturedCountryCode));
			
			if(count > 0){
				
				result = "true";
			}
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + result + "\"}");
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
}