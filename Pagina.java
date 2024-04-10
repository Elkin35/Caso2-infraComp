public class Pagina {

    private int id;
    private int bitDeReferenciado;
    private int bitDeModificado;
    private int categoria;

    private int tamanio = 0;

    public Pagina(int id, int tamanio) {
        this.id = id;
        this.bitDeReferenciado = -1;
        this.bitDeModificado = -1;
        this.tamanio = tamanio;
    }

    public int getCategoria() {
        if (bitDeReferenciado == 0 && bitDeModificado == 0) {
            categoria = 0;
        } else if (bitDeReferenciado == 0 && bitDeModificado == 1) {
            categoria = 1;
        } else if (bitDeReferenciado == 1 && bitDeModificado == 0) {
            categoria = 2;
        } else if (bitDeReferenciado == 1 && bitDeModificado == 1) {
            categoria = 3;
        }
        return categoria;
    }

    public int getId() {
        return id;
    }

    public int getBitDeReferenciado() {
        return bitDeReferenciado;
    }

    public void setBitDeReferenciado(int bitDeReferenciado) {
        this.bitDeReferenciado = bitDeReferenciado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBitDeModificado() {
        return bitDeModificado;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public void setBitDeModificado(int bitDeModificado) {
        this.bitDeModificado = bitDeModificado;
    }
    
    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    
}
