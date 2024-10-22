public class EditorTextoMVC {
    public static void main(String[] args) {
        Documento modelo = new Documento();
        EditorTextoVista vista = new EditorTextoVista();
        EditorTextoControlador controlador = new EditorTextoControlador(modelo, vista);
        
        vista.setVisible(true);
    }
}
