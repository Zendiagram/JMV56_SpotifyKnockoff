package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for updating the application and database using JPA for the Album class.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class UpdateAlbum {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Album b = emanager.find(Album.class, "bb5f6d7d-a2f4-43df-828a-1f4e278bf594");
		
		b.setCoverImagePath("/var/somerandompath/somethingnice");
		
		emanager.persist(b);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();

	}

}
