import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EditorTextoControlador {
    private Documento modelo;
    private EditorTextoVista vista;

    public EditorTextoControlador(Documento modelo, EditorTextoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.agregarListenerNuevo(new NuevoArchivoListener());
        vista.agregarListenerAbrir(new AbrirArchivoListener());
        vista.agregarListenerGuardar(new GuardarArchivoListener());
        vista.agregarListenerSalir(new SalirListener());
        vista.agregarListenerColorFuente(new CambiarColorFuenteListener());
        vista.agregarListenerTamanoFuente(new CambiarTamanoFuenteListener());
    }

    class NuevoArchivoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vista.setTexto("");
        }
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

    class CambiarColorFuenteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(vista, "Seleccione un color de fuente", Color.BLACK);
            if (color != null) {
                vista.setFontColor(color);
            }
        }
    }

    class CambiarTamanoFuenteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tamanoStr = JOptionPane.showInputDialog(vista, "Ingrese el tamaño de la fuente:", "12");
            if (tamanoStr != null) {
                try {
                    int tamano = Integer.parseInt(tamanoStr);
                    vista.setFontSize(tamano);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "Tamaño de fuente inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
