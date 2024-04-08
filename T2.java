/*
 * En este caso, la clase T2 es la encargada de ejecutar el algoritmo de actualización del bit R.
 * Es decir, borrar el bit R para todas las paginas de la tabla de paginas
 * Este thread debe correr cada 4 milisegundos
 */

public class T2 extends Thread {

    Tabla_De_Paginas tablaDePaginas;
    int nP;

    public T2 (Tabla_De_Paginas tablaDePaginas, int nP) {
        this.tablaDePaginas = tablaDePaginas;
        this.nP = nP;
    }

    public void run() {
        while (!Memoria.finEjecucionT1) {
            try {
                Thread.sleep(4); // Moved outside of synchronized block
                tablaDePaginas.actualizarPaginas();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
