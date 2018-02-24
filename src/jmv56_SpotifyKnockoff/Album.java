


package jmv56_SpotifyKnockoff;


import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
