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
package cl.bicevida.core.view.jsf.mb;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <br>La clase BackingLogout es un managed bean (de request) para manejar eventos
 * de la página infraestrutura/Logout.jspx.
 * @author Rodrigo López G.
 * @version 1.0, 20-03-2008
 * @since CoreWebApp1.0
 */
public class BackingLogout {
    public BackingLogout() {
    }

    public void logoutActionListener( ActionEvent actionEvent ) throws IOException {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = ( HttpServletResponse ) ectx.getResponse();
        HttpSession session = ( HttpSession ) ectx.getSession( false );
        String initPage = session.getServletContext().getInitParameter( "cl.bicevida.core.initPage" );
        session.invalidate();
        response.sendRedirect( "../faces/" + initPage );
    }
}
