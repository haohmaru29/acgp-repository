package cl.reduc.reportes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

public class DateUtils {

	public static String getMesAnterior(String mes) {
        if(mes.equals("01") || mes.equals("1")) {
            mes = "12";
        }  else {
            mes = String.valueOf(Integer.parseInt(mes)-1);
        }
        
        return mes;
    }
	
	public static String getAnioSegunMes(String anio, String mes) {
		String response = "";
		if(mes.equals("12")) {
			response = String.valueOf(Integer.parseInt(anio) +1);
		} else {
			response= anio;
		}
		
		
		return response;
	}
	
	public static String getMesSiguiente(String mes) {
        if(mes.equals("12") ) {
            mes = "01";
        }  else {
        	if((Integer.parseInt(mes)+1)>9) {
        		mes = String.valueOf(Integer.parseInt(mes)+1);
        	} else { 
        		mes = "0" + String.valueOf(Integer.parseInt(mes)+1);
        	}
        }
        
        return mes;
    }
    
    public static String getAnioAnterior(String anio) {
        anio = String.valueOf(Integer.parseInt(anio)-1);
        
        return anio;
    }
    
    public static String getDate() {
        Calendar c = Calendar.getInstance();
        String fecha =c.get(Calendar.DATE) + "/" + getMes(c.get(Calendar.MONTH)) + "/" + c.get(Calendar.YEAR);
        
        return fecha;
    }
    
    public static void loadMeses(JComboBox combo) {
        for(int x=1;x<=12;x++) {
            combo.addItem(getMes(x));
        }
    }
    
    public static String getMes(int mes) {
        String value = mes + "";
        int mesSol = Integer.parseInt(value);
        if(mesSol==1) {
            value = "ENERO";
        } else if(mesSol==2) {
            value = "FEBRERO";
        } else if(mesSol==3) {
            value = "MARZO";
        } else if(mesSol==4) {
            value = "ABRIL";
        } else if(mesSol==5) {
            value = "MAYO";
        } else if(mesSol==6) {
            value = "JUNIO";
        } else if(mesSol==7) {
            value = "JULIO";
        } else if(mesSol==8) {
            value = "AGOSTO";
        } else if(mesSol==9) {
            value = "SEPTIEMBRE";
        } else if(mesSol==10) {
            value = "OCTUBRE";
        } else if(mesSol==11) {
            value = "NOVIEMBRE";
        } else if(mesSol==12) {
            value = "DICIEMBRE";
        } 
    
        return value;
    }
  
    public static String getMesPorNombre(String nombreMes) {
        String value="0";
        if(nombreMes.trim().toUpperCase().equals("ENERO")) {
            value="01";
        } else if(nombreMes.trim().toUpperCase().equals("FEBRERO")) {
            value="02";
        } else if(nombreMes.trim().toUpperCase().equals("MARZO")) {
            value="03";
        } else if(nombreMes.trim().toUpperCase().equals("ABRIL")) {
            value="04";
        }  else if(nombreMes.trim().toUpperCase().equals("MAYO")) {
            value="05";
        } else if(nombreMes.trim().toUpperCase().equals("JUNIO")) {
            value="06";
        } else if(nombreMes.trim().toUpperCase().equals("JULIO")) {
            value="07";
        } else if(nombreMes.trim().toUpperCase().equals("AGOSTO")) {
            value="08";
        } else if(nombreMes.trim().toUpperCase().equals("SEPTIEMBRE")) {
            value="09";
        } else if(nombreMes.trim().toUpperCase().equals("OCTUBRE")) {
            value="10";
        } else if(nombreMes.trim().toUpperCase().equals("NOVIEMBRE")) {
            value="11";
        } else if(nombreMes.trim().toUpperCase().equals("DICIEMBRE")) {
            value="12";
        }
        
        return value;
    }
    
    public static String validaMont(String month) {
    	String mes = "0";
    	if(Integer.parseInt(month)<10) {
    		mes = mes + month;
    	} else {
    		mes = month + "";
    	}
    	
    	return mes;
    }
    
    public String parseDate(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("dd/mm/yyyy");
        Date fecha = new Date();
        try {
            fecha = sd.parse(date.toLocaleString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        return fecha.toLocaleString();
    }
    
    public static String nextDay(int dia, int mes, int anio) {
    	String response = "";
    	Calendar cal = GregorianCalendar.getInstance();
    	cal.set(anio, mes-1, dia);
    	int ultimoDia = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    	
    	if(ultimoDia < (dia +1)) {
    		response = "01" + DateUtils.getMesSiguiente(mes+ "") + DateUtils.getAnioSegunMes(anio+ "" , mes + "");
    	} else {
    		response = DateUtils.validaMont((dia + 1) +"") + "" + DateUtils.validaMont(mes + "") + "" + anio;
    	}
    	
    	return  response;
    }
}
