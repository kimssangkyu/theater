package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	// 자원 사용한 것들 예외처리해서 닫아주면 된다
	public static void close(Connection conn){
		
		try {
			if(conn != null && !conn.isClosed()){
				
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		
		try {
			if(stmt != null && !stmt.isClosed()){
				
				stmt.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		
		try {
			if(rs != null && !rs.isClosed()){
				
				rs.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn){
		
		/*
		 * 데이터베이스에 쿼리를 실행하면
		 * 데이터가 변경이 되는데 commit 작업 전에는
		 * 변경사항을 취소할 수 있다 >> rollback
		 * 
		 * A=1 B=1이라는 작업이 있을 때 둘 다 같이 처리되어야 한다
		 * 이를 트랜젝션이라고 부르고 하나만 처리됐을 때는 나머지 값은 rollback 한다
		 * */
		try {
			
			if(conn != null && !conn.isClosed()){
				
				conn.rollback();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}