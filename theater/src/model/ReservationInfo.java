package model;

public class ReservationInfo {
	
	private int theaterCode;
	private String theaterName;
	private int screenCode;
	private String screenName;
	private int movieCode;
	private String movieName;
	private int scheduleCode;
	private String scheduleViewingDay;
	private String scheduleViewingStartTime;
	private String scheduleViewingEndTime;
	private String customerId;
	private int ticketCode;
	private int ticketSalePrice;
	private int reservationCode;
	
	public ReservationInfo() {
		super();
	}

	public ReservationInfo(int theaterCode, String theaterName, int screenCode, String screenName, int movieCode,
			String movieName, int scheduleCode, String scheduleViewingDay, String scheduleViewingStartTime,
			String scheduleViewingEndTime, String customerId, int ticketCode, int ticketSalePrice,
			int reservationCode) {
		super();
		this.theaterCode = theaterCode;
		this.theaterName = theaterName;
		this.screenCode = screenCode;
		this.screenName = screenName;
		this.movieCode = movieCode;
		this.movieName = movieName;
		this.scheduleCode = scheduleCode;
		this.scheduleViewingDay = scheduleViewingDay;
		this.scheduleViewingStartTime = scheduleViewingStartTime;
		this.scheduleViewingEndTime = scheduleViewingEndTime;
		this.customerId = customerId;
		this.ticketCode = ticketCode;
		this.ticketSalePrice = ticketSalePrice;
		this.reservationCode = reservationCode;
	}

	public int getTheaterCode() {
		return theaterCode;
	}

	public void setTheaterCode(int theaterCode) {
		this.theaterCode = theaterCode;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public int getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(int screenCode) {
		this.screenCode = screenCode;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getScheduleCode() {
		return scheduleCode;
	}

	public void setScheduleCode(int scheduleCode) {
		this.scheduleCode = scheduleCode;
	}

	public String getScheduleViewingDay() {
		return scheduleViewingDay;
	}

	public void setScheduleViewingDay(String scheduleViewingDay) {
		this.scheduleViewingDay = scheduleViewingDay;
	}

	public String getScheduleViewingStartTime() {
		return scheduleViewingStartTime;
	}

	public void setScheduleViewingStartTime(String scheduleViewingStartTime) {
		this.scheduleViewingStartTime = scheduleViewingStartTime;
	}

	public String getScheduleViewingEndTime() {
		return scheduleViewingEndTime;
	}

	public void setScheduleViewingEndTime(String scheduleViewingEndTime) {
		this.scheduleViewingEndTime = scheduleViewingEndTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public int getReservationCode() {
		return reservationCode;
	}

	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
}
