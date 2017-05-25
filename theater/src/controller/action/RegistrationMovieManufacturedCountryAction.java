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
import model.MovieManufacturedCountry;
import model.Screen;
import model.Theater;

public class RegistrationMovieManufacturedCountryAction implements Action {

	private MovieDao movieDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = MovieDao.getInstance();
		
		String movieManufacturedCountryName = req.getParameter("movie_manufactured_country_name");
		
		try {
			
			String result = "false";
			
			int count = movieDao.insertMovieManufacturedCountry(new MovieManufacturedCountry(0, movieManufacturedCountryName));
			
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