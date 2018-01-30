//This class contains all of the code used to test my methods in the Song, Artist, and Album classes.  

package jmv56_SpotifyKnockoff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Tester {

	public static void main(String[] args) {
		
		//-----------Test Creating Object and Adding them to Database-----------------
		//Song s1 = new Song("All Along the Watchtower", 5.45, "1967-11-08", "1967-03-15", "57ed733c-0099-11e8-a67a-005056881e07");
		//Artist a1 = new Artist("Jimi", "Hendrix", "The Jimi Hendrix Experience");
		//Album alb1 = new Album("Electric Ladyland", "1968-10-12", "Reprise Records", 16, "NR", 75.47);
		
		
		//-----------Example of Hashtable Work-------------------
		/*
		Map<String, Song> songList = new Hashtable<String, Song>();
		
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs;
			rs = db.getResultSet(sql);
			
			while(rs.next()) {
				Song s = new Song(rs.getString("song_id"));			
				songList.put(s.getSongID(), s);
				System.out.println(s.getTitle());
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Song foundSong = songList.get("b2dc89b2-205e-40ac-be60-d9b271490458");
		System.out.println(foundSong.getTitle());
		*/
		
		//-------------Test Deleting Objects from Database----------------
		/*
		Album a1 = new Album("60698fbb-e60a-4c04-9d6c-7d0d7ffac4ea");
		a1.deleteAlbum(a1.getAlbumID());
		*/
		
		//------------Test Adding and Deleting Artists from Song Hashtable--------------
		/*
		Song s1 = new Song("50400288-6e24-4098-9e92-92eee66d1216");
		s1.addArtist("a4799a95-9496-483f-8029-f20be8edee0f", "270fbad1-1711-4f8e-9e1d-018b8f4096b6");
		
		System.out.println(s1.getSongArtists());
		
		s1.deleteArtist("a4799a95-9496-483f-8029-f20be8edee0f");
		
		System.out.println(s1.getSongArtists());
		*/
		
		//--------------Test Adding and Deleting Songs from Album Hashtable------------------
		/*
		Album a1 = new Album ("bb5f6d7d-a2f4-43df-828a-1f4e278bf594");
		a1.addSong("09cc41f9-3976-4405-aecb-c7571d5889da", "50400288-6e24-4098-9e92-92eee66d1216");
		System.out.println(a1.getAlbumSongs());
		
		Song s1 = new Song ("50400288-6e24-4098-9e92-92eee66d1216");
		a1.deleteSong(s1);
		System.out.println(a1.getAlbumSongs());
		*/
	}

}