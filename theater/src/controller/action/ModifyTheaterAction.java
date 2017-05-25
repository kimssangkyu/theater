package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dao.ReservationDao;
import dao.TheaterDao;
import model.Schedule;
import model.Screen;
import model.Theater;

public class ModifyTheaterAction implements Action{
	
	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		theaterDao = TheaterDao.getInstance();
		
		String originTheaterName = req.getParameter("origin_theater_name");
		String newTheaterName = req.getParameter("new_theater_name");
		String theaterLocation = req.getParameter("theater_location");
		
		try {
			
			String result = "false";
			
			int theaterCode = theaterDao.getTheaterCode(originTheaterName);
			
			int count = theaterDao.updateTheater(new Theater(theaterCode, newTheaterName, theaterLocation, 0));
			
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