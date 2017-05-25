package model;

public class Ticket {
	
	private int ticketCode;
	private int ticketSalePrice;
	private int ticketSeatNumber;
	private int scheduleCode;
	
	public Ticket() {
		super();
	}

	public Ticket(int ticketCode, int ticketSalePrice, int ticketSeatNumber, int scheduleCode) {
		super();
		this.ticketCode = ticketCode;
		this.ticketSalePrice = ticketSalePrice;
		this.ticketSeatNumber = ticketSeatNumber;
		this.scheduleCode = scheduleCode;
	}

	public int getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(int ticketCode) {
		this.ticketCode = ticketCode;
	}

	public int getTicketSalePrice() {
		return ticketSalePrice;
	}

	public void setTicketSalePrice(int ticketSalePrice) {
		this.ticketSalePrice = ticketSalePrice;
	}

	public int getTicketSeatNumber() {
		return ticketSeatNumber;
	}

	public void setTicketSeatNumber(int ticketSeatNumber) {
		this.ticketSeatNumber = ticketSeatNumber;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
}