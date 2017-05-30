package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MovieManufacturedCountry;
import model.Movie;
import model.MovieGenre;
import model.Reservation;
import model.ReservationInfo;
import model.Screen;
import model.Theater;
import provider.ConnectionProvider;

public class MovieDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static MovieDao instance;
	
	private MovieDao(){}
	
	public static MovieDao getInstance(){
		
		if(instance == null) instance = new MovieDao();
		
		return instance;
	}
	
	public int insertMovie(Movie movie) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into movie values(0, ?, ?, ?, ?, ?, ?, null, ?, ?, ?, ?, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movie.getMovieName());
			pstmt.setInt(2, movie.getMovieRunningtime());
			pstmt.setString(3, movie.getMovieSynopsis());
			pstmt.setString(4, movie.getMovieDirector());
			pstmt.setString(5, movie.getMovieActor());
			pstmt.setString(6, movie.getMoviePlaydate());
			pstmt.setString(7, movie.getMoviePoster());
			pstmt.setString(8, movie.getMovieTrailer());
			pstmt.setInt(9, movie.getMovieAgeLimit());
			pstmt.setInt(10, movie.getMovieGenreCode());
			pstmt.setInt(11, movie.getMovieManufacturedCountryCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateMovie(Movie movie) throws ClassNotFoundException, SQLException{
		
		String sql = "update movie set movie_name=?, movie_runingime=?, movie_synopsis=?, movie_director=?, movie_actor=?, movie_playdate=?, movie_playdate=?, movie_poster=?, movie_trailer=?, movie_age_limit=? movie_genre_code=?, movie_manufactured_country_code=? whre movie_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movie.getMovieName());
			pstmt.setInt(2, movie.getMovieRunningtime());
			pstmt.setString(3, movie.getMovieSynopsis());
			pstmt.setString(4, movie.getMovieDirector());
			pstmt.setString(5, movie.getMovieActor());
			pstmt.setString(6, movie.getMoviePlaydate());
			pstmt.setString(7, movie.getMovieEnddate());
			pstmt.setString(8, movie.getMoviePoster());
			pstmt.setString(9, movie.getMovieTrailer());
			pstmt.setInt(10, movie.getMovieAgeLimit());
			pstmt.setInt(11, movie.getMovieGenreCode());
			pstmt.setInt(12, movie.getMovieManufacturedCountryCode());
			pstmt.setInt(13, movie.getMovieCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteMovie(Movie movie) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from movie where movie_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, movie.getMovieCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertMovieGenre(MovieGenre movieGenre) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into movie_genre values(0, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movieGenre.getMovieGenreName());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateMovieGenre(MovieGenre movieGenre) throws ClassNotFoundException, SQLException{
		
		String sql = "update movie_genre set movie_genre_name=? where movie_genre_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movieGenre.getMovieGenreName());
			pstmt.setInt(2, movieGenre.getMovieGenreCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteMovieGenre(MovieGenre movieGenre) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from movie_genre where movie_genre_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, movieGenre.getMovieGenreCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int insertMovieManufacturedCountry(MovieManufacturedCountry movieManufacturedCountry) throws ClassNotFoundException, SQLException{
		
		String sql = "insert into movie_manufactured_country values(0, ?)";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, movieManufacturedCountry.getMovieManufacturedCounryName());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int updateManufacturedCountry(MovieManufacturedCountry manufacturedCountry) throws ClassNotFoundException, SQLException{
		
		String sql = "update movie_manufactured_contry set movie_manufactured_country_name=? where movie_manufactured_country_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, manufacturedCountry.getMovieManufacturedCounryName());
			pstmt.setInt(2, manufacturedCountry.getMovieManufacturedCountryCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int deleteManufacturedCountry(MovieManufacturedCountry manufacturedCountry) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from movie_manufactured_country where movie_manufactured_country_code=?";
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, manufacturedCountry.getMovieManufacturedCountryCode());
			
			return pstmt.executeUpdate();
		}finally{
			
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<String> getAllMovieGenreList(){
		
		
		String sql = "select movie_genre_name from movie_genre order by movie_genre_name asc";
		
		List<String> movieGenreList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieGenreList.add(rs.getString("movie_genre_name"));
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieGenreList;
	}
	
	public int getMovieCode(String movieName){
		
		
		String sql = "select movie_code from movie where movie_name = ?";
		
		int movieGenreCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieGenreCode = rs.getInt("movie_code");
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieGenreCode;
	}
	
	public Movie getMovie(String movieName) throws ClassNotFoundException, SQLException{
		
		
		String sql = "select * from movie where movie_name = ?";
		
		Movie movie = new Movie();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movie.setMovieCode(rs.getInt("movie_code"));
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieRunningtime(rs.getInt("movie_runningtime"));
				movie.setMovieSynopsis(rs.getString("movie_synopsis"));
				movie.setMovieDirector(rs.getString("movie_director"));
				movie.setMovieActor(rs.getString("movie_actor"));
				movie.setMoviePlaydate(rs.getString("movie_playdate"));
				movie.setMovieEnddate(rs.getString("movie_enddate"));
				movie.setMoviePoster(rs.getString("movie_poster"));
				movie.setMovieTrailer(rs.getString("movie_trailer"));
				movie.setMovieAgeLimit(rs.getInt("movie_age_limit"));
				movie.setMovieGenreCode(rs.getInt("movie_genre_code"));
				movie.setMovieManufacturedCountryCode(rs.getInt("movie_manufactured_country_code"));
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movie;
	}
	
	public List<String> getAllMovieNameList() throws ClassNotFoundException, SQLException{
		
		
		String sql = "select movie_name from movie";
		
		List<String> movieNameList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieNameList.add(rs.getString("movie_name"));
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieNameList;
	}
	
	public int getMovieGenreCodeByMovieGenreName(String movieGenreName){
		
		
		String sql = "select movie_genre_code from movie_genre where movie_genre_name = ?";
		
		int movieGenreCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieGenreName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieGenreCode = rs.getInt("movie_genre_code");
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieGenreCode;
	}
	
	public int getMovieManufacturedCountryCodeByMovieManufacturedCountryName(String movieManufacturedCountryName){
		
		
		String sql = "select movie_manufactured_country_code from movie_manufactured_country where movie_manufactured_country_name = ?";
		
		int movieManufacturedCountryCode = 0;
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieManufacturedCountryName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieManufacturedCountryCode = rs.getInt("movie_manufactured_country_code");
			}
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		} catch (NullPointerException e){
			
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieManufacturedCountryCode;
	}	
	
	public List<String> getAllMovieManufacturedCountryList() throws ClassNotFoundException, SQLException{
				
		String sql = "select movie_manufactured_country_name from movie_manufactured_country order by movie_manufactured_country_name asc";
		
		List<String> manufacturedCountryList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				manufacturedCountryList.add(rs.getString("movie_manufactured_country_name"));
			}
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return manufacturedCountryList;
	}
	
	public List<String> getPremiereMovieNameList() throws ClassNotFoundException, SQLException{
		
		String sql = "select movie_name from movie order by movie_name";
		
		List<String> movieNameList = new ArrayList<>();
		
		try{
			
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				movieNameList.add(rs.getString("movie_name"));
			}
			
			return movieNameList;
		}finally{
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	/*
	 * 현재 상영작 조회
	 * */
	public List<Movie> getMovieList(String date){
		String sql = " Select * From movie Where movie_playdate <= ? And movie_enddate >= ? ";
		List<Movie> movieList = new ArrayList<Movie>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				// 결과값 있으면 저장
				Movie movie = new Movie();
				
				movie.setMovieCode(rs.getInt("movie_code"));
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieRunningtime(rs.getInt("movie_runningtime"));
				movie.setMovieSynopsis(rs.getString("movie_synopsis"));
				movie.setMovieDirector(rs.getString("movie_director"));
				movie.setMovieActor(rs.getString("movie_actor"));
				movie.setMoviePlaydate(rs.getString("movie_playdate"));
				movie.setMovieEnddate(rs.getString("movie_enddate"));
				movie.setMoviePoster(rs.getString("movie_poster"));
				movie.setMovieTrailer(rs.getString("movie_trailer"));
				movie.setMovieAgeLimit(rs.getInt("movie_age_limit"));
				movie.setMovieGenreCode(rs.getInt("movie_genre_code"));
				movie.setMovieManufacturedCountryCode(rs.getInt("movie_manufactured_country_code"));
				
				movieList.add(movie);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return movieList;
	}
	
}