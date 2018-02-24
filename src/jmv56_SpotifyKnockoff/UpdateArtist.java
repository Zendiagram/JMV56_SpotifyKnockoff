

package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for updating the application and database using JPA for the Artist class.
 * 
 * @author James Van Poolen
 * @version 1.0
 */
public class UpdateArtist {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = emanager.find(Artist.class, "76035e5f-fb61-4721-bcde-df7e2a51fb52");
		
		a.setBio("");
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();


	}

}
