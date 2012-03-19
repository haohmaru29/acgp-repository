package cl.reduc.commons.utils.json;
import cl.reduc.commons.utils.ArrayUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    private static final String QUOTE = "\"";
	
    public static String parseJson(String key, String value){
      return new StringBuffer()
        .append( parse(key)).append(":").append( parse(value) ).toString();
    }
    
    public static String parseJson(String key, Object value){
      return new StringBuffer()
        .append( parse(key)).append(":").append( value.toString() ).toString();
    }
	
    /*
    public static Object jsonObject(Map params){
        List tmp = new ArrayList();
        
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
              Map.Entry pairs = (Map.Entry)it.next();
              if( pairs.getValue().toString().startsWith("{") 
                  || pairs.getValue().toString().contains("function")  
                  || pairs.getValue().toString().contains("true") 
                  || pairs.getValue().toString().contains("false")) {
                tmp.add( parseJson( pairs.getKey().toString(), (Object) pairs.getValue()));
              } else {
                tmp.add( parseJson( pairs.getKey().toString(), pairs.getValue().toString()));
              }
        }
        
        return new StringBuffer()
          .append( jsonObject( ArrayUtils.implode(tmp, ",") ) ).toString();
      
    }*/
	
	public static Object jsonObject(String key, String value){
		return new StringBuffer().append("{")
			.append( parseJson(key, value) ).append("}").toString();
	}
	
	public static Object jsonObject(String jsonData){
		return new StringBuffer().append("{")
			.append( jsonData ).append("}").toString();
	}
	
	public static String jsonArray(String jsonObjects){
		return new StringBuffer().append("[").append( jsonObjects).append("]").toString();
	}
	
	public static String jsonArray(List jsonObjects){
		return new StringBuffer().append("[").append( ArrayUtils.implode(jsonObjects, ",")).append("]").toString();
	}
	
	public static String parse(String string){
		String parsedStr = "\"\"";
		if( string != null )
			parsedStr = new StringBuffer().append(QUOTE).append(string.trim().replaceAll("\\s\\s+|\\n|\\r", ", ")).append(QUOTE).toString();
		
		return parsedStr;
	}
	
      public static String parse(Object data){
        String parsedStr = "\"\"";
        if( data == null)
          parsedStr = new StringBuffer().append("null").toString();
        else if( data instanceof String || data instanceof Timestamp)
          parsedStr = new StringBuffer().append(QUOTE).append(data.toString().trim().replaceAll("\\s\\s+|\\n|\\r", ", ")).append(QUOTE).toString();
        else
          parsedStr = new StringBuffer().append(data).toString();
          
        return parsedStr;
      }
 
}