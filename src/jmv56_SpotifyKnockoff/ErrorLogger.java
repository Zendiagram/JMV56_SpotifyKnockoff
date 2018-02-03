package JMV56_SpotifyKnockoff;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

public class ErrorLogger {
	public static void log(String errorMessage) {
		//Save the following information to errorlog.txt		
		//Date, Time, ErrorMessage /n
		
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
