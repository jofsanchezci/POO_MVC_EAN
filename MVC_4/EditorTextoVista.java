import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditorTextoVista extends JFrame {
    private JTextArea areaTexto;
    private JMenuItem nuevoItem, abrirItem, guardarItem, salirItem, colorItem, tamanoFuenteItem;

    public EditorTextoVista() {
        setTitle("Editor de Texto Mejorado - MVC");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        // Menú de archivo
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        nuevoItem = new JMenuItem("Nuevo");
        abrirItem = new JMenuItem("Abrir");
        guardarItem = new JMenuItem("Guardar");
        salirItem = new JMenuItem("Salir");
        menuArchivo.add(nuevoItem);
        menuArchivo.add(abrirItem);
        menuArchivo.add(guardarItem);
        menuArchivo.add(salirItem);
        barraMenu.add(menuArchivo);

        // Menú de edición
        JMenu menuEdicion = new JMenu("Edición");
        colorItem = new JMenuItem("Cambiar Color de Fuente");
        tamanoFuenteItem = new JMenuItem("Cambiar Tamaño de Fuente");
        menuEdicion.add(colorItem);
        menuEdicion.add(tamanoFuenteItem);
        barraMenu.add(menuEdicion);

        setJMenuBar(barraMenu);
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public void setTexto(String texto) {
        areaTexto.setText(texto);
    }

    public void setFontColor(Color color) {
        areaTexto.setForeground(color);
    }

    public void setFontSize(int size) {
        areaTexto.setFont(new Font("Arial", Font.PLAIN, size));
    }

    // Listeners para las nuevas funcionalidades
    public void agregarListenerNuevo(ActionListener listener) {
        nuevoItem.addActionListener(listener);
    }

    public void agregarListenerAbrir(ActionListener listener) {
        abrirItem.addActionListener(listener);
    }

    public void agregarListenerGuardar(ActionListener listener) {
        guardarItem.addActionListener(listener);
    }

    public void agregarListenerSalir(ActionListener listener) {
        salirItem.addActionListener(listener);
    }

    public void agregarListenerColorFuente(ActionListener listener) {
        colorItem.addActionListener(listener);
    }

    public void agregarListenerTamanoFuente(ActionListener listener) {
        tamanoFuenteItem.addActionListener(listener);
    }
}
