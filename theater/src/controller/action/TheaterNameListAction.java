package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;

public class TheaterNameListAction implements Action{
	
	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		theaterDao = TheaterDao.getInstance();

		String scheduleViewingDay = req.getParameter("schedule_viewing_day");
		
		List<String> TheaterList = theaterDao.getAllPossibleTheaterList(scheduleViewingDay);
		
		PrintWriter out = resp.getWriter();
		
		out.print("{\"items\":[");
		
		for (int i = 0; i < TheaterList.size(); i++) {
			
			out.print("{\"item\":\"" + TheaterList.get(i) + "\"}");

			if (i == TheaterList.size() - 1)	break;
			out.print(",");
		}
		
		out.print("]}");
	}
}