package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dao.ReservationDao;
import dao.TheaterDao;
import model.Movie;
import model.ReservationInfo;
import model.Theater;

public class OneMovieAction implements Action {

	private MovieDao movieDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String movieName = req.getParameter("movie_name");
		
		try {
			
			movieDao = MovieDao.getInstance();
			
			Movie movie = movieDao.getMovie(movieName);
			
			String movieMessage = "";
			
			for(int i=1; i<=12; i++){
				
				movieMessage += movie.getMovieName()+",";
				movieMessage += movie.getMovieRunningtime()+",";
				movieMessage += movie.getMovieSynopsis()+",";
				movieMessage += movie.getMovieDirector()+",";
				movieMessage += movie.getMovieActor()+",";
				movieMessage += movie.getMoviePlaydate()+",";
				movieMessage += movie.getMovieEnddate()+",";
				movieMessage += movie.getMoviePoster()+",";
				movieMessage += movie.getMovieTrailer()+",";
				movieMessage += movie.getMovieAgeLimit()+",";
				movieMessage += movie.getMovieGenreCode()+",";
				movieMessage += movie.getMovieManufacturedCountryCode()+",";
				movieMessage += movie.getMovieGenreCode()+",";	
			}
			
			System.out.println(movieMessage);
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + movieMessage + "\"}");
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}