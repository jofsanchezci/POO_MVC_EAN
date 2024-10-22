
# Implementación Mejorada de un Editor de Texto en Java usando el Modelo MVC

Este proyecto es una versión mejorada del editor de texto básico en Java utilizando el patrón de diseño Modelo-Vista-Controlador (MVC). En esta versión, se agregan las siguientes funcionalidades adicionales:

## Funcionalidades Mejoradas:

1. **Nuevo Documento**: Permite crear un nuevo documento vacío.
2. **Abrir Documento**: Permite abrir un archivo de texto existente.
3. **Guardar Documento**: Guarda el contenido del área de texto en un archivo.
4. **Cambiar Tamaño de la Fuente**: Permite cambiar el tamaño del texto en el área de texto.
5. **Cambiar Color de la Fuente**: Cambia el color del texto.

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
```

### 3. Controlador: EditorTextoControlador.java
```java
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
- **Nuevo Documento**: Limpia el área de texto para crear un nuevo documento vacío.
- **Abrir archivo**: Permite abrir un archivo de texto y mostrar su contenido en el área de texto.
- **Guardar archivo**: Guarda el contenido del área de texto en un archivo de texto.
- **Cambiar tamaño de la fuente**: Permite cambiar el tamaño del texto en el área de texto.
- **Cambiar color de la fuente**: Cambia el color del texto mostrado.

## Extensiones Posibles
- **Deshacer/Rehacer**: Implementar funcionalidad de deshacer y rehacer cambios.
- **Resaltado de Sintaxis**: Agregar resaltado de sintaxis para diferentes lenguajes.
- **Búsqueda de Texto**: Añadir funcionalidades de búsqueda y reemplazo de texto.

Este proyecto mejorado puede ser extendido para incluir más características avanzadas y una interfaz gráfica más rica.
