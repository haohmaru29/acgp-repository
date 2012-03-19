package cl.reduc.commons.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class MapUtils {

    public static Map ordenerPorValor(Map map) {
        HashMap mapResultado = new LinkedHashMap();
        List misMapKeys = new ArrayList(map.keySet());
        List misMapValues = new ArrayList(map.values());
        TreeSet conjuntoOrdenado = new TreeSet(misMapValues);
        Object[] arrayOrdenado = conjuntoOrdenado.toArray();
        int size = arrayOrdenado.length;
        for (int i=0; i<size; i++) {
            mapResultado.put(misMapKeys.get(misMapValues.indexOf(arrayOrdenado[i]) ),arrayOrdenado[i]);
        }
        
        return mapResultado;
    }
    
    public static String extract(Object value) {
        String response = "";
        if(value!="") {
            Object[] parse = (String[]) value;
            try {
                response=String.valueOf(parse[0] );
            }catch(Exception e) {
                e.printStackTrace();
                response=value.toString();
            }
        } 
        
        return response.trim();
    } 
  
}