


package jmv56_SpotifyKnockoff;


import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Transient;


/**Contains variables, constructor, getters, and setters to pair the Album objects in our java application with the Album entities in the database.
 * 
 * @author James Van Poolen
 * @version 1.0
 */


@Entity
@Table (name = "album")

public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column (name = "album_id")
	private String albumID;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "release_date")
	private String releaseDate;
	
	@Column (name = "cover_image_path")
	private String coverImagePath;
	
	@Column (name = "recording_company_name")
	private String recordingCompany;
	
	@Column (name = "number_of_tracks")
	private int numberOfTracks;
	
	@Column (name = "PMRC_rating")
	private String pmrcRating;
	
	@Column (name = "length")
	private double length;
	
	@Transient
	Map<String,Song> albumSongs;
	
	
	public Album(){
		super();
	}
	
	public void createAlbum (String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = new Album();
		
		b.setAlbumID(UUID.randomUUID().toString());
		b.setTitle(title);
		b.setReleaseDate(releaseDate);
		b.setCoverImagePath(coverImagePath);
		b.setRecordingCompany(recordingCompany);
		b.setNumberOfTracks(numberOfTracks);
		b.setPmrcRating(pmrcRating);
		b.setLength(length);
		
		emanager.persist(b);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void deleteAlbum (String albumID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = emanager.find(Album.class, albumID);
		emanager.remove(b);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void updateAlbum (String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = emanager.find(Album.class, albumID);
		
		if (title != "") {
			b.setTitle(title);
		}
		
		if (releaseDate != "") {
			b.setReleaseDate(releaseDate);
		}
		
		if (coverImagePath != "") {
			b.setCoverImagePath(coverImagePath);
		}
		
		if (recordingCompany != "") {
			b.setRecordingCompany(recordingCompany);
		}
		
		if (numberOfTracks != 0) {
			b.setNumberOfTracks(numberOfTracks);
		}
		
		if (pmrcRating != "") {
			b.setPmrcRating(pmrcRating);
		}
		
		if (length != 0) {
			b.setLength(length);
		}
		
		emanager.persist(b);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

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
	}


	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}


	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}


	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}


	public void setLength(double length) {
		this.length = length;
	}


	public void setAlbumSongs(Map<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}

}
