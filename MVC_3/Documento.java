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
