package jmv56_SpotifyKnockoff;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Artist {
	
	//private variables for Artist object
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
	//constructor - adds a artist to the database
	public Artist (String firstName, String lastName, String bandName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = UUID.randomUUID().toString(); //generates random identifier
		
		//build the sql query and put into variable
		String sql = "INSERT INTO artist (artist_id,first_name,last_name,band_name,bio) ";
		sql += "VALUES (?, ?, ?, ?, ?);";
		
		//connect to the database and inject the data using the sql query stored in 'sql'
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps;
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.artistID);
			ps.setString(2, this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName);
			ps.setString(5, "");  //this line would contain the bio.  I am currently leaving it blank.
			ps.executeUpdate();
			db.closeDbConnection();  //jdbc connections are expensive.  Close the dbConnection when you are done with it.
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//constructor - gets a artist object out of the database - in layman's terms
	public Artist(String artistID) {
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";  //is concat a risk with only one var? Maybe change to prep statement.
		
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){   //retrieving all attribute of the specified artist object
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");				
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//deletes an artist out of the database
	public void deleteArtist(String artistID) {
		//create the delete query and put it in var sql.  Using prepared statement.  Necessary?
		String sql = "DELETE FROM artist ";
		sql += "WHERE artist_id = ?;";
	
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//getters
	public String getArtistID() {
		return artistID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBandName() {
		return bandName;
	}

	public String getBio() {
		return bio;
	}

}
