package cl.reduc.commons.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

      public static String validaMont(String month) {
            String mes = "0";
            if(Integer.parseInt(month)<10) {
              mes = mes + month;
            } else {
              mes = month + "";
            }
            
            return mes;
      }

      public static String nextDay(int dia, int mes, int anio) {
            String response = "";
            Calendar cal = GregorianCalendar.getInstance();
            cal.set(anio, mes-1, dia);
            int ultimoDia = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            
            if(ultimoDia < (dia + 1)) {
              response = "01" + DateUtils.getMesSiguiente(mes+ "") + DateUtils.getAnioSegunMes(anio+ "" , mes + "");
            } else {
              response = DateUtils.validaMont((dia+1) +"") + "" + DateUtils.validaMont(mes + "") + "" + anio;
            }
            
            return  response;
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
      
    public static String parseTimestamp(String date){
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        Date tmp = null;
        try {
            tmp = df.parse( date );
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        df = new SimpleDateFormat("dd/MM/yyyy");  
        return df.format(tmp).toString();
    }
      
    public static String fechaActual() {
        Calendar cal = new GregorianCalendar();
        String fecha = cal.get(Calendar.YEAR) + "" + parse(cal.get(Calendar.MONTH)+1) + "" + parse(cal.get(Calendar.DAY_OF_MONTH)) + "";
        
        System.out.println(fecha);
        return fecha;
    }
      
      public static String horaMinutoSegundo() {
          Calendar cal = new GregorianCalendar();
          String time = parse(cal.get(Calendar.HOUR_OF_DAY)) + "" + parse(cal.get(Calendar.MINUTE)) + "" + parse(cal.get(Calendar.SECOND));
          return time;
      }
      
      public static String parse(int date) {
          String mes = "0";
          if(date<10) {
            mes=mes + date;
          } else {
            mes= date + "";
          }
      
          return mes;
      }
      
      public static String getDate() {
        return new Date().toString();
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

}