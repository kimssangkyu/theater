package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Bank;
import model.Board;
import model.BoardReply;
import model.Customer;
import model.Reservation;
import model.ReservationInfo;
import model.Schedule;
import model.Screen;
import model.Theater;
import model.Ticket;
import provider.ConnectionProvider;

public class BoardDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static BoardDao instance;
	
	private BoardDao(){}
	
	public static BoardDao getInstance(){
		
		if(instance == null) instance = new BoardDao();
		
		return instance;
	}
	
	public int insertBoard(Board board) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into board values(0, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardWriteDate());
			pstmt.setString(4, board.getCustomerId());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateBoard(Board board) throws ClassNotFoundException, SQLException{
		
		String sql = "update board set board_title=?, board_content=?, board_write_date=?, customer_id=? where board_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardWriteDate());
			pstmt.setString(4, board.getCustomerId());
			pstmt.setInt(5, board.getBoardCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteBoard(Board board) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from board where board_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getBoardCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertBoardReply(BoardReply boardReply) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into board_reply values(0, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardReply.getBoardReplyContent());
			pstmt.setString(2, boardReply.getCustomerId());
			pstmt.setInt(3, boardReply.getBoardCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateBoardReply(BoardReply boardReply) throws ClassNotFoundException, SQLException{
		
		String sql = "update board_reply set board_reply_content=?, customer_id=?, board_code=? where board_reply_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardReply.getBoardReplyContent());
			pstmt.setString(2, boardReply.getCustomerId());
			pstmt.setInt(3, boardReply.getBoardCode());
			pstmt.setInt(4, boardReply.getBoardReplyCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteBoardReply(BoardReply boardReply) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from board_reply where board_reply_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardReply.getBoardReplyCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
}