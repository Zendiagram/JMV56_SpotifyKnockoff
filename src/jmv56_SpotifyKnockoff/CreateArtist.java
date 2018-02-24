package jmv56_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for creating artist objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class CreateArtist {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = new Artist();
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName("Jimi");
		a.setLastName("Hendrix");
		a.setBandName("The Jimi Hendrix Experience");
		a.setBio("The best electric guitar player of all time");
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
