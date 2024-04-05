package Caso_2.V2;

public class Pagina {

    private int id;
    private int bitDeModificado;
    private int bitDeLeido;

    private int tamanio = 0;

    public Pagina(int id, int bitDeModificado, int bitDeLeido, int tamanio) {
        this.id = id;
        this.bitDeModificado = bitDeModificado;
        this.bitDeLeido = bitDeLeido;
        this.tamanio = tamanio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBitDeModificado() {
        return bitDeModificado;
    }

    public void setBitDeModificado(int bitDeModificado) {
        this.bitDeModificado = bitDeModificado;
    }

    public int getBitDeValidez() {
        return bitDeLeido;
    }

    public void setBitDeValidez(int bitDeLeido) {
        this.bitDeLeido = bitDeLeido;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    
    
}
