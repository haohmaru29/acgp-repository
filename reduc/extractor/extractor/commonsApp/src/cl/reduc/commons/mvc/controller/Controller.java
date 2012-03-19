package cl.reduc.commons.mvc.controller;
import cl.reduc.commons.odbc.orm.ResultManager;
import java.util.HashMap;
import java.util.Map;

public abstract class Controller {
    
    private ResultManager resultManager;
    protected static Map namedQuery = new HashMap();
    protected static Map namedProcedure = new HashMap();
    

    static {
        namedQuery.put("select", "SELECT * FROM :table");
        namedQuery.put("selectById","SELECT * FROM :table WHERE :pk = ?");
        namedQuery.put("selectByFields","SELECT * FROM :table WHERE :keys");
        
        namedQuery.put("insert","INSERT INTO :table (:fields) VALUES (:values)");
        namedQuery.put("update","UPDATE :table SET :set WHERE :pk=? ");
    }
    
    public Controller() {
        resultManager = new ResultManager();
    }
    
    public void loadController(){
    
    }
}