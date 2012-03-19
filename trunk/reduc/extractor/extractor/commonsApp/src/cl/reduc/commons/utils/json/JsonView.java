package cl.reduc.commons.utils.json;

import cl.reduc.commons.utils.StringUtils;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class JsonView  {
   
    	private StringBuffer jsonResponse = new StringBuffer();
	
      public void prepareList(List list) {
        if( list != null) jsonResponse.append( JsonParser.parseList(list) );
        else emptyResponse();
      }
      
      public void prepareResponse(List list, String count) {
        if( list != null) prepareResults(true, JsonParser.parseList(list), count );
        else emptyResponse();
      }
      
      public void prepareResponse(List list) {
            if( list != null) prepareResults(true, JsonParser.parseList(list) );
            else emptyResponse();
      }
      
      public void prepareResponse(Map map) {
            if( map != null) { 
                prepareResults(true, JsonParser.parseMap(map) );
            }
            else emptyResponse();
      }
      
      public void prepareResponse(String jsonString){
          jsonResponse.append( jsonString );
      }
      
      public void render(HttpServletResponse response) {
          response.setContentType("application/json");
          response.setHeader("Expires", "0");
          response.setHeader("Pragma", "no-cache");
          response.setContentType("text/html; charset=UTF-8");
          try {
              Logger.getLogger(getClass()).info(jsonResponse.toString());
              response.getWriter().print( jsonResponse.toString() );
        } catch (IOException ex) {
              Logger.getLogger(getClass()).error(ex);
        }
      }
      
      public void render(HttpServletResponse response, String strResponse) {
          response.setContentType("application/json");
          response.setHeader("Expires", "0");
          response.setHeader("Pragma", "no-cache");
          
          try {
              Logger.getLogger(getClass()).info(strResponse );
              response.getWriter().print( strResponse );
          } catch (IOException ex) {
              Logger.getLogger(getClass()).error(ex);
          }
      }
      
      
      public void emptyResponse(){
        jsonResponse.append("{").append(JsonUtils.parse("success")).append(": true");
        jsonResponse.append(",").append(JsonUtils.parse("message")).append(": \"Datos no encontrados!\"");
        jsonResponse.append(",").append(JsonUtils.parse("data")).append(":[]");
        jsonResponse.append("}");
      
      }
      
      public void prepareMessageResponse(boolean success, String message) {
        jsonResponse.append("{").append(JsonUtils.parse("success")).append(":").append( "" + success );
        jsonResponse.append(",").append(JsonUtils.parse("message")).append(":").append(JsonUtils.parse( message ));
        jsonResponse.append("}");
        
      }
      
      public void prepareResponseTodos(ResultSet rs) {
          List l = new ArrayList();
          Map m =null;
          Map m2 = new HashMap();
          Map m3 = new HashMap();
          try {
              ResultSetMetaData metadata = rs.getMetaData();
              int numberOfColumns = metadata.getColumnCount();
              String columnName = "";
              int cont = 0;
              while(rs.next()) {
                  m = new HashMap();
                  for (int i = 1; i <= numberOfColumns; i++) {
                      columnName = metadata.getColumnName(i).trim();
                      m.put(columnName, rs.getString(columnName).replace('\"', ' '));
                      if(cont == 0) {
                        m2.put(columnName, "TODOS");
                        m3.put(columnName, "NINGUNO");
                        cont =1;
                      } else if(cont == 1) {
                        m2.put(columnName, "TODOS");
                        m3.put(columnName, "NINGUNO");
                        l.add(m2);
                        l.add(m3);
                        cont=5;
                      }
                  }
                  l.add(m);
              }        
          } catch(SQLException e) {
              Logger.getLogger(getClass()).error(e);   
          } finally {
              closeResultSet(rs);
          }
          
          prepareResponse(l, l.size() + "");
      }
      
      public void prepareResponse(ResultSet rs) {
          List l = new ArrayList();
          Map m = new HashMap();
          try {
              ResultSetMetaData metadata = rs.getMetaData();
              int numberOfColumns = metadata.getColumnCount();
              String columnName = "";
              
              while(rs.next()) {
                  m = new HashMap();
                  for (int i = 1; i <= numberOfColumns; i++) {
                      columnName = metadata.getColumnName(i).trim();
                      m.put(columnName, rs.getString(columnName).replace('\"', ' '));
                  }
                  l.add(m);
              }        
          } catch(SQLException e) {
              Logger.getLogger(getClass()).error(e);   
          } finally {
              closeResultSet(rs);
          }
          
          prepareResponse(l, l.size() + "");
      }
      
       public void prepareResponsePagination(ResultSet rs, int initial, int finish) {
          List l = new ArrayList();
          Map m = new HashMap();
          int contador = 0;
          try {
              ResultSetMetaData metadata = rs.getMetaData();
              int numberOfColumns = metadata.getColumnCount();
              String columnName = "";
              while(rs.next()) {
                  if(initial <= contador && contador < finish) {
                      m = new HashMap();
                      for (int i = 1; i <= numberOfColumns; i++) {
                          columnName = metadata.getColumnName(i).trim();
                          m.put(columnName, rs.getString(columnName).replace('\"', ' '));
                      }
                      l.add(m);
                  }
                  contador++;
              }        
          } catch(SQLException e) {
              Logger.getLogger(getClass()).error(e);   
          } finally {
              closeResultSet(rs);
          }
          
          prepareResponse(l, contador + "");
      }
      
      public void prepareResponsePagination(ResultSet rs, int initial, int finish, List l) {
          Map m = new HashMap();
          List listaPaginada = new ArrayList();
          int contador = 0;
          try {
              ResultSetMetaData metadata = rs.getMetaData();
              int numberOfColumns = metadata.getColumnCount();
              String columnName = "";
              while(rs.next()) {
                  m = new HashMap();
                  for (int i = 1; i <= numberOfColumns; i++) {
                      columnName = metadata.getColumnName(i).trim();
                      m.put(columnName, (rs.getString(columnName)==null)?"": rs.getString(columnName).replace('\"', ' '));
                  }
                  if(initial <= contador && contador < finish) {
                      listaPaginada.add(m);
                  }
                  l.add(m);
                  contador++;
              }        
          } catch(SQLException e) {
              Logger.getLogger(getClass()).error(e);   
          } finally {
              closeResultSet(rs);
          }
          
          prepareResponse(listaPaginada, l.size() + "");
      }
      
      public void prepareResponsePagination(String filter, int initial, int finish, List l, String nombreCampo) {
          Map m = null;
          //System.out.println("Tama�o .....-> " + l.size());
          List newList = new ArrayList();
          filter = filter.toUpperCase();
          int contador = 0;
          if(!"".equals(filter) ) {
              Iterator it = l.iterator();
              while(it.hasNext() ) {
                  m = (HashMap) it.next();                  
                  if(StringUtils.contains(filter, m.get(nombreCampo).toString().toUpperCase().trim() ) ) {
                      if(initial <= contador && contador < finish) {
                        newList.add(m);
                      }
                      contador++;
                  }
              }
              
              prepareResponse(newList, contador + "");
          } else {
              Iterator it = l.iterator();
              while(it.hasNext() ) {
                  m = (HashMap) it.next();
                  if(initial <= contador && contador < finish) {
                      if(StringUtils.contains(filter, m.get(nombreCampo).toString() ) ) {
                          newList.add(m);
                      }
                  }
                  contador++;
              }
          
              prepareResponse(newList, l.size() + "");
          }
      }
      
      public void prepareResponse(List list, String page, String start, String limit) {
            List listEdit = new ArrayList();
            int pageLimit = Integer.parseInt(limit);
            int pageStart = Integer.parseInt(start);
            int pageNumber = Integer.parseInt(page);
            int maxCamp = pageLimit*pageNumber;
            
            if(maxCamp>list.size()) {
                maxCamp = list.size();
            }
            
            for(int x=pageStart;x<=maxCamp;x++) {
                listEdit.add(list.get(x));
                
                if((maxCamp-1)==x) {
                    break;
                } 
            }
            
            if( list != null) prepareResponse(listEdit, list.size()+"" );
            else emptyResponse();
      }
      
      public void prepareResponse(ResultSet rs, String page, String start, String limit) {
          List l = new ArrayList();
          Map m = new HashMap();
          try {
              ResultSetMetaData metadata = rs.getMetaData();
              int numberOfColumns = metadata.getColumnCount();
              String columnName = "";
              while(rs.next()) {
                  m = new HashMap();
                  for (int i = 1; i <= numberOfColumns; i++) {
                      columnName = metadata.getColumnName(i).trim();
                      m.put(columnName, rs.getString(columnName));
                  }
                  l.add(m);
              }        
          } catch(SQLException e) {
              Logger.getLogger(getClass()).error(e);   
          } finally {
              closeResultSet(rs);
          }
          
          prepareResponse(l,page, start, limit);
      }
      
      private void closeResultSet(ResultSet rs ) {
          try {
              rs.close();
          } catch(SQLException e ) {
          }
      }
      
      public void prepareResponse(boolean success, String value) {
        jsonResponse.append("{").append(JsonUtils.parse("success")).append(":").append(  "" + success );
        jsonResponse.append(",").append(JsonUtils.parse("value")).append(":").append(JsonUtils.parse( value ));
        jsonResponse.append("}");
        
      }
    
      public void prepareResults(boolean success, String results) {
        jsonResponse.append("{").append(JsonUtils.parse("success")).append(":").append(  "" + success );
        jsonResponse.append(",").append(JsonUtils.parse("data")).append(":").append( results );
        jsonResponse.append("}");
        
      }
    
      public void prepareResults(boolean success, String results, String count) {
        jsonResponse.append("{").append(JsonUtils.parse("success")).append(":").append(  "" + success );
        jsonResponse.append(",").append(JsonUtils.parse("count")).append(":").append( count );
        jsonResponse.append(",").append(JsonUtils.parse("data")).append(":").append( results );
        jsonResponse.append("}");
      }
}