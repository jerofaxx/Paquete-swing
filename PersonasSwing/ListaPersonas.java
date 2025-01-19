package PersonasSwing;
import java.util.*;

public class ListaPersonas {
    private Vector<Persona> listaPersonas;
    
    public ListaPersonas() {
        listaPersonas = new Vector<>();
    }

    public void añadirPersona(Persona p) {
        listaPersonas.add(p);
    }

    public void eliminarPersona(int i) {
        if (i >= 0 && i < listaPersonas.size()) {
            listaPersonas.removeElementAt(i);
        }
    }

    public void borrarLista() {
        listaPersonas.removeAllElements();
    }

    public Vector<Persona> obtenerLista() {
        return listaPersonas;
    }

    public int obtenerTamaño() {
        return listaPersonas.size();
    }
}
