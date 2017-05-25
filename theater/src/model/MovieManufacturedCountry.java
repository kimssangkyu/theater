package model;

public class MovieManufacturedCountry {
	
	private int movieManufacturedCountryCode;
	private String movieManufacturedCounryName;
	
	public MovieManufacturedCountry() {
		super();
	}
	
	public MovieManufacturedCountry(int movieManufacturedCountryCode, String movieManufacturedCounryName) {
		super();
		this.movieManufacturedCountryCode = movieManufacturedCountryCode;
		this.movieManufacturedCounryName = movieManufacturedCounryName;
	}

	public int getMovieManufacturedCountryCode() {
		return movieManufacturedCountryCode;
	}

	public void setMovieManufacturedCountryCode(int movieManufacturedCountryCode) {
		this.movieManufacturedCountryCode = movieManufacturedCountryCode;
	}

	public String getMovieManufacturedCounryName() {
		return movieManufacturedCounryName;
	}

	public void setMovieManufacturedCounryName(String movieManufacturedCounryName) {
		this.movieManufacturedCounryName = movieManufacturedCounryName;
	}


}