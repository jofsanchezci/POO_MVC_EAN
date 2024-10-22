import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditorTextoVista extends JFrame {
    private JTextArea areaTexto;
    private JMenuItem abrirItem, guardarItem, salirItem;

    public EditorTextoVista() {
        setTitle("Editor de Texto MVC");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        JMenuBar barraMenu = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        abrirItem = new JMenuItem("Abrir");
        guardarItem = new JMenuItem("Guardar");
        salirItem = new JMenuItem("Salir");
        
        menuArchivo.add(abrirItem);
        menuArchivo.add(guardarItem);
        menuArchivo.add(salirItem);
        barraMenu.add(menuArchivo);
        
        setJMenuBar(barraMenu);
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public void setTexto(String texto) {
        areaTexto.setText(texto);
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
}
