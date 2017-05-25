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

public class EachScreenNameListAction implements Action {

	private ReservationDao reservationDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String theaterName = req.getParameter("theater_name");
		
		try {

			reservationDao = ReservationDao.getInstance();
			
			List<String> screenNameList = reservationDao.getEachScreenNameList(theaterName);
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			for (int i = 0; i < screenNameList.size(); i++) {

				out.print("{\"item\":\"" + screenNameList.get(i) + "\"}");

				if (i == screenNameList.size() - 1)	break;
				out.print(",");
			}
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}