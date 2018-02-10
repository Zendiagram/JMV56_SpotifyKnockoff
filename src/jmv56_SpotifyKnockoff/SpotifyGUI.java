package JMV56_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SpotifyGUI {

	private JFrame frame;
	private JRadioButton rdbtnShowArtists;
	private JRadioButton rdbtnShowAlbums;
	private JRadioButton rdbtnShowSongs;
	private JTextField txtSearch;
	private JLabel lblNewLabel;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpotifyGUI window = new SpotifyGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpotifyGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Spotify");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100,1000, 600);
		frame.getContentPane().setLayout(null);
		
		JLabel lblViewSelector = new JLabel("Select View");
		lblViewSelector.setForeground(Color.BLACK);
		lblViewSelector.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblViewSelector.setBounds(25, 24, 102, 27);
		frame.getContentPane().add(lblViewSelector);
		
		rdbtnShowAlbums = new JRadioButton("Show Albums");
		rdbtnShowAlbums.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShowAlbums.setBounds(59, 58, 134, 38);
		frame.getContentPane().add(rdbtnShowAlbums);
		
		rdbtnShowSongs = new JRadioButton("Show Songs");
		rdbtnShowSongs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShowSongs.setBounds(59, 99, 134, 38);
		frame.getContentPane().add(rdbtnShowSongs);
		
		rdbtnShowArtists = new JRadioButton("Show Artists");
		rdbtnShowArtists.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShowArtists.setBounds(59, 140, 134, 38);
		frame.getContentPane().add(rdbtnShowArtists);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setBounds(59, 356, 247, 38);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 318, 102, 27);
		frame.getContentPane().add(lblNewLabel);
		
		tblData = new JTable(getSongData());
		tblData.setBounds(347, 11, 627, 539);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		frame.getContentPane().add(tblData);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private DefaultTableModel getSongData() {
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song;";
		
		try {
			DbUtilities db = new DbUtilities();
			String [] columns = {"Song ID","Title","Length","Release Date","Record Date"};
			return db.getDataTable(sql, columns);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
}
