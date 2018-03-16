package jmv56_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
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
	
	public void createArtist(String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = new Artist();
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void deleteArtist(String artistID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = emanager.find(Artist.class, artistID);
		emanager.remove(a);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = emanager.find(Artist.class, artistID);
		
		if (firstName != "") {
			a.setFirstName(firstName);
		}
		
		if (lastName != "") {
			a.setLastName(lastName);
		}
		
		if (bandName != "") {
			a.setBandName(bandName);
		}
		
		if (bio != "") {
			a.setBio(bio);
		}
		
		a.setBio("");
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

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
