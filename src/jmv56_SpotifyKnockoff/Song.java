


package jmv56_SpotifyKnockoff;

import java.util.Map;
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
import javax.persistence.Transient;

/**Contains variables, constructor, getters, and setters to pair the Song objects in our java application with the Song entities in the database.
 * 
 * 
 * @author James Van Poolen
 * @version 1.0
 */

@Entity
@Table (name = "song")

public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	//private vars for Song
	@Column (name = "song_id")
	private String songID;
	
	@Column (name = "title")
	private String title;
	
	@Column (name = "length")
	private double length;
	
	@Column (name = "file_path")
	private String filePath;
	
	@Column (name = "release_date")
	private String releaseDate;
	
	@Column (name = "record_date")
	private String recordDate;
	
	@Transient
	Map<String, Artist> songArtists;

	public Song(){
		super();
	}
	
	public void createSong(String title, int length, String recordDate, String releaseDate, String filePath ) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setRecordDate(recordDate);
		s.setReleaseDate(releaseDate);
		s.setFilePath(filePath);
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void deleteSong(String songID) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = emanager.find(Song.class, songID);
		emanager.remove(s);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}
	
	public void updateSong(String songID, String title, int length, String recordDate, String releaseDate, String filePath) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = emanager.find(Song.class, songID);
		
		if (title != ""){
			s.setTitle(title);
		}
		
		if (length != 0) {
			s.setLength(length);
		}
		
		if (recordDate != "") {
			s.setRecordDate(recordDate);
		}
		
		if (releaseDate != "") {
			s.setReleaseDate(releaseDate);
		}
		
		if (filePath != "") {
			s.setFilePath(filePath);
		}
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}


	
//getters and setters
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

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void setSongID(String songID) {
		this.songID = songID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
}



