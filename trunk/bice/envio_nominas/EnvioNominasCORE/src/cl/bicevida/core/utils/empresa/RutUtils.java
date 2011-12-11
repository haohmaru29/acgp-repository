/*
  * ===========================================================================
  * Licensed Materials - Property of BiceVida
  * "Restricted Materials of BiceVida"
  * ===========================================================================
  * Created on 02-10-2007
  * BiceVida
  * ===========================================================================
  * Copyright 2007 BiceVida, Inc. All rights reserved.
  * ===========================================================================
  */
package cl.bicevida.core.utils.empresa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RutUtils {
    //TODO: soportar otros formatos de RUT además del formato "mediano".

    public static boolean isRutMediumFormat( String rut ) {
        boolean result = false;
        Pattern p = Pattern.compile( "[0-9]{1,8}-(?i)[\\dK]" );
        Matcher m = p.matcher( rut );
        result = m.matches();
        return result;
    }

    public static boolean esRutValido( String pRutEntero ) {
        int suma = 0, mul = 2;
        int dvi;
        int i = 0;
        String dvs;
        String pRut, pDv;
        String arr[];
        arr = pRutEntero.split( "-" );
        pRut = arr[ 0 ];
        pDv = arr[ 1 ].toUpperCase();
        for ( i = pRut.length() - 1; i >= 0; i-- ) {
            suma = suma + Integer.parseInt( String.valueOf( pRut.charAt( i ) ) ) * mul;
            mul = mul == 7 ? 2 : mul + 1;
        }
        dvi = ( 11 - suma % 11 );
        dvs = String.valueOf( dvi );
        if ( dvi == 10 )
            dvs = "K";
        else if ( dvi == 11 )
            dvs = "0";
        if ( dvs.equals( pDv ) )
            return true;
        else
            return false;
    }

    public static String formateaRUT( String rut ) {
        String aux, dv;
        String[] fin;
        aux = rut;
        if ( aux == null || aux.equals( "" ) )
            return "";
        if ( aux.indexOf( "-" ) < 0 ) {
            aux = aux.substring( 0, aux.length() - 1 ) + "-" + aux.substring( aux.length() - 1, aux.length() );
        }
        if ( aux.indexOf( "." ) < 0 ) {
            aux = "000000000000|" + aux;
            aux = aux.substring( aux.length() - 10, aux.length() );
            aux = aux.substring( 0, 2 ) + "." + aux.substring( 2, 5 ) + "." + aux.substring( 5, 10 );
        }
        fin = aux.split( "\\|" );
        if ( fin.length == 1 )
            return fin[ 0 ];
        else
            return fin[ 1 ];
    }

    public static String quitaPuntos( String rut ) {
        return rut.replaceAll( "[.]", "" );
    }

    public static String quitaDv( String rut ) {
        String[] arr = rut.split( "-" );
        if ( arr.length > 0 )
            return arr[ 0 ];
        else
            return rut;
    }

    public static String calculaDv( Long pElRut ) {
        int suma = 0, mul = 2;
        int dvi;
        int i = 0;
        String dvs;
        String pRut;
        pRut = pElRut.toString();
        for ( i = pRut.length() - 1; i >= 0; i-- ) {
            suma = suma + Integer.parseInt( String.valueOf( pRut.charAt( i ) ) ) * mul;
            mul = mul == 7 ? 2 : mul + 1;
        }
        dvi = ( 11 - suma % 11 );
        dvs = String.valueOf( dvi );
        if ( dvi == 10 )
            dvs = "K";
        else if ( dvi == 11 )
            dvs = "0";
        return dvs;
    }
}
