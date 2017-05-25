package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	// �ڿ� ����� �͵� ����ó���ؼ� �ݾ��ָ� �ȴ�
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
		 * �����ͺ��̽��� ������ �����ϸ�
		 * �����Ͱ� ������ �Ǵµ� commit �۾� ������
		 * ��������� ����� �� �ִ� >> rollback
		 * 
		 * A=1 B=1�̶�� �۾��� ���� �� �� �� ���� ó���Ǿ�� �Ѵ�
		 * �̸� Ʈ�������̶�� �θ��� �ϳ��� ó������ ���� ������ ���� rollback �Ѵ�
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