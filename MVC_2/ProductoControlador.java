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
