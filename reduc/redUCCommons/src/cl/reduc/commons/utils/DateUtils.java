package cl.reduc.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static String getDate() {
    	return new Date().toLocaleString();
    }
	
	public static String parseDate(String date) {
		SimpleDateFormat formateador = new SimpleDateFormat("DD/MM/YYYY");
		Date fecha=null;
		try {
		   fecha = formateador.parse(new Date().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		System.out.println(fecha.toString());
		return fecha.toString();
	}
	
	public static String dateToString(Date date) {
		String fecha="";
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			fecha = formateador.format(date);
			System.out.println(fecha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fecha;
	}
	
	public static String dateToString(Date date, String format) {
		String fecha="";
		try {
			SimpleDateFormat formateador = new SimpleDateFormat(format);
			fecha = formateador.format(date);
			System.out.println(fecha);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fecha;
	}
	
	public static String parseDate(String date, String format) {
		SimpleDateFormat formateador = new SimpleDateFormat(format);
		Date fecha=null;
		try {
		   fecha = formateador.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		System.out.println(fecha.toString());
		return fecha.toString();
	}
	
	public static String getHourOfDay(String hour, String tipo) {
		String response = "";
		String separate = hour.substring(0, hour.indexOf(":"));
		String rest = hour.substring(hour.indexOf(":"), hour.length() );
		if(tipo.trim().toUpperCase().equals("PM")) {
				switch(Integer.parseInt(separate)) {
					case 1: response="13";break;
					case 2: response="14";break;
					case 3: response="15";break;
					case 4: response="16";break;
					case 5: response="17";break;
					case 6: response="18";break;
					case 7: response="19";break;
					case 8: response="20";break;
					case 9: response="21";break;
					case 10: response="22";break;
					case 11: response="23";break;
					case 12: response="00";break;
				}
				response = response + rest;
		} else {
			response = hour;
		}
		return response;
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
	
	public static String validaMont(String month) {
    	String mes = "0";
    	if(Integer.parseInt(month)<10) {
    		mes = mes + month;
    	} else {
    		mes = month + "";
    	}
    	
    	return mes;
    }
    
}
