package model;

public class Screen {
	
	private int screenCode;
	private String screenName;
	private int screenSeatCount;
	private int theaterCode;
	
	public Screen() {
		super();
	}

	public Screen(int screenCode, String screenName, int screenSeatCount, int theaterCode) {
		super();
		this.screenCode = screenCode;
		this.screenName = screenName;
		this.screenSeatCount = screenSeatCount;
		this.theaterCode = theaterCode;
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

	public int getScreenSeatCount() {
		return screenSeatCount;
	}

	public void setScreenSeatCount(int screenSeatCount) {
		this.screenSeatCount = screenSeatCount;
	}

	public int getTheaterCode() {
		return theaterCode;
	}

	public void setTheaterCode(int theaterCode) {
		this.theaterCode = theaterCode;
	}
}