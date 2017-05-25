package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;
import dao.TheaterDao;
import model.ReservationInfo;
import model.Theater;
import model.TicketType;

public class ScreenSeatCountAction implements Action {

	private ReservationDao reservationDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String scheduleViewingDay = req.getParameter("schedule_viewing_day");
		String theaterName = req.getParameter("theater_name");
		String movieName = req.getParameter("movie_name");
		String scheduleViewingStartTime = req.getParameter("schedule_viewing_start_time");
		String screenName = req.getParameter("screen_name");
		
		try {

			reservationDao = ReservationDao.getInstance();
			
			String screenSeatCount = reservationDao.getPossibleScreenSeatCount(scheduleViewingDay, theaterName, movieName, scheduleViewingStartTime, screenName);
			
			List<String> seatList = reservationDao.getPossibleSeatList(scheduleViewingDay, theaterName, movieName, scheduleViewingStartTime, screenName);
			
			List<TicketType> ticketTypeList = reservationDao.getTicketPrice(scheduleViewingDay, theaterName, movieName, scheduleViewingStartTime, screenName);
			
			String[] listArr = new String[Integer.parseInt(screenSeatCount)];
				
			for(int i=0; i<listArr.length; i++){
				
				listArr[i] = "false#";
			}
			
			for(int i=0; i<Integer.parseInt(screenSeatCount); i++){
				
				for(int j=0; j<seatList.size(); j++){
					
					if((i+1)==Integer.parseInt(seatList.get(j))){
						
						listArr[i] = "true#";
						break;
					}
				}
			}
			
			for(int i=0; i<Integer.parseInt(screenSeatCount); i++){
				
				if((i+1) <= 20){
					
					listArr[i] += ticketTypeList.get(0).getTicketType() + "#" + ticketTypeList.get(0).getTicketPrice();
				}else if((i+1) <= 40){
					
					listArr[i] += ticketTypeList.get(1).getTicketType() + "#" + ticketTypeList.get(1).getTicketPrice();
				}else{
					
					listArr[i] += ticketTypeList.get(2).getTicketType() + "#" + ticketTypeList.get(2).getTicketPrice();
				}
			}
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + screenSeatCount + "\"},");
			
			for (int i = 0; i < listArr.length; i++) {

				out.print("{\"item\":\"" + listArr[i] + "\"}");

				if (i == listArr.length - 1)	break;
				out.print(",");
			}
			
			out.print("]}");
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}