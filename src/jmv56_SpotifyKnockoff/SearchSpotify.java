package jmv56_SpotifyKnockoff;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;


/**This class pulls the information from the database and returns tables which can be displayed in jTable.  It is dependent on the searches specified by the GUI.
 * 
 * @author James Van Poolen
 * @version 1.0
 */
public abstract class SearchSpotify {

	
/**Searches the song table for the specified search term.  If search term is blank will return all songs.
 * 
 * @param searchTerm
 * @return
 */
	public static DefaultTableModel searchSongs(String searchTerm) {
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song ";
		
		String [] columns = {"Song ID","Title","Length","Release Date","Record Date"};
		
		if(!searchTerm.equals("")) {
			sql += "WHERE title LIKE '%" + searchTerm + "%';";
		}
			try {
				DbUtilities db = new DbUtilities();
				return db.getDataTable(sql, columns);
				
			} catch (SQLException e) {
				
				ErrorLogger.log(e.getMessage());
			}
			return null;	 		
	}
	
/**Searches the Album table for the specified search term.  If search term is blank will return all albums.
 * .
 * 
 * @param searchTerm
 * @return
 */
	public static DefaultTableModel searchAlbums(String searchTerm) {
		String sql = "SELECT album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length FROM album ";
		String [] columns = {"Album ID","Title","Release Date","Recording Company Name","Number of Tracks", "PMRC Rating", "Length"};
		
		if(!searchTerm.equals("")) {
			sql += "WHERE title LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			return db.getDataTable(sql, columns);
		} catch (SQLException e) {
			
			ErrorLogger.log(e.getMessage());		
		}
		return null;
	}

/**Searches the artist table for the specified search term.  If search term is blank will return all artists.
 * 
 * @param searchTerm
 * @return
 */
	public static DefaultTableModel searchArtist(String searchTerm) {
		String sql = "SELECT artist_id, first_name, last_name, band_name, bio FROM artist ";
		
		if(!searchTerm.equals("")) {
			sql += "WHERE last_name LIKE '%" + searchTerm + "%' OR first_name LIKE '%" + searchTerm + "%' OR band_name LIKE '%" + searchTerm + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String [] columns = {"Artist ID","First Name","Last Name","Band Name","Bio"};
			return db.getDataTable(sql, columns);
		} catch (SQLException e) {
			
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
}
