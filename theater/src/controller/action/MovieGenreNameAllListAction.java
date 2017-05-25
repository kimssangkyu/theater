package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;

public class MovieGenreNameAllListAction implements Action{
	
	private MovieDao movieDao;
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		movieDao = MovieDao.getInstance();
		
		List<String> movieGenreList = movieDao.getAllMovieGenreList();
		
		PrintWriter out = resp.getWriter();
		
		out.print("{\"items\":[");
		
		for (int i = 0; i < movieGenreList.size(); i++) {
			
			out.print("{\"item\":\"" + movieGenreList.get(i) + "\"}");

			if (i == movieGenreList.size() - 1)	break;
			out.print(",");
		}
		
		out.print("]}");
	}
}