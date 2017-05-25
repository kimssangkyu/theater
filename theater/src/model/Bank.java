package model;

public class Bank {
	
	private int bankCode;
	private String bankName;
	
	public Bank() {
		super();
	}
	public Bank(int bankCode, String bankName) {
		super();
		this.bankCode = bankCode;
		this.bankName = bankName;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}