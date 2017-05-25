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
import model.Customer;
import model.Reservation;
import model.ReservationInfo;
import model.Schedule;
import model.Screen;
import model.Theater;
import model.Ticket;
import provider.ConnectionProvider;

public class CustomerDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static CustomerDao instance;
	
	private CustomerDao(){}
	
	public static CustomerDao getInstance(){
		
		if(instance == null) instance = new CustomerDao();
		
		return instance;
	}
	
	public int insertCustomer(Customer customer) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into customer values(0, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, customer.getCustomerPassword());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getCustomerAddress());
			pstmt.setString(4, customer.getCustomerEmail());
			pstmt.setString(5, customer.getCustomerPhone());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
		
		String sql = "update customer set customer_password=?, customer_name=?, customer_address=?, customer_email=?, customer_phone=? where customer_id=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, customer.getCustomerPassword());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getCustomerAddress());
			pstmt.setString(4, customer.getCustomerEmail());
			pstmt.setString(5, customer.getCustomerPhone());
			pstmt.setString(6, customer.getCustomerId());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteCustomer(Customer customer) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from customer where customer_id=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, customer.getCustomerId());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}	
	
	public int insertAccount(Account account) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into account values(0, 0, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, account.getAccountNumber());
			pstmt.setInt(2, account.getAccountBalance());
			pstmt.setInt(3, account.getBankCode());
			pstmt.setString(4, account.getCustomerId());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deposit(int ticketSalePrice, String customerId) throws ClassNotFoundException, SQLException{
		
		String sql = "update account set account_balance = account_balance - ? where customer_id=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ticketSalePrice);
			pstmt.setString(2, customerId);
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int withdraw(int withdrawPrice, String customerId) throws ClassNotFoundException, SQLException{
		
		String sql = "update account set account_balance = account_balance + ? where customer_id=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, withdrawPrice);
			pstmt.setString(2, customerId);
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateAccountNumber(int accountCode) throws ClassNotFoundException, SQLException{
		
		String sql = "update account set account_number=lpad(account_code, 10, '0')  where account_code=? ";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, accountCode);
			pstmt.setInt(2, accountCode);
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteAccount(Account account) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from acocunt where account_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, account.getAccountCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertBank(Bank bank) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into bank values(0, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bank.getBankName());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateBank(Bank bank) throws ClassNotFoundException, SQLException{
		
		String sql = "update bank set bank_name=? where bank_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bank.getBankName());
			pstmt.setInt(2, bank.getBankCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteBank(Bank bank) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from bank where bank_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bank.getBankCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectAccountBalance(String customerId) throws ClassNotFoundException, SQLException{
		
		String sql = "select account_balance from account where customer_id=? ";
		
		int accountBalance = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, customerId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				accountBalance = rs.getInt("account_balance");
			}
			return accountBalance;
		} finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}	
}