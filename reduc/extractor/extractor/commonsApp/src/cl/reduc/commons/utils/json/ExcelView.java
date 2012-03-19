package cl.reduc.commons.utils.json;

import cl.reduc.commons.odbc.controller.ExcelEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ExcelView  {

      private FileInputStream fis;
      private ExcelEngine excel;
      private int paginas;
      private String fecha;
      private String fileName;
      
      
      public void prepareResponse(ResultSet rs, File f ) throws IOException{
          this.fileName = fileName;
           try {
                excel = new ExcelEngine();
                //excel.createWorkBook(File.createTempFile(fileName + ".xls", null) );
                excel.createWorkBook(f);
                excel.createSheet();
                excel.setFormatArialBold();
                ResultSetMetaData rsMetaData = rs.getMetaData();
                  int numberOfColumns = rsMetaData.getColumnCount();
                  String value = "";
                  for (int i=0; i < numberOfColumns; i++) {
                    value =rsMetaData.getColumnName(i+1).toString().replace('_', ' ');
                    excel.addCell(i, 0, value);
                  }
                excel.setFormatArialNoBold();
                int cont = 1;
                int cont2= 1;
                int paginas=0;
                while(rs.next()) {
                  System.out.println("Cantidad de Registros :" + cont2);
                  for(int x=1;x<=numberOfColumns;x++) {
                    excel.addCell(x-1, cont, rs.getString(x));
                          }
                  if(cont>10000) {
                    paginas++;
                    excel.createSheet(paginas, "Pagina " + paginas);
                    cont=1;
                    System.gc();
                    
                  }
                  if(cont2>240000) {
                    break;
                  }
                  cont++;
                  cont2++;
                }
                
              } catch (SQLException e) {
                e.printStackTrace();
              } finally {
                excel.writeExcel();
              }
      }
      
      public void prepareResponse(File f ) throws FileNotFoundException {
          this.fileName = f.getName();
          fis = new FileInputStream( f );
      }
      
      public void render(HttpServletResponse response){
          byte[] bytes;
          try {
              bytes = new byte[fis.available()];
              fis.read(bytes);
              response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition","attachment; filename=" + fileName);
              response.setContentLength(bytes.length);
              response.getOutputStream().write(bytes);
              response.getOutputStream().flush();
              response.getOutputStream().close();
              fis.close();
          } catch (IOException e) {
              Logger.getLogger( ExcelView.class).error(e);
          }
        
      }

}