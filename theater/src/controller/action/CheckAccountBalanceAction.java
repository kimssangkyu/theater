package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;

public class CheckAccountBalanceAction implements Action {

	private CustomerDao customerDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		int ticketSalePrice = Integer.parseInt(req.getParameter("ticket_sale_price"));
		String customerId = req.getParameter("customer_id");
		
		String result = "false";
		
		try {
			
			customerDao = CustomerDao.getInstance();
			
			int accountBalance = customerDao.selectAccountBalance(customerId);
			
			
			if(ticketSalePrice <= accountBalance){
				
				result = "true";
			}
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + result + "\"}");
			
			out.print(",");
			
			out.print("]}");
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}