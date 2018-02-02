package jmv56_SpotifyKnockoff;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class Song {
	
	//private vars for Song
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	Map<String, Artist> songArtists;
	
	//adds a new song to the database
	public Song(String title, double length, String releaseDate, String recordDate) {
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString();  //randomly generated identifier
		
		songArtists = new Hashtable<String, Artist>();
		
	//System.out.println(this.songID);	
		//sql query stored in the var sql
		String sql = "INSERT INTO song (song_id, title, length, file_path, release_date, record_date, fk_album_id) ";
		sql += "VALUES (?,?,?,?,?,?,?);";  //sql += used to add information in a prepared statment.  Protects against sql injection.
		
		//System.out.println(sql);
		
		//Connect to the database and insert the new song.
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.songID);
			ps.setString(2, this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, "");  //this will be added later once we know where we are storing the actual song files.
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.executeUpdate();
			db.closeDbConnection();  //always close the door when you are done.
			db = null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
		
	}
	
	public Song(String songID, String title, double length, String releaseDate, String recordDate) {
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = songID;  
		
		songArtists = new Hashtable<String, Artist>();
		
	}
	
	//retrieve a song that is store in the database
	public Song(String songID) {
		
		songArtists = new Hashtable<String, Artist>();
		
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";  //maybe convert to prepared statment?
		
		DbUtilities db = new DbUtilities();
		
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {  //retreive all attributes of the song specified by songID
				this.songID = rs.getString("song_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>();
		
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(String.valueOf(this.length));
		songRecord.add(this.recordDate);
		songRecord.add(this.filePath);
		
		return songRecord;
	}
	
	//deletes a song from the database
	public void deleteSong(String songID) {
		
		//store the needed query in var sql
		String sql = "DELETE FROM song ";
		sql += "WHERE song_id = ?;";
	
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, songID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	//add artist to the songArtists hashtable by artistID
	public void addArtist(String... artistID) {  //multiple artists can be added at once, handled in the below loop.
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (String v : artistID) {
					Artist a = new Artist(v);
					this.songArtists.put(v, a);
				
					String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES (?,?);";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, v);
					ps.setString(2, a.getArtistID());
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
		
		db.closeDbConnection();
		db = null;
					
					
	}
	
	//delete artist from the songArtists hashtable by artistID
	public void deleteArtist(String... artistID) {
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (String v : artistID) {
					this.songArtists.remove(v);	
				
				
					String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, this.getSongID());
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
		
	
	
	//delete artist from the songArtists hashtable by artist object
	public void deleteArtist(Artist... artist) {
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
			try {
				for (Artist v : artist) {
					this.songArtists.remove(v.getArtistID());
				
				
					String sql = "DELETE FROM song_artist WHERE fk_song_id = ? AND fk_artist_id = ?;";
				
					PreparedStatement ps;
					ps = conn.prepareStatement(sql);
					ps.setString(1, this.getSongID());
					ps.setString(2, v.getArtistID());
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
	public String getReleaseDate() {
		return releaseDate;
	}

	public String getSongID() {
		return songID;
	}

	public String getTitle() {
		return title;
	}

	public double getLength() {
		return length;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}
}



