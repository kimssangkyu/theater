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

public class ModifyScreenAction implements Action{
	
	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		theaterDao = TheaterDao.getInstance();
		
		String originTheaterName = req.getParameter("origin_theater_name");
		String[] originScreenName = req.getParameter("origin_screen_name").split(",");
		String[] screenNameArr = req.getParameter("screen_name").split(",");
		String[] screenSeatCountStrArr = req.getParameter("screen_seat_count").split(",");
		int[] screenSeatCountArr = new int[screenSeatCountStrArr.length];
		int[] screenCodeArr = new int[screenSeatCountStrArr.length];
		
		for(int i=0; i<screenSeatCountStrArr.length; i++){
			
			screenSeatCountArr[i] = Integer.parseInt(screenSeatCountStrArr[i]);
		}
		
		int count = 0;
		
		try {
			
			String result = "false";
			
			int theaterCode = theaterDao.getTheaterCode(originTheaterName);
			
			for(int i=0; i<screenSeatCountStrArr.length; i++){
				
				screenCodeArr[i] = theaterDao.getScreenCode(originScreenName[i], theaterCode);
				count += theaterDao.updateScreen(new Screen(screenCodeArr[i], screenNameArr[i], Integer.parseInt(screenSeatCountStrArr[i]), theaterCode));
			}
			
			if(count == screenSeatCountStrArr.length){
				
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