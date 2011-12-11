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
package cl.bicevida.core.view.jsf.utils;

import cl.bicevida.envionominas.model.utils.ExcelGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.OutputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.convert.Converter;
import javax.faces.el.ValueBinding;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.view.faces.component.UIXValue;
import oracle.adf.view.faces.component.core.data.CoreColumn;
import oracle.adf.view.faces.component.core.data.CoreTable;
import oracle.adf.view.faces.context.AdfFacesContext;

import oracle.adfinternal.view.faces.uinode.UINodePropertyKey;

/**
 * <br>La clase JsfUtils proporciona utilidades para manejar tareas específicas
 * de JSF que requieren varias líneas de código (tedioso).
 * @author Rodrigo López G.
 * @version 1.0, 20-03-2008
 * @since CoreWebApp1.0
 */
public class JsfUtils {
    public JsfUtils() {
    }

    public static FacesContext getFacesContext() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx;
    }

    public static AdfFacesContext getAdfFacesContext() {
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        return ctx;
    }

    public static Object getValue( String el ) {
        FacesContext ctx = getFacesContext();
        return ctx.getApplication().createValueBinding( el ).getValue( ctx );
    }

    public static void handleNavigation( String action ) {
        FacesContext ctx = getFacesContext();
        NavigationHandler nh = ctx.getApplication().getNavigationHandler();
        nh.handleNavigation( ctx, null, action );
    }

    public static ExternalContext getExternalContext() {
        return JsfUtils.getFacesContext().getExternalContext();
    }

    public String getViewId() {
        UIViewRoot viewRoot = getFacesContext().getViewRoot();
        String viewId = viewRoot.getViewId();
        return viewId;
    }

    public static void logout() {
        //HttpServletResponse response = (HttpServletResponse)getExternalContext().getResponse();
        HttpSession session = ( HttpSession ) getExternalContext().getSession( false );
        session.invalidate();
    }

    public static UIComponent findComponent( String id ) {
        return getFacesContext().getViewRoot().findComponent( id );
    }

    private static void getAllUIXValue( List<UIComponent> childrens, List<UIXValue> outList ) {
        if ( childrens == null || childrens.size() == 0 )
            return;
        for ( UIComponent c: childrens ) {
            if ( c instanceof UIXValue ) {
                outList.add( ( UIXValue ) c );
            }
            getAllUIXValue( c.getChildren(), outList );
        }
    }

    public static void adfFacesTableToExcel2( CoreTable table, String nombre, String[] titulos, OutputStream outputStream ) throws IllegalAccessException, InvocationTargetException, FileNotFoundException, IOException {
        List<String> titulosList = new ArrayList<String>();
        List<List<String>> datos = new ArrayList<List<String>>();
        List<String> fila = new ArrayList<String>();
        List<UIXValue> outList = new ArrayList<UIXValue>();
        String[][] valores = new String[ 0 ][ 0 ];
        int currentIndex;
        currentIndex = table.getRowIndex();
        /*** Obtener los hijos de priner de la Tabla***/
        List<UIComponent> componentes = table.getChildren();
        /*** Se recorren las filas de la tabla
          *   Por cada fila se toman todos los objetos column.
          *   Por cada objeto column se toman todos aquellos componentes hijos
          *   que son capaces de desplegar valores (UIXValue).
          *   Luego se toman todos los valores de estos componentes UIXValue
          *   y se concatenan en un único String.
          ***/
        for ( int i = 0; i < table.getRowCount(); i++ ) {
            table.setRowIndex( i );
            fila = new ArrayList<String>();
            for ( UIComponent comp: componentes ) {
                if ( comp instanceof CoreColumn ) {
                    CoreColumn column = ( CoreColumn ) comp;
                    outList.clear();
                    getAllUIXValue( column.getChildren(), outList );
                    String str = "";
                    for ( UIXValue component: outList ) {
                        Iterator iter = component.getFacesBean().bindingKeySet().iterator();
                        if ( iter == null || !iter.hasNext() )
                            str = str + "" + component.getValue();
                        while ( iter.hasNext() ) {
                            UINodePropertyKey pk = ( UINodePropertyKey ) iter.next();
                            System.out.println( pk.getName() );
                            ValueBinding vb = component.getFacesBean().getValueBinding( pk );
                            if ( vb != null && vb.getValue( JsfUtils.getFacesContext() ) != null ) {
                                Converter conv = component.getConverter();
                                if ( conv != null )
                                    str = str + "" + conv.getAsString( JsfUtils.getFacesContext(), component, vb.getValue( JsfUtils.getFacesContext() ) );
                                else
                                    str = str + "" + vb.getValue( JsfUtils.getFacesContext() ).toString();
                            } else {
                                System.out.println( "***" );
                                str = str + "" + component.getValue();
                            }
                        }
                    }
                    fila.add( str.trim() );
                }
            }
            datos.add( fila );
        }
        valores = new String[ datos.size() ][ fila.size() ];
        for ( int i = 0; i < datos.size(); i++ ) {
            for ( int j = 0; j < titulos.length; j++ ) {
                if ( datos.get( i ) != null && datos.get( i ).get( j ) != null && !"null".equals(datos.get( i ).get( j ).trim()))
                    valores[ i ][ j ] = datos.get( i ).get( j );
                else
                    valores[ i ][ j ] = " ";
            }
        }
        table.setRowIndex( currentIndex );
        ExcelGenerator.generateExcel( nombre, titulos, valores, outputStream );
    }

    public static void redirect( String url ) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = ( HttpServletResponse ) context.getExternalContext().getResponse();
        try {
            response.sendRedirect( url );
        } catch ( IOException e ) {
            
        }
    }

    public static List getObjetosSeleccionados( CoreTable table ) {
        List lista = new ArrayList();
        Set rowSet = table.getSelectionState().getKeySet();
        if ( table.getRowIndex() >= 0 ) {
            lista.add( table.getRowData() );
        } else {
            Iterator rowSetIter = rowSet.iterator();
            while ( rowSetIter.hasNext() ) {
                String key = ( String ) rowSetIter.next();
                table.setRowKey( key );
                Object obj = table.getRowData();
                lista.add( obj );
            }
        }
        return lista;
    }
}
