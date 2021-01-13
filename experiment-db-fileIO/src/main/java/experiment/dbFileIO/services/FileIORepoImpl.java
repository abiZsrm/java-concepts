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
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
   public void readTxtFile(String inputFileName) 
      throws IOException, SQLException
   {
      String selectQuery = "SELECT STORED_FILE FROM FILE_STORAGE WHERE FILENAME_TXT = ?";
      Blob result = m_namedJdbcTemplate.getJdbcTemplate().queryForObject(selectQuery, Blob.class, inputFileName);
      
      // Print to console. 
      String resultTxt = printContentsOffile(result.getBinaryStream());
      System.out.println(resultTxt);
   }
   
   
   @Override
   public void readSpreadSheetFile(String inputFileName)
      throws IOException, SQLException
   {
      String selectQuery = "SELECT STORED_FILE FROM FILE_STORAGE WHERE FILENAME_TXT = ?"; 
      Blob result = m_namedJdbcTemplate.getJdbcTemplate().queryForObject(selectQuery, Blob.class, inputFileName); 
      
      // Print to console. 
      String output = ""; 
      XSSFWorkbook readSpreadSheet = new XSSFWorkbook(result.getBinaryStream());
      Iterator<Sheet> sheets = readSpreadSheet.sheetIterator();

      while (sheets.hasNext())
      {
         // Every sheet is composed of records: ROWS.
         Sheet sheet = sheets.next();
         Iterator<Row> rows = sheet.rowIterator();

         // Every row is composed of cells: Column.
         // Skip the header row. 
         rows.next(); 
         
         while (rows.hasNext())
         {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();

            // Every cell being processed.
            while (cells.hasNext())
            {
              Cell cell = cells.next(); 
              output += cell.toString() + "|"; 
            }
         }
      }
      
      // Print output. 
      System.out.println(output);
      readSpreadSheet.close();
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
