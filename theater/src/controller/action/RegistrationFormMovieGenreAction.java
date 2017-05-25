package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDao;
import model.Theater;

public class RegistrationFormMovieGenreAction implements Action{
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		
		String url = "admin/registrationFormMovieGenre.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}