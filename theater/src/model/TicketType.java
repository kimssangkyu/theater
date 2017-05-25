package model;

public class TicketType {
	
	private int ticketTypecode;
	private String ticketType;
	private int ticketPrice;
	private int scheduleCode;
	
	public TicketType() {
		super();
	}

	public TicketType(int ticketTypecode, String ticketType, int ticketPrice, int scheduleCode) {
		super();
		this.ticketTypecode = ticketTypecode;
		this.ticketType = ticketType;
		this.ticketPrice = ticketPrice;
		this.scheduleCode = scheduleCode;
	}

	public int getTicketTypecode() {
		return ticketTypecode;
	}

	public void setTicketTypecode(int ticketTypecode) {
		this.ticketTypecode = ticketTypecode;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
}