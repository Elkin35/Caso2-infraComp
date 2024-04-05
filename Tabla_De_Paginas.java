
import java.util.HashMap;


public class Tabla_De_Paginas {

    int tamanioTabla;

    HashMap<Integer, Integer> tabla;

    public Tabla_De_Paginas(int tamanioTabla) {
        this.tamanioTabla = tamanioTabla;
        tabla = new HashMap<>(tamanioTabla);
    }

    public void inicializarTabla() {
        for (int i = 0; i < tamanioTabla; i++) {
            tabla.put(i, -1);
        }
    }

    public void agregarEntrada(int pagina, int marco) {
        tabla.put(pagina, marco);
    }

    public int obtenerEntrada(int pagina) {
        return tabla.get(pagina);
    }

    public void eliminarEntrada(int pagina) {
        tabla.put(pagina, -1);
    }
    
}
