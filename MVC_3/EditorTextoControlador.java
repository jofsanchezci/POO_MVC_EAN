import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class EditorTextoControlador {
    private Documento modelo;
    private EditorTextoVista vista;

    public EditorTextoControlador(Documento modelo, EditorTextoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.agregarListenerAbrir(new AbrirArchivoListener());
        vista.agregarListenerGuardar(new GuardarArchivoListener());
        vista.agregarListenerSalir(new SalirListener());
    }

    class AbrirArchivoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showOpenDialog(vista);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                try {
                    modelo.abrirArchivo(archivo);
                    vista.setTexto(modelo.getContenido());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    class GuardarArchivoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showSaveDialog(vista);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                try {
                    modelo.setContenido(vista.getTexto());
                    modelo.guardarArchivo(archivo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al guardar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    class SalirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
