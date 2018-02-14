package jmv56_SpotifyKnockoff;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

import javax.swing.JOptionPane;

public class ErrorLogger {
	public static void log(String errorMessage) {
		//Save the following information to errorlog.txt		
		//Date, Time, ErrorMessage /n
		
		JOptionPane.showMessageDialog(null, "The connection to the database has failed.");
		
		try {
			LocalDateTime now = LocalDateTime.now();
			File file = new File("ErrorLog.txt");
			FileWriter fileWriter = new FileWriter(file, true);
			//I kept having trouble with "\n" and researched System.getProperty. It works great!
			fileWriter.write(now + "," + errorMessage + System.getProperty( "line.separator" ));
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//ErrorLogger.log(e.getMessage());
//e.printStackTrace();
//JOptionPane.showMessageDialog(null, "The connection to the database has failed.", JOptionPane.INFORMATION_MESSAGE);