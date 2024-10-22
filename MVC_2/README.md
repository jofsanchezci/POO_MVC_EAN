
# Implementación del Modelo MVC en Java (Sistema de Gestión de Productos)

Esta es una implementación más interactiva del patrón Modelo-Vista-Controlador (MVC) en Java, que simula un sistema de gestión de productos en una tienda utilizando la consola como interfaz de usuario.

## Estructura del Proyecto

El proyecto contiene las siguientes clases:

1. **Producto.java** - Representa el modelo, que contiene los datos y la lógica de negocio del producto (nombre y precio).
2. **ProductoVista.java** - Representa la vista, que se encarga de interactuar con el usuario y mostrar los detalles del producto.
3. **ProductoControlador.java** - Representa el controlador, que actúa como intermediario entre el modelo y la vista.
4. **MVCProducto.java** - Clase principal que ejecuta el programa.

## Código

### 1. Producto.java
```java
public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
```

### 2. ProductoVista.java
```java
import java.util.Scanner;

public class ProductoVista {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarDetallesProducto(String nombre, double precio) {
        System.out.println("Detalles del Producto:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
    }

    public String obtenerNombreProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        return scanner.nextLine();
    }

    public double obtenerPrecioProducto() {
        System.out.print("Ingrese el precio del producto: ");
        return scanner.nextDouble();
    }

    public int mostrarMenu() {
        System.out.println("\nOpciones:");
        System.out.println("1. Mostrar detalles del producto");
        System.out.println("2. Modificar nombre del producto");
        System.out.println("3. Modificar precio del producto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }
}
```

### 3. ProductoControlador.java
```java
public class ProductoControlador {
    private Producto modelo;
    private ProductoVista vista;

    public ProductoControlador(Producto modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    vista.mostrarDetallesProducto(modelo.getNombre(), modelo.getPrecio());
                    break;
                case 2:
                    String nuevoNombre = vista.obtenerNombreProducto();
                    modelo.setNombre(nuevoNombre);
                    System.out.println("Nombre actualizado.");
                    break;
                case 3:
                    double nuevoPrecio = vista.obtenerPrecioProducto();
                    modelo.setPrecio(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }
}
```

### 4. MVCProducto.java (Clase principal)
```java
public class MVCProducto {
    public static void main(String[] args) {
        // Crear el modelo (Producto)
        Producto modelo = new Producto("Laptop", 1500.00);

        // Crear la vista
        ProductoVista vista = new ProductoVista();

        // Crear el controlador
        ProductoControlador controlador = new ProductoControlador(modelo, vista);

        // Iniciar la aplicación
        controlador.iniciar();
    }
}
```

## Pasos para Ejecutar

### Opción 1: Usando el terminal

1. Compilar los archivos Java:
   ```bash
   javac *.java
   ```

2. Ejecutar la clase principal:
   ```bash
   java MVCProducto
   ```

### Opción 2: Usando un IDE (Eclipse o IntelliJ)

1. Crear un nuevo proyecto.
2. Agregar las clases `Producto.java`, `ProductoVista.java`, `ProductoControlador.java` y `MVCProducto.java`.
3. Ejecutar la clase `MVCProducto` desde el IDE.

## Salida esperada

El programa mostrará un menú interactivo donde el usuario podrá ver y modificar los detalles de un producto.

```
Opciones:
1. Mostrar detalles del producto
2. Modificar nombre del producto
3. Modificar precio del producto
4. Salir
Seleccione una opción:
```
