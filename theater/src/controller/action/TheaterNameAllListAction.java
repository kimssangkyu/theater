package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;

public class TheaterNameAllListAction implements Action{
	
	private TheaterDao theaterDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		theaterDao = TheaterDao.getInstance();
		
		List<String> theaterList = theaterDao.getAllTheaterList();
		
		PrintWriter out = resp.getWriter();
		
		out.print("{\"items\":[");
		
		for (int i = 0; i < theaterList.size(); i++) {
			
			out.print("{\"item\":\"" + theaterList.get(i) + "\"}");

			if (i == theaterList.size() - 1)	break;
			out.print(",");
		}
		
		out.print("]}");
	}
}