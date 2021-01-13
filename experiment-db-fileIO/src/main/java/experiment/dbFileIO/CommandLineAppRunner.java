package experiment.dbFileIO;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import experiment.dbFileIO.services.FileIORepo;

@Component
public class CommandLineAppRunner implements CommandLineRunner
{
   @Autowired
   private FileIORepo m_fileRepo;

   public void run(String... args) 
      throws IOException, SQLException
   {
      /*
       * --- TASK 1 --- 
       * 1. Create a sample file. (.txt). 
       * 2. Store some random value. 
       * 3. Pass the file to the FileRepoImpl class to store the file
       * into the database. 4. Repeat the same for simple spreadsheet files.
       * (.xlsx)
       */

      /*
       * --- TASK 2 --- 
       * 1. Read a known file from the database of type ".txt".
       * 2. Print out the system-console. 
       * 3. Repeat the same for simple spreadsheet files. (.xlsx).
       */
      String filePath = "\\SampleFiles\\SampleData.txt";
      String path = System.getProperty("user.dir").concat(filePath);
      File inputFile = new File(path);

      // Store the file.
      m_fileRepo.saveFile(inputFile);
      
      // Read the 'SampleData' file. 
      m_fileRepo.readRandomFile("SampleData.txt");
   }

}
