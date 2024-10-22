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
