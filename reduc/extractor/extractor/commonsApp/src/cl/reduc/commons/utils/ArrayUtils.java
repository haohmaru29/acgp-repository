package cl.reduc.commons.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArrayUtils {
  
    public static String extract( String[] data){
        String str = null;
        
        try{
          str = data[0];
        } catch(IllegalArgumentException ex){
          ex.printStackTrace();
        }
        
        return str;
    }
	
	public static ObjectUtils extract( ObjectUtils[] data){
		ObjectUtils str = null;
		
		try{
			str =  data[0];
		} catch(IllegalArgumentException ex){
        ex.printStackTrace();
		}
		
		return str;
	}
	
    public static String implode(String[] ary, String delim) {
        String out = "";
        for(int i=0; i<ary.length; i++) {
            if(i!=0) { out += delim; }
            out += ary[i];
        }
        return out;
    }
	
    public static String implode(List data, String delimiter){
        StringBuffer buffer = new StringBuffer();
        ListIterator dataIterator = data.listIterator();
        
        if( !data.isEmpty()) buffer.append(dataIterator.next() );
        while (dataIterator.hasNext()){
          buffer.append( delimiter ).append(dataIterator.next().toString());
        }
        
        return buffer.toString();
    }
}