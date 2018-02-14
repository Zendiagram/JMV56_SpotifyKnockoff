/**Provides methods for adding and removing artists from the database
 * 
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


public class Artist {
	
	//private variables for Artist object
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
	//constructor - adds a artist to the database
	/**
     * Constructor - use to create an artist and add it to the database
     * @param firstName - first name of the artist being added
     * @param lastName - last name of the artist being added
     * @param bandName - the name of the band that the artist is a member of
     */
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
			ps.setString(5, "");  //this line would contain the bio.  Creat a setter for it.
			ps.executeUpdate();
			db.closeDbConnection();  //jdbc connections are expensive.  Close the dbConnection when you are done with it.
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		
		
	}
	//constructor - gets a artist object out of the database - in layman's terms
	/**
     * Constructor - use to create an artist from existing information in the database
     * @param artistID - the unique identifier for the artist in the database.  Primary key of the artist table.
     * 
     */
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
			ErrorLogger.log(e.getMessage());
		}
	}
	
	//deletes an artist out of the database
	/**
	 * Deletes and artist out of the artist table in the database
	 * 
	 * @param artistID - the unique identifier for the artist in the database.  Primary key of the artist table.
	 */
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
			ErrorLogger.log(e.getMessage());
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
	public void setBio(String bio) {
		this.bio = bio;
		
		DbUtilities db = new DbUtilities();
		Connection conn = db.getConn();
		
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		
			try {	
				PreparedStatement ps;
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.bio);
				ps.setString(2, this.artistID);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogger.log(e.getMessage());
			}
			
		db.closeDbConnection();
		db = null;
	}

}
