package jmv56_SpotifyKnockoff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**Test class for deleting artist objects in the application and database using JPA.
 * 
 * @author James Van Poolen
 * @version 1.0
 */

public class DeleteArtist {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JMV56_SpotifyKnockoff");
		
		EntityManager emanager = emfactory.createEntityManager();
		
		emanager.getTransaction().begin();
		
		Artist a = emanager.find(Artist.class, "a4799a95-9496-483f-8029-f20be8edee0f");
		emanager.remove(a);
				
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();


	}

}
