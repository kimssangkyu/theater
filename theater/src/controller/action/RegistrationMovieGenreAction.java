package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dao.TheaterDao;
import model.MovieGenre;
import model.Screen;
import model.Theater;

public class RegistrationMovieGenreAction implements Action {

	private MovieDao movieDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = MovieDao.getInstance();
		
		String movieGenreName = req.getParameter("movie_genre_name");
		
		try {
			
			String result = "false";
			
			int count = movieDao.insertMovieGenre(new MovieGenre(0, movieGenreName));
			
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