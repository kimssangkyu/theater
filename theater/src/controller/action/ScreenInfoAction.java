package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;
import model.Screen;

public class ScreenInfoAction implements Action {

	private TheaterDao theaterDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		String theaterName = req.getParameter("theater_name");
		
		try {

			theaterDao = TheaterDao.getInstance();
			
			int theaterCode = theaterDao.getTheaterCode(theaterName);
			
			List<Screen> screenList = theaterDao.getScreenInfo(theaterCode);
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			for (int i = 0; i < screenList.size(); i++) {
				
				out.print("{\"item\":\"" + screenList.get(i).getScreenName()+","+screenList.get(i).getScreenSeatCount() + "\"}");

				if (i == screenList.size() - 1)	break;
				out.print(",");
			}
			
			out.print("]}");
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}