package cl.bicevida.core.view.jsf.mb;

import cl.bicevida.core.view.jsf.utils.JsfUtils;
import cl.bicevida.core.view.utils.BvWebLog;

import java.io.PrintWriter;

import java.io.StringWriter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class MbError {
    protected String mensajeAmigable = "";
    protected String stackTrace = "";
    protected String exceptionMessage = "";
    protected String outcome = "";
    protected String inicio = "";
    protected Exception lastException;
    protected boolean hasLogged = false;
    protected boolean hasSend = false;
    protected boolean showDetail = true;

    public MbError() {
    }

    public void prepareErrorPage( ActionEvent actionEvent, String mensajeAmigable, Exception ex, String volver, String inicio ) {
        this.mensajeAmigable = mensajeAmigable;
        this.lastException = ex;
        this.outcome = volver;
        this.inicio = inicio;
        this.hasLogged = false;
        this.hasSend = false;
        if ( ex != null ) {
            exceptionMessage = ex.getMessage();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter( sw );
            ex.printStackTrace( pw );
            stackTrace = sw.getBuffer().toString();
            showDetail = true;
        } else {
            showDetail = false;
        }
    }

    public void showError( String name, int nivelLog, ActionEvent actionEvent, String mensajeAmigable, Exception ex, String volver, String inicio ) {
        BvWebLog.log( name, nivelLog, mensajeAmigable, ex );
        this.hasLogged = true;
        if ( nivelLog == BvWebLog.LEVEL_ERROR || nivelLog == BvWebLog.LEVEL_FATAL ) {
            this.hasSend = true;
        }
        prepareErrorPage( actionEvent, mensajeAmigable, ex, volver, inicio );
        redirectToErrorPage();
    }

    public void showError( ActionEvent actionEvent, String mensajeAmigable, Exception ex, String volver, String inicio ) {
        prepareErrorPage( actionEvent, mensajeAmigable, ex, volver, inicio );
        redirectToErrorPage();
    }

    private void redirectToErrorPage() {
        JsfUtils.handleNavigation( "Error" );
        JsfUtils.getFacesContext().renderResponse();
        JsfUtils.getFacesContext().responseComplete();
    }

    public void setMensajeAmigable( String mensajeAmigable ) {
        this.mensajeAmigable = mensajeAmigable;
    }

    public String getMensajeAmigable() {
        return mensajeAmigable;
    }

    public void setOutcome( String outcome ) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setStackTrace( String stackTrace ) {
        this.stackTrace = stackTrace;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setInicio( String inicio ) {
        this.inicio = inicio;
    }

    public String getInicio() {
        return inicio;
    }

    public void setExceptionMessage( String exceptionMessage ) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setHasLogged( boolean hasLogged ) {
        this.hasLogged = hasLogged;
    }

    public boolean isHasLogged() {
        return hasLogged;
    }

    public void setHasSend( boolean hasSend ) {
        this.hasSend = hasSend;
    }

    public boolean isHasSend() {
        return hasSend;
    }

    public void setShowDetail( boolean showDetail ) {
        this.showDetail = showDetail;
    }

    public boolean isShowDetail() {
        return showDetail;
    }
}
