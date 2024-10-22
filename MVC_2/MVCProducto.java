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
