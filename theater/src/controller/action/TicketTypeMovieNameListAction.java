package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReservationDao;
import dao.TheaterDao;
import model.ReservationInfo;
import model.Theater;

public class TicketTypeMovieNameListAction implements Action {

	private ReservationDao reservationDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String scheduleViewingDay = req.getParameter("schedule_viewing_day");
		String scheduleViewingStartTime = req.getParameter("schedule_viewing_start_time");
		String theaterName = req.getParameter("theater_name");
		String screenName = req.getParameter("screen_name");
		
		try {
			
			reservationDao = ReservationDao.getInstance();
			
			List<String> movieNameList = reservationDao.getPossibleMovieNameList(scheduleViewingDay, scheduleViewingStartTime, theaterName, screenName);
			
			req.setAttribute("movieNameList", movieNameList);

			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			for (int i = 0; i < movieNameList.size(); i++) {

				out.print("{\"item\":\"" + movieNameList.get(i) + "\"}");

				if (i == movieNameList.size() - 1)	break;
				out.print(",");
			}
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}