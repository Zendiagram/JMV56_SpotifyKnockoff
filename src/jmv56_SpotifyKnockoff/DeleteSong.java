package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for deleting song objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class DeleteSong {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Song s = emanager.find(Song.class, "18080926-8953-4186-a5eb-eb1ead34796a");
		emanager.remove(s);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
