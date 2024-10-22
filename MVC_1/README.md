
# Implementación del Modelo MVC en Java

Esta es una implementación básica del patrón Modelo-Vista-Controlador (MVC) en Java. El ejemplo gestiona la información de un usuario en una aplicación de consola.

## Estructura del Proyecto

El proyecto contiene las siguientes clases:

1. **Usuario.java** - Representa el modelo, que contiene los datos y la lógica de negocio del usuario.
2. **UsuarioVista.java** - Representa la vista, que se encarga de mostrar los detalles del usuario.
3. **UsuarioControlador.java** - Representa el controlador, que actúa como intermediario entre el modelo y la vista.
4. **MVCPatron.java** - Clase principal que ejecuta el programa.

## Código

### 1. Usuario.java
```java
public class Usuario {
    private String nombre;
    private String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

### 2. UsuarioVista.java
```java
public class UsuarioVista {
    public void mostrarDetallesUsuario(String nombre, String email) {
        System.out.println("Detalles del Usuario:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }
}
```

### 3. UsuarioControlador.java
```java
public class UsuarioControlador {
    private Usuario modelo;
    private UsuarioVista vista;

    public UsuarioControlador(Usuario modelo, UsuarioVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void setNombreUsuario(String nombre) {
        modelo.setNombre(nombre);
    }

    public String getNombreUsuario() {
        return modelo.getNombre();
    }

    public void setEmailUsuario(String email) {
        modelo.setEmail(email);
    }

    public String getEmailUsuario() {
        return modelo.getEmail();
    }

    public void actualizarVista() {
        vista.mostrarDetallesUsuario(modelo.getNombre(), modelo.getEmail());
    }
}
```

### 4. MVCPatron.java (Clase principal)
```java
public class MVCPatron {
    public static void main(String[] args) {
        // Crear el modelo (Usuario)
        Usuario modelo = new Usuario("Carlos Perez", "carlos@example.com");

        // Crear la vista
        UsuarioVista vista = new UsuarioVista();

        // Crear el controlador
        UsuarioControlador controlador = new UsuarioControlador(modelo, vista);

        // Actualizar la vista inicial
        controlador.actualizarVista();

        // Modificar los datos del modelo a través del controlador
        controlador.setNombreUsuario("Juan Gomez");
        controlador.setEmailUsuario("juan.gomez@example.com");

        // Actualizar la vista con los nuevos datos
        controlador.actualizarVista();
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
   java MVCPatron
   ```

### Opción 2: Usando un IDE (Eclipse o IntelliJ)

1. Crear un nuevo proyecto.
2. Agregar las clases `Usuario.java`, `UsuarioVista.java`, `UsuarioControlador.java` y `MVCPatron.java`.
3. Ejecutar la clase `MVCPatron` desde el IDE.

## Salida esperada

La salida en consola debería verse de la siguiente manera:
```
Detalles del Usuario:
Nombre: Carlos Perez
Email: carlos@example.com
Detalles del Usuario:
Nombre: Juan Gomez
Email: juan.gomez@example.com
```
