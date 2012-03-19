package cl.reduc.commons.ofimatica;
import com.csvreader.CsvWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

public class CsvEngine {

    private CsvWriter writer;
    private FileWriter fileWriter;
    private BufferedWriter bf;
    private String separator;
    private String breakLine;
    private char delimeter;
    
    public CsvEngine(String path, char delimeter) {
        this.delimeter = delimeter;
        //writer = new CsvWriter(path);
        //writer.setDelimiter(delimeter);
        separator = File.separator;
        breakLine = System.getProperty("line.separator");
        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            bf = new BufferedWriter(new FileWriter(f)) ;
        } catch(IOException e ) {
            Logger.getLogger(getClass()).error(e);
        }
        
    }

    public void toCSV(String path, ResultSet rs) {
        
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            String value = "";
            for (int i=0; i < numberOfColumns; i++) {
                value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
                writer.write(value);
            }
            writer.endRecord();
            int cont = 0;
            while(rs.next()) {
                System.out.println(cont++);
                for(int x=1;x<=numberOfColumns;x++) {
                    writer.write(rs.getString(x));
                }
                writer.endRecord();
            }
        } catch(IOException e) {
            Logger.getLogger(CsvEngine.class).error(e);
        } catch(SQLException e ) {
            Logger.getLogger(CsvEngine.class).error(e);
        } finally {
            closeResultSet(rs);
        }
    }
    
    public void writeCsv(String content) {
        try {
            bf.write(content);       
        } catch(IOException e) {
            Logger.getLogger(getClass()).error(e);
        }
    }
    
     public void csvRunner(String path, ResultSet rs) {
        
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            String value = "";
            for (int i=0; i < numberOfColumns; i++) {
                value +=rsMetaData.getColumnName(i+1).toString().replace('_', ' ') + delimeter;
            }
            writeCsv(value);
            writeCsv(breakLine);
            int cont = 0;
            String content = "";
            while(rs.next()) {
                System.out.println(cont++);
                for(int x=1;x<=numberOfColumns;x++) {
                    
                    content+=rs.getString(x) +  delimeter;
                }
                writeCsv(content);
                writeCsv(breakLine);
                
            }
            bf.flush();
            
        } catch(SQLException e ) {
            Logger.getLogger(CsvEngine.class).error(e);
        } catch(IOException e) {
            Logger.getLogger(CsvEngine.class).error(e);
        } finally {
            closeResultSet(rs);
        }
        
    }
    
    public void finaliceFile() {
        try {
            bf.close();
        } catch(IOException e) {
            Logger.getLogger(CsvEngine.class).error(e);
        }
    }
            
    public void closeResultSet(ResultSet rs) {
        try {
            rs.close();
        } catch(SQLException e) {
            
        }
    }        
            
           
}