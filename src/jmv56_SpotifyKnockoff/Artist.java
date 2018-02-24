package jmv56_SpotifyKnockoff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**Contains variables, constructor, getters, and setters to pair the Artist objects in our java application with the Artist entities in the database.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

@Entity
@Table (name = "artist")

public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column (name = "artist_id")
	private String artistID;
	
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Column (name = "band_name")
	private String bandName;
	
	@Column (name = "bio")
	private String bio;
	
	public Artist(){
		super();
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
	}

	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

}
