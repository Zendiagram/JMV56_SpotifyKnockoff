package jmv56_SpotifyKnockoff;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class Album {
	
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	Map<String,Song> albumSongs;
	
	
	public Album (String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString();
		
		String sql = "INSERT INTO album (album_id,title,release_date,cover_image_path,recording_company_name,number_of_tracks,PMRC_rating,length) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.albumID);
			ps.setString(2,  this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, "");
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks);
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Album(String albumID) {
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		
		DbUtilities db = new DbUtilities();
		
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

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

}
