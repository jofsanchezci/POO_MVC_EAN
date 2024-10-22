
# Implementación de un Editor de Texto en Java usando el Modelo MVC

Este proyecto es una implementación básica de un editor de texto en Java utilizando el patrón de diseño Modelo-Vista-Controlador (MVC). El editor permite abrir, modificar y guardar archivos de texto, todo dentro de una interfaz gráfica creada con Swing.

## Estructura del Proyecto

El proyecto está dividido en tres componentes principales:

1. **Modelo (Documento.java)**: Maneja el contenido del archivo y las operaciones de abrir y guardar.
2. **Vista (EditorTextoVista.java)**: Proporciona la interfaz gráfica para la edición de texto y las opciones del menú.
3. **Controlador (EditorTextoControlador.java)**: Gestiona la interacción entre el modelo y la vista, respondiendo a las acciones del usuario.

## Código

### 1. Modelo: Documento.java
```java
import java.io.*;

public class Documento {
    private String contenido;

    public Documento() {
        contenido = "";
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void abrirArchivo(File archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        StringBuilder builder = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null) {
            builder.append(linea).append("\n");
        }
        contenido = builder.toString();
        reader.close();
    }

    public void guardarArchivo(File archivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        writer.write(contenido);
        writer.close();
    }
}
```

### 2. Vista: EditorTextoVista.java
```java
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
```

### 3. Controlador: EditorTextoControlador.java
```java
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
```

### 4. Clase Principal: EditorTextoMVC.java
```java
public class EditorTextoMVC {
    public static void main(String[] args) {
        Documento modelo = new Documento();
        EditorTextoVista vista = new EditorTextoVista();
        EditorTextoControlador controlador = new EditorTextoControlador(modelo, vista);
        
        vista.setVisible(true);
    }
}
```

## Instrucciones de Ejecución

### Compilación y Ejecución
1. **Compilar**:
   ```bash
   javac *.java
   ```

2. **Ejecutar**:
   ```bash
   java EditorTextoMVC
   ```

### Funcionalidades:
- **Abrir archivo**: Permite abrir un archivo de texto y mostrar su contenido en el área de texto.
- **Guardar archivo**: Guarda el contenido del área de texto en un archivo de texto.
- **Salir**: Cierra la aplicación.

## Extensiones Posibles
- **Deshacer/Rehacer**: Implementar funcionalidad de deshacer y rehacer cambios.
- **Resaltado de Sintaxis**: Agregar resaltado de sintaxis para diferentes lenguajes.
- **Búsqueda de Texto**: Añadir funcionalidades de búsqueda y reemplazo de texto.

Este proyecto básico puede ser extendido para incluir más características avanzadas y una interfaz gráfica más rica.
