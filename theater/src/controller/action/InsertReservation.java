package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dao.ReservationDao;
import dao.TheaterDao;
import model.Reservation;
import model.ReservationInfo;
import model.Theater;

public class InsertReservation implements Action {

	private ReservationDao reservationDao;
	private CustomerDao customerDao;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		int seatNumber = Integer.parseInt(req.getParameter("seat_number"));
		int ticketSalePrice = Integer.parseInt(req.getParameter("ticket_sale_price"));
		String customerId = req.getParameter("customer_id");
		int scheduleCode = Integer.parseInt(req.getParameter("schedule_code"));
		
		try {

			reservationDao = ReservationDao.getInstance();
			customerDao = CustomerDao.getInstance();
			
			int result = reservationDao.insertReservation(new Reservation(0, seatNumber, ticketSalePrice, customerId, scheduleCode));
			
			String msg = "false";
			
			if(result > 0){
				
				result = customerDao.deposit(ticketSalePrice, customerId);
				
				if(result > 0){
					

					msg = "true";	
				}
			}
			
			PrintWriter out = resp.getWriter();
			
			out.print("{\"items\":[");
			
			out.print("{\"item\":\"" + msg + "\"}");

			out.print("]}");
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}