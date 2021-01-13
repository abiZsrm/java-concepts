package experiment.dbFileIO.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FileIORepoImpl implements FileIORepo
{
   private NamedParameterJdbcTemplate m_namedJdbcTemplate;

   @Autowired
   public FileIORepoImpl( NamedParameterJdbcTemplate namedJdbcTemplate )
   {
      this.m_namedJdbcTemplate = namedJdbcTemplate;
   }

   @Override
   public void saveFile(File inputFile) 
      throws FileNotFoundException
   {
      FileInputStream fin = new FileInputStream(inputFile);
      StringBuilder insertQuery = new StringBuilder();
      insertQuery.append("INSERT INTO FILE_STORAGE ");
      insertQuery.append("(FILENAME_TXT, STORED_FILE) ");
      insertQuery.append("VALUES (:fileName, :storedFile)");

      HashMap<String, Object> mappedParams = new HashMap<String, Object>();
      mappedParams.put("fileName", inputFile.getName());
      mappedParams.put("storedFile", fin);

      // Store the file into the database.
      m_namedJdbcTemplate.update(insertQuery.toString(), mappedParams);
   }

   @Override
   public void readRandomFile(String inputFileName) 
      throws IOException, SQLException
   {
      String selectQuery = "SELECT STORED_FILE FROM FILE_STORAGE WHERE FILENAME_TXT = ?";
      Blob result = m_namedJdbcTemplate.getJdbcTemplate().queryForObject(selectQuery, Blob.class, inputFileName);
      
      // Print to console. 
      String resultTxt = printContentsOffile(result.getBinaryStream());
      System.out.println(resultTxt);
   }
   
   
   private String printContentsOffile(InputStream in) 
      throws IOException
   {
      BufferedReader bf = new BufferedReader(new InputStreamReader(in));
      String line = null;
      line = bf.readLine();

      String result = "";

      while (line != null)
      {
         result += line;
         line = bf.readLine();
      }

      return result;
   }
}
