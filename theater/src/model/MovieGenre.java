package model;

public class MovieGenre {
	
	private int movieGenreCode;
	private String movieGenreName;
	
	public MovieGenre() {
		super();
	}
	
	public MovieGenre(int movieGenreCode, String movieGenreName) {
		super();
		this.movieGenreCode = movieGenreCode;
		this.movieGenreName = movieGenreName;
	}
	public int getMovieGenreCode() {
		return movieGenreCode;
	}
	public void setMovieGenreCode(int movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}
	public String getMovieGenreName() {
		return movieGenreName;
	}
	public void setMovieGenreName(String movieGenreName) {
		this.movieGenreName = movieGenreName;
	}
}