package model;

public class Schedule {
	
	private int scheduleCode;
	private String scheduleViewingDay;
	private String scheduleViewingStartTime;
	private String scheduleViewingEndTime;
	private int screenCode;
	private int movieCode;
	
	public Schedule() {
		super();
	}

	public Schedule(int scheduleCode, String scheduleViewingDay, String scheduleViewingStartTime,
			String scheduleViewingEndTime, int screenCode, int movieCode) {
		super();
		this.scheduleCode = scheduleCode;
		this.scheduleViewingDay = scheduleViewingDay;
		this.scheduleViewingStartTime = scheduleViewingStartTime;
		this.scheduleViewingEndTime = scheduleViewingEndTime;
		this.screenCode = screenCode;
		this.movieCode = movieCode;
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

	public int getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(int screenCode) {
		this.screenCode = screenCode;
	}

	public int getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}
}