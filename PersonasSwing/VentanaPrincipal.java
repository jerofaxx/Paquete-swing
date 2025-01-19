package PersonasSwing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private ListaPersonas lista;
    private Container contenedor;
    private JLabel nombre, apellidos, teléfono, dirección;
    private JTextField campoNombre, campoApellidos, campoTeléfono, campoDirección;
    private JButton añadir, eliminar, borrarLista, editar;
    private JList<String> listaNombres;
    private DefaultListModel<String> modelo;
    private JScrollPane scrollLista;

    public VentanaPrincipal() {
        lista = new ListaPersonas();
        inicio();
        setTitle("Personas");
        setSize(270,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        nombre = new JLabel("Nombre:");
        nombre.setBounds(20, 20, 135, 23);
        campoNombre = new JTextField();
        campoNombre.setBounds(105, 20, 135, 23);

        apellidos = new JLabel("Apellidos:");
        apellidos.setBounds(20, 50, 135, 23);
        campoApellidos = new JTextField();
        campoApellidos.setBounds(105, 50, 135, 23);

        teléfono = new JLabel("Teléfono:");
        teléfono.setBounds(20, 80, 135, 23);
        campoTeléfono = new JTextField();
        campoTeléfono.setBounds(105, 80, 135, 23);

        dirección = new JLabel("Dirección:");
        dirección.setBounds(20, 110, 135, 23);
        campoDirección = new JTextField();
        campoDirección.setBounds(105, 110, 135, 23);

        añadir = new JButton("Añadir");
        añadir.setBounds(105, 150, 80, 23);
        añadir.addActionListener(this);

        eliminar = new JButton("Eliminar");
        eliminar.setBounds(20, 300, 90, 23);
        eliminar.addActionListener(this);

        borrarLista = new JButton("Borrar Lista");
        borrarLista.setBounds(120, 300, 120, 23);
        borrarLista.addActionListener(this);

        editar = new JButton("Editar");
        editar.setBounds(105, 270, 80, 23);
        editar.addActionListener(this);

        listaNombres = new JList<>();
        listaNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo = new DefaultListModel<>();
        listaNombres.setModel(modelo);

        scrollLista = new JScrollPane(listaNombres);
        scrollLista.setBounds(20, 190 ,220, 80);

        contenedor.add(nombre);
        contenedor.add(campoNombre);
        contenedor.add(apellidos);
        contenedor.add(campoApellidos);
        contenedor.add(teléfono);
        contenedor.add(campoTeléfono);
        contenedor.add(dirección);
        contenedor.add(campoDirección);
        contenedor.add(añadir);
        contenedor.add(eliminar);
        contenedor.add(borrarLista);
        contenedor.add(editar);
        contenedor.add(scrollLista);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == añadir) {
            añadirPersona();
        }
        if (evento.getSource() == eliminar) {
            eliminarNombre(listaNombres.getSelectedIndex());
        }
        if (evento.getSource() == borrarLista) {
            borrarLista();
        }
        if (evento.getSource() == editar) {
            editarPersona();
        }
    }

    private void añadirPersona() {
        String nombre = campoNombre.getText();
        String apellidos = campoApellidos.getText();
        String telefono = campoTeléfono.getText();
        String direccion = campoDirección.getText();

        if (personaExiste(nombre, apellidos)) {
            JOptionPane.showMessageDialog(this, "La persona ya está en la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!nombre.isEmpty() && !apellidos.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty()) {
            Persona p = new Persona(nombre, apellidos, telefono, direccion);
            lista.añadirPersona(p);

            String elemento = nombre + " - " + apellidos + " - " + telefono + " - " + direccion;
            modelo.addElement(elemento);
            listaNombres.setModel(modelo);

            campoNombre.setText("");
            campoApellidos.setText("");
            campoTeléfono.setText("");
            campoDirección.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean personaExiste(String nombre, String apellidos) {
        for (int i = 0; i < lista.obtenerTamaño(); i++) {
            Persona p = lista.obtenerLista().get(i);
            if (p.getNombre().equals(nombre) && p.getApellidos().equals(apellidos)) {
                return true;
            }
        }
        return false;
    }

    private void eliminarNombre(int indice) {
        if (indice >= 0) {
            modelo.removeElementAt(indice);
            lista.eliminarPersona(indice);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarLista() {
        lista.borrarLista();
        modelo.clear();
    }

    private void editarPersona() {
        int indiceSeleccionado = listaNombres.getSelectedIndex();
        if (indiceSeleccionado >= 0) {
            Persona personaSeleccionada = lista.obtenerLista().get(indiceSeleccionado);

            campoNombre.setText(personaSeleccionada.getNombre());
            campoApellidos.setText(personaSeleccionada.getApellidos());
            campoTeléfono.setText(personaSeleccionada.getTelefono());
            campoDirección.setText(personaSeleccionada.getDireccion());

            lista.eliminarPersona(indiceSeleccionado);
            modelo.removeElementAt(indiceSeleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona para editar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
