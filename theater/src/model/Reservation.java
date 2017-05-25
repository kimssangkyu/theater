package model;

public class Reservation {
	
	private int reservationCode;
	private int seatNumber;
	private int ticketSalePrice;
	private String customerId;
	private int scheduleCode;
	
	public Reservation() {
		super();
	}

	public Reservation(int reservationCode, int seatNumber, int ticketSalePrice, String customerId, int scheduleCode) {
		super();
		this.reservationCode = reservationCode;
		this.seatNumber = seatNumber;
		this.ticketSalePrice = ticketSalePrice;
		this.customerId = customerId;
		this.scheduleCode = scheduleCode;
	}

	public int getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getTicketSalePrice() {
		return ticketSalePrice;
	}

	public void setTicketSalePrice(int ticketSalePrice) {
		this.ticketSalePrice = ticketSalePrice;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
}