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
