package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for deleting album objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class DeleteAlbum {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = emanager.find(Album.class, "1497e914-dfe5-43b2-be33-1291bf935d63");
		emanager.remove(b);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
