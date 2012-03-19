package cl.reduc.commons.utils.json;
import cl.reduc.commons.utils.ArrayUtils;
import cl.reduc.commons.utils.DateUtils;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.log4j.Logger;

public class JsonParser {

      public static boolean success = false;
	
      public static String parseMap(Map map){
        List jsonData = new ArrayList();
        Iterator keysIterator = map.keySet().iterator();
        
        String key = null;
        
        while( keysIterator.hasNext() ){
          key = (String) keysIterator.next();
          
          if(map.get(key) != null){
            jsonData.add( new StringBuffer().append( JsonUtils.parse(key) ).append(":")
                .append( parseValue( map.get(key).getClass(), map.get(key)) ).toString() );
          } else {
            jsonData.add( new StringBuffer().append( JsonUtils.parse(key) ).append(":")
                .append( "\"\"" ).toString() );
          }
          
        }
        
        return new StringBuffer()
          .append("{").append( ArrayUtils.implode(jsonData, ",")).append("}").toString();
      }
      
      
      public static String parseList (List list){
        List jsonData = new ArrayList();
        ListIterator listIterator = list.listIterator();
        
        while( listIterator.hasNext()){
          Object e = listIterator.next();
          if(e!=null)
            jsonData.add( parseValue( e.getClass(), e)  );
        }
        
        return new StringBuffer().append("[")
          .append( ArrayUtils.implode(jsonData, ", ") )
          .append("]").toString();
        
      }
      
      private static String parseValue(Class dataType, Object value){
          Object parsedValue = null;
          
          if( value == null){
            parsedValue = "null";
          
          } else if( dataType.equals( List.class)){
            parsedValue = parseList((List) value);
          
          } else if( dataType.equals(Map.class) ) {
              parsedValue = parseMap((Map) value);
            
          } else if( dataType.equals(HashMap.class) ) {
              parsedValue = parseMap((Map) value);
          } else if(dataType.equals(Timestamp.class)){
              parsedValue = "";
              if( value != null){
                parsedValue = JsonUtils.parse( DateUtils.parseTimestamp( value.toString()) );
              }
          } else if( dataType.equals(String.class)
                || dataType.equals(Long.class) 
                || dataType.equals(long.class)
                || dataType.equals(Integer.class) 
                || dataType.equals(int.class)
                || dataType.equals(short.class)
                || dataType.equals(Boolean.class) 
                || dataType.equals(boolean.class)
                || dataType.equals(BigDecimal.class)
                || dataType.equals(Object.class)) {
            parsedValue = JsonUtils.parse( value );
        
          } else if( dataType.equals(Object.class) ) { 
            
          } 
          
          return parsedValue.toString();
        }
}