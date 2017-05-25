package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;

public class MovieManufacturedCountryNameAllListAction implements Action{
	
	private MovieDao movieDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = MovieDao.getInstance();
		
		List<String> manufacturedCountryList;
		
		try {
			
			manufacturedCountryList = movieDao.getAllMovieManufacturedCountryList();
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			for (int i = 0; i < manufacturedCountryList.size(); i++) {
				
				out.print("{\"item\":\"" + manufacturedCountryList.get(i) + "\"}");

				if (i == manufacturedCountryList.size() - 1)	break;
				out.print(",");
			}
			
			out.print("]}");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
}