package jmv56_SpotifyKnockoff;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for creating Song objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class CreateSong {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle("The Fear");
		s.setLength(10);
		s.setRecordDate("2018-02-13");
		s.setReleaseDate("2018-02-14");
		s.setFilePath("");
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
