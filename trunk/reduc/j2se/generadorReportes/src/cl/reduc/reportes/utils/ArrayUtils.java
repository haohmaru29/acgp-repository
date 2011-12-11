package cl.reduc.reportes.utils;

import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

public class ArrayUtils {

	public static void recorreArray(List array, JComboBox combo) {
        Iterator it = array.iterator();
        while(it.hasNext()) {
            combo.addItem(it.next());
        }
    }
	
	public static void loadList(List array, DefaultListModel model ) {
		for(int x=0; x<array.size();x++) {
			model.add(x, array.get(x));
		}
		
	}
	
	public static void loadTipoAtencion(JComboBox combo) {
        combo.addItem("TODOS");
        /*
        combo.addItem("MEDICAS");
        combo.addItem("NO MEDICAS");
        combo.addItem("PROCEDIMIENTOS");
        combo.addItem("EXAMENES");*/
    }
	
	public static void loadEspecialidades(JComboBox combo) {
        combo.addItem("TODOS");
        combo.addItem("ANCORA");
        combo.addItem("CARDIOLOGIA");
        combo.addItem("CIRUGIA DIGESTIVA");
        combo.addItem("DERMATOLOGIA");
        combo.addItem("ENDOCRINOLOGIA");
        combo.addItem("GASTROENTEROLOGIA");
        combo.addItem("MEDICINA DEPORTIVA");
        combo.addItem("MEDICINA FAMILIAR");
        combo.addItem("MEDICINA INTERNA");
        combo.addItem("NEUROLOGIA");
        combo.addItem("NEUROCIRUJIA");
        combo.addItem("NEFROLOGIA");
        combo.addItem("NUTRICION");
        combo.addItem("obstetricia y ginecologia".toUpperCase());
        combo.addItem("OFTALMOLOGIA");
        combo.addItem("otorrinolaringologia".toUpperCase());
        combo.addItem("PEDIATRIA");
        combo.addItem("PALIATIVO");
        combo.addItem("PSIQUIATRIA");
        combo.addItem("RESPIRATORIO");
        combo.addItem("REUMATOLOGIA");
        combo.addItem("TRAUMATOLOGIA");
        combo.addItem("KINESIOLOGIA");
        combo.addItem("CENTROS MEDICOS");
        
    }
}
