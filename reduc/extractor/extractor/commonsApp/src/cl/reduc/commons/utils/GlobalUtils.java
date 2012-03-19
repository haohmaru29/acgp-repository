package cl.reduc.commons.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class GlobalUtils {
 
    private static ResourceBundle proper = ResourceBundle.getBundle("UI");
    
    public static Map getPaginacion(List reportes, int start, int pageSize) {
        
        Map paginacion = new HashMap();
        paginacion.put("start", ""+ (start * Integer.parseInt(proper.getString("start"))));
        paginacion.put("pageSize", ""+(pageSize * Integer.parseInt(proper.getString("pageSize"))));
        
        return paginacion;
    }
    
    public static int getIndex(String start) {
        int value=0;
        if(start.equals("0") || start.equals("1")) {
            value= Integer.parseInt(proper.getString("start"));
        } else {
            value= (Integer.parseInt(start)-1) * Integer.parseInt(proper.getString("pageSize") );
        }
        
        return value;
    }
    
    public static int getPageSize(String start) {
        int value=0;
        if(start.equals("0")) {
            value= Integer.parseInt(proper.getString("pageSize"));
        } else {
            value= Integer.parseInt(start) * Integer.parseInt(proper.getString("pageSize"));
        }
        
        return value;
    }
    
    public static String paginas(String total) {
        double paginas=0;
        int pag =0;
        int size = Integer.parseInt(proper.getString("pageSize"));
        if(Integer.parseInt(total) < size) {
            pag=1;
        } else {
            paginas= Double.parseDouble(total)/size;
            pag=Integer.parseInt(total)/size;
            if(paginas>1) {
              pag= pag+1; 
            }
        }
        String retorna = String.valueOf(pag);
        System.out.println(retorna );
        return retorna;
    }
 
}