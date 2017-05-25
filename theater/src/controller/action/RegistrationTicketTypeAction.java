package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dao.ReservationDao;
import model.Schedule;
import model.Screen;
import model.TicketType;

public class RegistrationTicketTypeAction implements Action{
	
	private MovieDao movieDao;
	private ReservationDao reservationDao;
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = MovieDao.getInstance();
		reservationDao = ReservationDao.getInstance();
		
		String scheduleViewingDay = req.getParameter("schedule_viewing_day");
		String scheduleViewingStartTime = req.getParameter("schedule_viewing_start_time");
		String theaterName = req.getParameter("theater_name");
		String screenName = req.getParameter("screen_name");
		String movieName = req.getParameter("movie_name");
		String ticketType = req.getParameter("ticket_type");
		int ticketPrice = Integer.parseInt(req.getParameter("ticket_price"));
		
		try {
			
			String result = "false";
			
			int movieCode = movieDao.getMovieCode(movieName);
			
			int screenCode = reservationDao.getEachScreenCode(theaterName, screenName);
			
			int scheduleCode = reservationDao.getScheduleCode(scheduleViewingDay, scheduleViewingStartTime, screenCode, movieCode);
			
			int count = reservationDao.insertTicketType(new TicketType(0, ticketType, ticketPrice, scheduleCode));
			
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