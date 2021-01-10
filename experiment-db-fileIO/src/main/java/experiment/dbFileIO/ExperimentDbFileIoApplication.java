package experiment.dbFileIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExperimentDbFileIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperimentDbFileIoApplication.class, args);
		
		/* --- TASK 1 ---
		 * 1. Create a sample file. (.txt). 
		 * 2. Store some random value. 
		 * 3. Pass the file to the FileRepoImpl class to store the file into the database. 
		 * 4. Repeat the same for simple spreadsheet files. (.xlsx)
		 */
		
		/* --- TASK 2 ---
		 * 1. Read a known file from the database of type ".txt". 
		 * 2. Print out the system-console. 
		 * 3. Repeat the same for simple spreadsheet files. (.xlsx). 
		 */
	}

}
