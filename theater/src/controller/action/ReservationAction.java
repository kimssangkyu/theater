package controller.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;
import model.Theater;

public class ReservationAction implements Action{
	
	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String url = "reservation/reservation.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}