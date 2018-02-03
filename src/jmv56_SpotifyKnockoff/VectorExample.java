package JMV56_SpotifyKnockoff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VectorExample {

	public static void main(String[] args) {
		
		Vector<Vector<Song>> songTable = new Vector<>();
				
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs;
			rs = db.getResultSet(sql);
			
			while(rs.next()) {
				Song s = new Song(rs.getString("song_id"),	
					rs.getString("title"),
					rs.getDouble("length"),
					rs.getString("release_date"),
					rs.getString("record_date"));
					
				//songTable.add(s.getSongRecord());
				System.out.println(s.getTitle());
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

}
