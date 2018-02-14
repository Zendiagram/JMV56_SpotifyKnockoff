/**Provides methods for adding and removing albums from the database
 * associating songs with albums
 * 
 * @author James Van Poolen
 * @version 1.0
 */


package jmv56_SpotifyKnockoff;


import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Album {
	
	//private vars for Album objects
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	Map<String,Song> albumSongs;
	
	//add a new Album object into the database using JDBC
	/**
	 * Constructor - creates a new ablum object and adds it to the database
	 * @param title
	 * @param releaseDate
	 * @param recordingCompany
	 * @param numberOfTracks
	 * @param pmrcRating - parental rating
	 * @param length - total length of the album
	 */
	
	public Album (String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString();  //randomly generated identifier
		
		albumSongs = new Hashtable<String, Song>();
		
		//build query and store it in the sql variable
		String sql = "INSERT INTO album (album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating,length) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		//connect to the database and run the insert query
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2,  this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, "");  //we will populate this path once we know where we are storing the album images, using a setter
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks);
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
			ps.executeUpdate();
			db.closeDbConnection();  //close that expensive connectoin!
			db = null;
		} catch (SQLException e) {
			
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	/**
	 * Constructor - creates a new object using the information that already exists in the database.
	 * @param albumID
	 */
	public Album(String albumID) {
		
		albumSongs = new Hashtable<String, Song>();
		
		//create the query and store to var sql. Maybe change to prepared statement?
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		
		DbUtilities db = new DbUtilities();
		
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {  //retreive the listed attributes of the Album from the database and store them as private variables in the created Album object
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();  //cast the date to a String
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	//deletes the album from the database
	/**
	 * Deletes an album from the database. Removes records from album_songs and albums
	 * @param albumID
	 */
	public void deleteAlbum(String albumID) {
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
		//created query and store to sql.  Using a prepared statement.
		String sql = "DELETE FROM album_song WHERE fk_album_id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
		sql = "DELETE FROM album WHERE album_id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, albumID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	//add Songs to the hashtable for the Album object by songID
	/**
	 * Associate a song with an album. Used to create the list of songs on an album
	 * populates the album_song table 
	 * @param songID
	 */
	public void addSong(String... songID) {  //allows for multiple songIDs to be passed in
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (String v : songID) {
					Song a = new Song(v);
					this.albumSongs.put(v, a);
				
					String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) VALUES (?,?);";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, this.getAlbumID());
					ps.setString(2, v);
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
		
		db.closeDbConnection();
		db = null;
										
	}
	
	//deletes song from the albumSongs hashtable by songID
	/**
	 * Remove a song album relationship using songID
	 * @param songID
	 */
	public void deleteSong(String... songID) {
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (String v : songID) {
					this.albumSongs.remove(v);
				
				
					String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, this.getAlbumID());
					ps.setString(2, v);
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
				
		
		db.closeDbConnection();
		db = null;			
	}

	//deletes song from the albumSongs hashtable by Song object
	/**
	 * Removes album to song relationship using song object
	 * @param song
	 */
	public void deleteSong(Song... song) {
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (Song v : song) {
					this.albumSongs.remove(v.getSongID());
				
				
					String sql = "DELETE FROM album_song WHERE fk_album_id = ? AND fk_song_id = ?;";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, this.getAlbumID());
					ps.setString(2, v.getSongID());
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
				
		
		db.closeDbConnection();
		db = null;		
	}

//getters
	public String getAlbumID() {
		return albumID;
	}

	public String getTitle() {
		return title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public double getLength() {
		return length;
	}

	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
		String sql = "UPDATE album SET cover_image_path = ? WHERE album_id = ?;";
		
			try {	
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.coverImagePath);
				ps.setString(2, this.albumID);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
			
		db.closeDbConnection();
		db = null;
	}

}
