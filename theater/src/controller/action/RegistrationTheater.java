package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;
import model.Screen;
import model.Theater;

public class RegistrationTheater implements Action {

	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		theaterDao = TheaterDao.getInstance();
		
		String theaterName = req.getParameter("theater_name");
		String theaterLocation = req.getParameter("theater_location");
		int theaterScreenCount = Integer.parseInt(req.getParameter("theater_screen_count"));
		
		String screenNames = req.getParameter("screen_name");
		String screenSeatCounts = req.getParameter("screen_seat_count");
		
		String[] screenName = screenNames.split(",");
		String[] screenSeatCount = screenSeatCounts.split(",");
		
		try {
			
			String result = "false";
			
			int count = theaterDao.insertTheater(new Theater(0, theaterName, theaterLocation, theaterScreenCount));
			
			if(count > 0){
				
				int theaterCode = theaterDao.getTheaterCodeByTheaterName(theaterName);
				
				for(int i=0; i<screenName.length; i++){
					
					
					theaterDao.insertScreen(new Screen(0, screenName[i], Integer.parseInt(screenSeatCount[i]), theaterCode));
				}
				
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