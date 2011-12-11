<%@ page contentType="text/plain;charset=windows-1252"%>
<%@ page import="javax.faces.context.FacesContext"%>
<%@ page import="cl.bicevida.core.view.jsf.mb.MbExcelVisor"%>
<%@ page import="cl.bicevida.core.view.jsf.utils.JsfUtils"%>

<%
    MbExcelVisor mbExcelVisor = (MbExcelVisor)JsfUtils.getValue("#{MbExcelVisor}");
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=\""+mbExcelVisor.getNombre()+"\"");
    out.clear();
    Object objeto = mbExcelVisor.getObjeto();
    String nombre = mbExcelVisor.getNombre();
    String[] titulos = mbExcelVisor.getTitulo().split(":");
    JsfUtils.adfFacesTableToExcel2((oracle.adf.view.faces.component.core.data.CoreTable)objeto, nombre, titulos, response.getOutputStream());
    out.close();            
%>
