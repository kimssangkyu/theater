package model;

public class Theater {
	
	private int theaterCode;
	private String theaterName;
	private String theaterLocation;
	private int theaterScreenCount;
	
	public Theater() {
		super();
	}

	public Theater(int theaterCode, String theaterName, String theaterLocation, int theaterScreenCount) {
		super();
		this.theaterCode = theaterCode;
		this.theaterName = theaterName;
		this.theaterLocation = theaterLocation;
		this.theaterScreenCount = theaterScreenCount;
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

	public String getTheaterLocation() {
		return theaterLocation;
	}

	public void setTheaterLocation(String theaterLocation) {
		this.theaterLocation = theaterLocation;
	}

	public int getTheaterScreenCount() {
		return theaterScreenCount;
	}

	public void setTheaterScreenCount(int theaterScreenCount) {
		this.theaterScreenCount = theaterScreenCount;
	}
}