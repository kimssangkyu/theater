package model;

public class Movie {
	
	private int movieCode;
	private String movieName;
	private int movieRunningtime;
	private String movieSynopsis;
	private String movieDirector;
	private String movieActor;
	private String moviePlaydate;
	private String movieEnddate;
	private String moviePoster;
	private String movieTrailer;
	private int movieAgeLimit;
	private int movieGenreCode;
	private int movieManufacturedCountryCode;
	
	public Movie() {
		super();
	}

	public Movie(int movieCode, String movieName, int movieRunningtime, String movieSynopsis, String movieDirector,
			String movieActor, String moviePlaydate, String movieEnddate, String moviePoster, String movieTrailer,
			int movieAgeLimit, int movieGenreCode, int movieManufacturedCountryCode) {
		super();
		this.movieCode = movieCode;
		this.movieName = movieName;
		this.movieRunningtime = movieRunningtime;
		this.movieSynopsis = movieSynopsis;
		this.movieDirector = movieDirector;
		this.movieActor = movieActor;
		this.moviePlaydate = moviePlaydate;
		this.movieEnddate = movieEnddate;
		this.moviePoster = moviePoster;
		this.movieTrailer = movieTrailer;
		this.movieAgeLimit = movieAgeLimit;
		this.movieGenreCode = movieGenreCode;
		this.movieManufacturedCountryCode = movieManufacturedCountryCode;
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

	public int getMovieRunningtime() {
		return movieRunningtime;
	}

	public void setMovieRunningtime(int movieRunningtime) {
		this.movieRunningtime = movieRunningtime;
	}

	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}

	public String getMoviePlaydate() {
		return moviePlaydate;
	}

	public void setMoviePlaydate(String moviePlaydate) {
		this.moviePlaydate = moviePlaydate;
	}

	public String getMovieEnddate() {
		return movieEnddate;
	}

	public void setMovieEnddate(String movieEnddate) {
		this.movieEnddate = movieEnddate;
	}

	public String getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String getMovieTrailer() {
		return movieTrailer;
	}

	public void setMovieTrailer(String movieTrailer) {
		this.movieTrailer = movieTrailer;
	}

	public int getMovieAgeLimit() {
		return movieAgeLimit;
	}

	public void setMovieAgeLimit(int movieAgeLimit) {
		this.movieAgeLimit = movieAgeLimit;
	}

	public int getMovieGenreCode() {
		return movieGenreCode;
	}

	public void setMovieGenreCode(int movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}

	public int getMovieManufacturedCountryCode() {
		return movieManufacturedCountryCode;
	}

	public void setMovieManufacturedCountryCode(int movieManufacturedCountryCode) {
		this.movieManufacturedCountryCode = movieManufacturedCountryCode;
	}
}