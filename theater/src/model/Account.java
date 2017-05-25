package model;

public class Account {
	
	private int accountCode;
	private String accountNumber;
	private int accountBalance;
	private int bankCode;
	private String customerId;
	public Account() {
		super();
	}
	public Account(int accountCode, String accountNumber, int accountBalance, int bankCode, String customerId) {
		super();
		this.accountCode = accountCode;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.bankCode = bankCode;
		this.customerId = customerId;
	}
	public int getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}