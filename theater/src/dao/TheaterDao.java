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
import model.Screen;
import model.Theater;
import provider.ConnectionProvider;

public class TheaterDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static TheaterDao instance;
	
	private TheaterDao(){}
	
	public static TheaterDao getInstance(){
		
		if(instance == null) instance = new TheaterDao();
		
		return instance;
	}
	
	public int insertTheater(Theater theater) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into theater values(0, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, theater.getTheaterName());
			pstmt.setString(2, theater.getTheaterLocation());
			pstmt.setInt(3, theater.getTheaterScreenCount());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateTheater(Theater theater) throws ClassNotFoundException, SQLException{
		
		String sql = "update theater set theater_name=?, theater_location=? where theater_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, theater.getTheaterName());
			pstmt.setString(2, theater.getTheaterLocation());
			pstmt.setInt(3, theater.getTheaterCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteTheater(Theater theater) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from theater where theater_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, theater.getTheaterCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<String> getAllTheaterList(){
		
		
		String sql = "select * from theater";
		
		List<String> theaterList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				theaterList.add(rs.getString("theater_name"));
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theaterList;
	}
	
	public int getTheaterCodeByTheaterName(String theaterName){
		
		
		String sql = "select theater_code from theater where theater_name = ?";

		int theaterCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				theaterCode = rs.getInt("theater_code");
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theaterCode;
	}
	
	public List<String> getAllPossibleTheaterList(String scheduleViewingDay){
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select th.theater_name from theater th, screen sc, schedule sch ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("group by th.theater_name ").
		append("order by th.theater_name asc");
		
		List<String> theaterList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				theaterList.add(rs.getString("theater_name"));
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theaterList;
	}
	
	public Theater getTheaterInfo(String theaterName) throws ClassNotFoundException, SQLException{
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select theater_name, theater_location from theater where theater_name=?");
		
		Theater theater = new Theater();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				theater.setTheaterName(rs.getString("theater_name"));
				theater.setTheaterLocation(rs.getString("theater_location"));
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theater;
	}
	
	public List<Screen> getScreenInfo(int theaterCode) throws ClassNotFoundException, SQLException{
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select screen_code, screen_name, screen_seat_count, theater_code from screen where theater_code=?");
		
		List<Screen> screenList = new ArrayList<Screen>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, theaterCode);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				screenList.add(new Screen(rs.getInt("screen_code"), rs.getString("screen_name"), rs.getInt("screen_seat_count"), rs.getInt("theater_code")));
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return screenList;
	}
	
	public int getScreenCode(String screenName, int theaterCode) throws ClassNotFoundException, SQLException{
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select screen_code from screen where screen_name=? and theater_code=?");
		
		int screenCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, screenName);
			pstmt.setInt(2, theaterCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				screenCode = rs.getInt("screen_code");
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return screenCode;
	}	
	
	public int getTheaterCode(String theaterName) throws ClassNotFoundException, SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select theater_code from theater where theater_name=?");
		
		int theaterCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, theaterName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				theaterCode = rs.getInt("theater_code");
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theaterCode;
	}
	
	public List<String> getTicketTypePossibleTheaterList(String scheduleViewingDay, String scheduleViewingStartTime){
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select th.theater_name from theater th, screen sc, schedule sch ").
		append("where sc.theater_code = th.theater_code ").
		append("and sch.screen_code = sc.screen_code ").
		append("and sch.schedule_viewing_day = ? ").
		append("and sch.schedule_viewing_start_time = ? ").
		append("group by th.theater_name ").
		append("order by th.theater_name asc");
		
		List<String> theaterList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, scheduleViewingDay);
			pstmt.setString(2, scheduleViewingStartTime);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				theaterList.add(rs.getString("theater_name"));
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return theaterList;
	}
	
	public int insertScreen(Screen screen) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into screen values(0, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, screen.getScreenName());
			pstmt.setInt(2, screen.getScreenSeatCount());
			pstmt.setInt(3, screen.getTheaterCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateScreen(Screen screen) throws ClassNotFoundException, SQLException{
		
		String sql = "update screen set screen_name=?, screen_seat_count=?, theater_code=? where screen_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, screen.getScreenName());
			pstmt.setInt(2, screen.getScreenSeatCount());
			pstmt.setInt(3, screen.getTheaterCode());
			pstmt.setInt(4, screen.getScreenCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteScreen(Screen screen) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from screen where screen_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, screen.getScreenCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
}