package JMV56_SpotifyKnockoff;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import java.awt.event.ActionEvent;



public class SpotifyGUI {

	private JFrame frame;
	private JRadioButton rdbtnShowArtists;
	private JRadioButton rdbtnShowAlbums;
	private JRadioButton rdbtnShowSongs;
	private JTextField txtSearch;
	private JLabel lblNewLabel;
	private JTable tblData;
	private JButton btnSearchButton;
	private DefaultTableModel musicData;
	private JScrollPane scrollPane;

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
					ErrorLogger.log(e.getMessage());
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
		
		musicData = getSongData("");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(316, 11, 658, 539);
		frame.getContentPane().add(scrollPane);
		tblData = new JTable(musicData);
		scrollPane.setViewportView(tblData);
		tblData.setFillsViewportHeight(true);
		tblData.setShowGrid(true);
		tblData.setGridColor(Color.BLACK);
		
		
		rdbtnShowAlbums = new JRadioButton("Show Albums");
		rdbtnShowAlbums.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShowAlbums.isSelected()) {
					rdbtnShowArtists.setSelected(false);
					rdbtnShowSongs.setSelected(false);
					musicData = getAlbumData("");
					tblData.setModel(musicData);
				}
			}
		});
		rdbtnShowAlbums.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShowAlbums.setBounds(59, 58, 134, 38);
		frame.getContentPane().add(rdbtnShowAlbums);
		
		rdbtnShowSongs = new JRadioButton("Show Songs");
		rdbtnShowSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShowSongs.isSelected()) {
					rdbtnShowAlbums.setSelected(false);
					rdbtnShowArtists.setSelected(false);
					musicData = getSongData("");
					tblData.setModel(musicData);
				}
			}
		});
		rdbtnShowSongs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnShowSongs.setBounds(59, 99, 134, 38);
		frame.getContentPane().add(rdbtnShowSongs);
		
		rdbtnShowArtists = new JRadioButton("Show Artists");
		rdbtnShowArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShowArtists.isSelected()) {
					rdbtnShowSongs.setSelected(false);
					rdbtnShowAlbums.setSelected(false);
					musicData = getArtistData("");	
					tblData.setModel(musicData);
				}
			}
		});
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
		
		
		btnSearchButton = new JButton("Search");
		btnSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnShowSongs.isSelected()) {
					musicData = getSongData(txtSearch.getText());
					tblData.setModel(musicData);
				} else if(rdbtnShowAlbums.isSelected()) {
					musicData = getAlbumData(txtSearch.getText());
					tblData.setModel(musicData);
				} else {
					musicData = getArtistData(txtSearch.getText());
					tblData.setModel(musicData);
				}
				
			}
		});
		btnSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearchButton.setBounds(172, 405, 134, 38);
		frame.getContentPane().add(btnSearchButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private DefaultTableModel getSongData(String searchTerm) {
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song ";
		
		String [] columns = {"Song ID","Title","Length","Release Date","Record Date"};
		
		if(!searchTerm.equals("")) {
			sql += "WHERE title LIKE '%" + searchTerm + "%';";
		}
			try {
				DbUtilities db = new DbUtilities();
				return db.getDataTable(sql, columns);
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(frame, "Unable to connect to database");
				ErrorLogger.log(e.getMessage());
			}
			return null;	 		
	}
	
	private DefaultTableModel getAlbumData(String searchTerm) {
		String sql = "SELECT album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length FROM album ";
		String [] columns = {"Album ID","Title","Release Date","Recording Company Name","Number of Tracks", "PMRC Rating", "Length"};
		
		if(!searchTerm.equals("")) {
			sql += "WHERE title LIKE '%" + searchTerm + "%';";
		}
		
		try {
			DbUtilities db = new DbUtilities();
			return db.getDataTable(sql, columns);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Unable to connect to database");		
			ErrorLogger.log(e.getMessage());		
		}
		return null;
	}
	
	private DefaultTableModel getArtistData(String searchTerm) {
		String sql = "SELECT artist_id, first_name, last_name, band_name, bio FROM artist ";
		
		if(!searchTerm.equals("")) {
			sql += "WHERE last_name LIKE '%" + searchTerm + "%' OR first_name LIKE '%" + searchTerm + "%' OR band_name LIKE '%" + searchTerm + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String [] columns = {"Artist ID","First Name","Last Name","Band Name","Bio"};
			return db.getDataTable(sql, columns);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frame, "Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
}
