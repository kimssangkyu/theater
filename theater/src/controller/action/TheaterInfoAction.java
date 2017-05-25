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

public class TheaterInfoAction implements Action {

	private TheaterDao theaterDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String theaterName = req.getParameter("theater_name");
		
		try {

			theaterDao = TheaterDao.getInstance();
			
			Theater theater = theaterDao.getTheaterInfo(theaterName);
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + theater.getTheaterName() + "\"},");
			out.print("{\"item\":\"" + theater.getTheaterLocation() + "\"}");
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}