package experiment.dbFileIO.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface FileIORepo
{
   public void saveFile(File inputFile)
      throws FileNotFoundException; 
   
   public void readRandomFile(String inputFileName)
      throws IOException, SQLException; 
}
