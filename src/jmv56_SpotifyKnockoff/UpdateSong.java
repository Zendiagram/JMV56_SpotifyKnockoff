package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for updating the application and database using JPA for the Song class.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class UpdateSong {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = emanager.find(Song.class, "6c8b1775-bc9a-4d90-841e-1466939f2e6a");
		
		s.setTitle("This song IS in the database");
		s.setFilePath("/var/www/html/song.mp3");
		
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
