package cl.bicevida.envionominas.model.bo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ListaDistribucionBO implements Serializable {
    private Long id;
    private String nombreLista;
    private String origenLista;

    public ListaDistribucionBO() {
    }

    public Long getId() {
        return id;
    }

    public void setId( Long newId ) {
        id = newId;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista( String newNombreLista ) {
        nombreLista = newNombreLista;
    }

    public String getOrigenLista() {
        return origenLista;
    }

    public void setOrigenLista( String newOrigenLista ) {
        origenLista = newOrigenLista;
    }

    public List<ListaDistribucionBO> getListasDistribucion() {
        List<ListaDistribucionBO> registros = new ArrayList<ListaDistribucionBO>();
        return registros;
    }

    public static ListaDistribucionBO crear( Long id, String origenLista, String nombreLista ) {
        return new ListaDistribucionBO( id, origenLista, nombreLista );
    }

    public ListaDistribucionBO( Long id, String origenLista, String nombreLista ) {
        this.id = id;
        this.origenLista = origenLista;
        this.nombreLista = nombreLista;
    }

    public String toString() {
        return "ListaDistribucionBO{" + "id=" + id + ", nombreLista=" + nombreLista + ", origenLista=" + origenLista + "}";
    }
}
