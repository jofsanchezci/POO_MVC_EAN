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
