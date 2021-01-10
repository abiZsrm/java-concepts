package experiment.dbFileIO.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class FileIORepoImpl implements FileIORepo
{
   private JdbcTemplate m_jdbcTemplate; 
   
   @Autowired
   public FileIORepoImpl(JdbcTemplate jdbcTemplate)
   {
      this.m_jdbcTemplate = jdbcTemplate; 
   }
   
   @Override
   public void saveFile(File inputFile)
   {
      String inputQuery = ""; 
      
      // m_jdbcTemplate.update(inputQuery, "TODO: Specify Input Arguments"); 
   }

}
