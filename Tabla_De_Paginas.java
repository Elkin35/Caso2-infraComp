
import java.util.HashMap;

public class Tabla_De_Paginas {

    int tamanioTabla;

    HashMap<Integer, Integer> tabla;
    HashMap<Integer, Pagina> idPaginas;
    HashMap<Integer, Integer> categorias = new HashMap<>();

    MemoriaFisica memoriaFisica;

    public Tabla_De_Paginas(int tamanioTabla, int tamanioPagina, MemoriaFisica memoriaFisica) {
        this.tamanioTabla = tamanioTabla;
        tabla = new HashMap<>(tamanioTabla);
        idPaginas = new HashMap<>(tamanioTabla);
        this.memoriaFisica = memoriaFisica;

        // Se llena la tabla de paginas vacias
        for (int i = 0; i < tamanioTabla; i++) {
            Pagina pagina = new Pagina(i, tamanioPagina);
            tabla.put(i, -1); // i es el id de la pagina y -1 indica que no hay ninguna pagina en ese marco
            idPaginas.put(i, pagina); // Se guarda el id de la pagina con la pagina para obtener las paginas con el id
        }
    }

    public synchronized Pagina getPagina(int id) { // Retorna la pagina con el id
        return idPaginas.get(id);
    }

    public synchronized void putMarco(int pagina, int marco) { // Se guarda la pagina en el marco
        tabla.put(pagina, marco);
    }

    public synchronized int getMarco(int pagina) { // Retorna el marco en el que se encuentra la pagina
        return tabla.get(pagina);
    }

    public synchronized void removeMarco(int pagina) { // Se elimina la pagina de la tabla colocandola en -1
        tabla.put(pagina, -1);
    }

    public synchronized int getTamanioTabla() {
        return tamanioTabla;
    }

    public synchronized void setTamanioTabla(int tamanioTabla) {
        this.tamanioTabla = tamanioTabla;
    }

    public synchronized HashMap<Integer, Integer> getTabla() {
        return tabla;
    }

    public synchronized void setTabla(HashMap<Integer, Integer> tabla) {
        this.tabla = tabla;
    }

    public synchronized HashMap<Integer, Pagina> getIdPaginas() {
        return idPaginas;
    }

    public synchronized void setIdPaginas(HashMap<Integer, Pagina> idPaginas) {
        this.idPaginas = idPaginas;
    }


    public synchronized void updateCategorias() {
        for (int i = 0; i < tamanioTabla; i++) {
            categorias.put(i, idPaginas.get(i).getCategoria());
        }
    }

    public synchronized Integer[] getCategorias() {
        updateCategorias();
        Object[] categoriasObj = categorias.values().toArray();

        Integer[] categorias = new Integer[categoriasObj.length];

        for (int i = 0; i < categoriasObj.length; i++) {
            categorias[i] = (Integer) categoriasObj[i];
        }
        
        return categorias;
    }



    public synchronized Integer[] getPaginaARemover() {
        updateCategorias();

        Marco[] marcos = memoriaFisica.getMarcos();

        Pagina paginaReemplazo = null;
        
        for (Marco marco : marcos) {
            Pagina pagina = idPaginas.get(marco.getPaginaAlmacenada());
            if (paginaReemplazo == null) {
                paginaReemplazo = pagina;
            } else if (pagina.getCategoria() < paginaReemplazo.getCategoria()) {
                paginaReemplazo = pagina;
            } 
        }

        Integer[] retorno = new Integer[2];

        retorno[0] = paginaReemplazo.getId();
        retorno[1] = getMarco(paginaReemplazo.getId());

        return retorno;
    }

    public synchronized void actualizarPaginas() {
        for (int i = 0; i < tamanioTabla; i++) {
            idPaginas.get(i).setBitDeReferenciado(0);
        }
    }

    
}
