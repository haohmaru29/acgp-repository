package cl.bicevida.core.view.jsf.mb;

import cl.bicevida.core.view.jsf.utils.JsfUtils;

import cl.bicevida.envionominas.model.utils.SimpleDate;

import java.util.Date;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.faces.component.core.data.CoreTable;

public class MbExcelVisor {
    protected Object objeto;
    protected String titulo;
    protected String nombre;
    
    public MbExcelVisor() {
    }

    public synchronized void setObjeto( Object objeto ) {
        this.objeto = objeto;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setTitulo( String titulo ) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void onClickGenerarExcel( ActionEvent actionEvent ) throws Exception {
        SimpleDate sd = new SimpleDate(new Date());
        UIComponent component = ( UIComponent ) actionEvent.getComponent();
        while ( !( component instanceof CoreTable ) && component != null ) {
            component = component.getParent();
        }
        setNombre((String)actionEvent.getComponent().getAttributes().get("nombre")+"-"+sd.getYear()+sd.getMonth()+sd.getDay()+sd.getHour()+sd.getMin()+".xls");
        setTitulo((String)actionEvent.getComponent().getAttributes().get("titulo"));
        setObjeto( component );
        System.out.println("###############TITULO:["+getTitulo()+"]###############");
        JsfUtils.handleNavigation( "excelVisor" );
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
