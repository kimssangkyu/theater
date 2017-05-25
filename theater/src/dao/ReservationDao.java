package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;
import model.ReservationInfo;
import model.Schedule;
import model.Screen;
import model.Theater;
import model.Ticket;
import model.TicketType;
import provider.ConnectionProvider;

public class ReservationDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static ReservationDao instance;
	
	private ReservationDao(){}
	
	public static ReservationDao getInstance(){
		
		if(instance == null) instance = new ReservationDao();
		
		return instance;
	}
	
	public List<String> getAllPossibleViewingDayList() throws ClassNotFoundException, SQLException{
		
		String sql = "select schedule_viewing_day from schedule where schedule_viewing_day >= DATE_FORMAT(now(),'%Y-%m-%d') group by schedule_viewing_day order by schedule_viewing_day asc";
		
		List<String> scheduleViewingDayList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				scheduleViewingDayList.add(rs.getString("schedule_viewing_day"));
			}
			return scheduleViewingDayList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getAllPossibleMovieNameList(String scheduleViewingDay, String theaterName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select mo.movie_name from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").		
		append("group by mo.movie_name ").
		append("order by mo.movie_name asc");
		
		List<String> movieNameList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieNameList.add(rs.getString("movie_name"));
			}
			return movieNameList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getPossibleMovieNameList(String scheduleViewingDay, String scheduleViewingStartTime, String theaterName, String screenName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select mo.movie_name from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and th.theater_name = ? ").
		append("and sc.screen_name = ? ").	
		append("group by mo.movie_name ").
		append("order by mo.movie_name asc");
		
		List<String> movieNameList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, scheduleViewingStartTime);
			pstmt.setString(3, theaterName);
			pstmt.setString(4, screenName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieNameList.add(rs.getString("movie_name"));
			}
			return movieNameList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getAllPossibleViewingStartTimeList(String scheduleViewingDay, String theaterName, String movieName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sch.schedule_viewing_start_time from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("group by sch.schedule_viewing_start_time ").
		append("order by sch.schedule_viewing_start_time asc");
		
		List<String> scheduleViewingStartTimeList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				scheduleViewingStartTimeList.add(rs.getString("schedule_viewing_start_time"));
			}
			return scheduleViewingStartTimeList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getPossibleTicketTypeViewingStartTimeList(String scheduleViewingDay) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sch.schedule_viewing_start_time from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("group by sch.schedule_viewing_start_time ").
		append("order by sch.schedule_viewing_start_time asc");
		
		List<String> scheduleViewingStartTimeList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				scheduleViewingStartTimeList.add(rs.getString("schedule_viewing_start_time"));
			}
			return scheduleViewingStartTimeList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getTicketTypeList(String scheduleViewingDay, String scheduleViewingStartTime, String theaterName, String screenName, String movieName ) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select ti.ticket_type ").
		append("from theater th, screen sc, schedule sch, movie mo, ticket_type ti ").
		append("where sc.theater_code = th.theater_code  ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").
		append("and ti.schedule_code = sch.schedule_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and th.theater_name = ? ").
		append("and sc.screen_name = ? ").
		append("and mo.movie_name = ? ");
		
		List<String> ticketTypeList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, scheduleViewingStartTime);
			pstmt.setString(3, theaterName);
			pstmt.setString(4, screenName);
			pstmt.setString(5, movieName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ticketTypeList.add(rs.getString("ticket_type"));
			}
			return ticketTypeList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getAllPossibleScreenNameList(String scheduleViewingDay, String theaterName, String movieName, String scheduleViewingStartTime) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sc.screen_name from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("group by sc.screen_name ").
		append("order by sc.screen_name asc");
		
		List<String> screenNameList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			pstmt.setString(4, scheduleViewingStartTime);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				screenNameList.add(rs.getString("screen_name"));
			}
			return screenNameList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getTicketTypePossibleScreenList(String scheduleViewingDay, String scheduleViewingStartTime, String theaterName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sc.screen_name from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and th.theater_name = ? ").
		append("group by sch.schedule_viewing_start_time ").
		append("order by sch.schedule_viewing_start_time asc");
		
		List<String> scheduleViewingStartTimeList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, scheduleViewingStartTime);
			pstmt.setString(3, theaterName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				scheduleViewingStartTimeList.add(rs.getString("screen_name"));
			}
			return scheduleViewingStartTimeList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getEachScreenNameList(String theaterName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select screen_name from theater th, screen sc where sc.theater_code = th.theater_code and th.theater_name = ?");
		
		List<String> screenNameList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				screenNameList.add(rs.getString("screen_name"));
			}
			return screenNameList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public int getEachScreenCode(String theaterName, String screenName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sc.screen_code from theater th, screen sc where sc.theater_code = th.theater_code and th.theater_name = ? and sc.screen_name = ?");
		
		int screenCode = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			pstmt.setString(2, screenName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				screenCode = rs.getInt("screen_code");
			}
			return screenCode;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public int getAllPossibleScheduleCode(String scheduleViewingDay, String theaterName, String movieName, String scheduleViewingStartTime) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sch.schedule_code from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").		
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("group by sc.screen_name ").
		append("order by sc.screen_name asc");
		
		int scheduleCode = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			pstmt.setString(4, scheduleViewingStartTime);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				scheduleCode = rs.getInt("schedule_code");
			}
			return scheduleCode;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public String getPossibleScreenSeatCount(String scheduleViewingDay, String theaterName, String movieName, String scheduleViewingStartTime, String screenName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sc.screen_seat_count from theater th, screen sc, schedule sch, movie mo ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and sc.screen_name = ? ");
		
		String screenSeatCount = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			pstmt.setString(4, scheduleViewingStartTime);
			pstmt.setString(5, screenName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				screenSeatCount = rs.getString("screen_seat_count");
			}
			
			return screenSeatCount;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public List<String> getPossibleSeatList(String scheduleViewingDay, String theaterName, String movieName, String scheduleViewingStartTime, String screenName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select re.seat_number from theater th, screen sc, schedule sch, movie mo, reservation re, ticket_type ti ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").
		append("and sch.schedule_code = ti.schedule_code ").
		append("and re.schedule_code = sch.schedule_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and sc.screen_name = ? ").
		append("group by re.seat_number ").
		append("order by re.seat_number asc ");
				
		List<String> seatList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			pstmt.setString(4, scheduleViewingStartTime);
			pstmt.setString(5, screenName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				seatList.add(rs.getString("seat_number"));
			}
			
			return seatList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}	
	
	public List<TicketType> getTicketPrice(String scheduleViewingDay, String theaterName, String movieName, String scheduleViewingStartTime, String screenName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select first.ticket_type, first.ticket_price ").
		append("from ticket_type first ").
		append("where first.schedule_code = ").
		append("(select sch.schedule_code from theater th, screen sc, schedule sch, movie mo, ticket_type ti ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.movie_code = mo.movie_code ").
		append("and sch.schedule_code = ti.schedule_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("and th.theater_name = ? ").
		append("and mo.movie_name = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("and sc.screen_name = ? ").
		append("group by sch.schedule_code) ");
		
		List<TicketType> ticketTypeList = new ArrayList<>();
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, theaterName);
			pstmt.setString(3, movieName);
			pstmt.setString(4, scheduleViewingStartTime);
			pstmt.setString(5, screenName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ticketTypeList.add(new TicketType(0, rs.getString("ticket_type"), rs.getInt("ticket_price"), 0));
			}
			
			return ticketTypeList;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	public int insertSchedule(Schedule schedule) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into schedule values(0, ?, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, schedule.getScheduleViewingDay());
			pstmt.setString(2, schedule.getScheduleViewingStartTime());
			pstmt.setString(3, schedule.getScheduleViewingEndTime());
			pstmt.setInt(4, schedule.getScreenCode());
			pstmt.setInt(5, schedule.getMovieCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateSchedule(Schedule schedule) throws ClassNotFoundException, SQLException{
		
		String sql = "update schedule set schedule_viewing_day=?, schedule_viewing_start_time=?, schedule_viewing_end_time=?, schedule_screen_code=?, movie_code=? where schedule_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, schedule.getScheduleViewingDay());
			pstmt.setString(2, schedule.getScheduleViewingStartTime());
			pstmt.setString(3, schedule.getScheduleViewingEndTime());
			pstmt.setInt(4, schedule.getScreenCode());
			pstmt.setInt(5, schedule.getMovieCode());
			pstmt.setInt(6, schedule.getScheduleCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
	
	public int deleteSchedule(Schedule schedule) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from schedule where schedule_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, schedule.getScheduleViewingDay());
			pstmt.setString(2, schedule.getScheduleViewingStartTime());
			pstmt.setString(3, schedule.getScheduleViewingEndTime());
			pstmt.setInt(4, schedule.getScreenCode());
			pstmt.setInt(5, schedule.getMovieCode());
			pstmt.setInt(6, schedule.getScheduleCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
	
	public int getScheduleCode(String scheduleViewingDay, String scheduleViewingStartTime, int screenCode, int movieCode) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		int scheduleCode = 0;
		
		sql.append("select schedule_code ").
		append("from schedule ").
		append("where schedule_viewing_day=? ").
		append("and schedule_viewing_start_time=? ").
		append("and screen_code=? ").
		append("and movie_code=? ");
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, scheduleViewingStartTime);
			pstmt.setInt(3, screenCode);
			pstmt.setInt(4, movieCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				scheduleCode = rs.getInt("schedule_code");
			}
			
			return scheduleCode;
			
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
	
	public int insertTicketType(TicketType ticketType) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into ticket_type values(0, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ticketType.getTicketType());
			pstmt.setInt(2, ticketType.getTicketPrice());
			pstmt.setInt(3, ticketType.getScheduleCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateTicketType(TicketType ticketType) throws ClassNotFoundException, SQLException{
		
		String sql = "update ticket_type set ticket_type=?, ticket_price=?, schedule_code=? where ticket_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ticketType.getTicketType());
			pstmt.setInt(2, ticketType.getTicketPrice());
			pstmt.setInt(3, ticketType.getScheduleCode());
			pstmt.setInt(4, ticketType.getTicketTypecode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteTicketType(TicketType ticketType) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from ticket_type where ticket_type_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ticketType.getTicketTypecode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertReservation(Reservation reservation) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into reservation values(0, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reservation.getSeatNumber());
			pstmt.setInt(2, reservation.getTicketSalePrice());
			pstmt.setString(3, reservation.getCustomerId());
			pstmt.setInt(4, reservation.getScheduleCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException{
		
		String sql = "update reservation set seat_number=?, ticket_sale_price=?, customer_id=?, schedule_code=? where reservation_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reservation.getSeatNumber());
			pstmt.setInt(2, reservation.getTicketSalePrice());
			pstmt.setString(3, reservation.getCustomerId());
			pstmt.setInt(4, reservation.getScheduleCode());
			pstmt.setInt(5, reservation.getReservationCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteReservation(Reservation reservation) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from reservation where reservation_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reservation.getReservationCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
}