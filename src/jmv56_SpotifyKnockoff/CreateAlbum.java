package jmv56_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for creating ablum objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class CreateAlbum {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = new Album();
		b.setAlbumID(UUID.randomUUID().toString());
		b.setTitle("Electric Ladyland");
		b.setReleaseDate("1968-07-07");
		b.setCoverImagePath("wapalooooooo");
		b.setRecordingCompany("Reprise Records");
		b.setNumberOfTracks(19);
		b.setPmrcRating("NA");
		b.setLength(75);
		
		emanager.persist(b);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
