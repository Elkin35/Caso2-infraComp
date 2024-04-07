
public class Marco {

    private int id;
    private int tamanio = 0;
    private int paginaAlmacenada;

    public Marco(int id, int tamanio) {
        this.id = id;
        this.tamanio = tamanio;
        paginaAlmacenada = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getPaginaAlmacenada() {
        return paginaAlmacenada;
    }

    public void setPaginaAlmacenada(int paginaAlmacenada) {
        this.paginaAlmacenada = paginaAlmacenada;
    }
    
}
