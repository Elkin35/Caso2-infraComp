/* Este es el Thread encargado de ir actualizando el estado de la tabla de paginas y de
la memoria fisica (ram) de acuerdo con las referencias generadas por el proceso y el 
número de marcos de página asignados. Este thread debe correr cada milisegundo */

import java.io.BufferedReader;
import java.io.IOException;

public class T1 extends Thread {

    Tabla_De_Paginas tablaDePaginas;
    MemoriaFisica memoriaFisica;
    BufferedReader bf;
    int nR;

    int repeticiones;

    Integer[] categorias;

    public T1(Tabla_De_Paginas tablaDePaginas, MemoriaFisica memoriaFisica, BufferedReader bf, int nR) {
        this.tablaDePaginas = tablaDePaginas;
        this.memoriaFisica = memoriaFisica;
        this.bf = bf;
        this.nR = nR;

        repeticiones = nR;
    }

    public void run() {
        agregarMarco();
    }

    public void agregarMarco() {

        while (repeticiones > 0) {
            synchronized (Lock.lock) {
            

            // Se actualiza la tabla de paginas y la memoria fisica
            // for (int i = 0; i < nR; i++) {
                String linea;
                try {
                    linea = bf.readLine();
                    String[] partes = linea.split(",");

                    int pagina = Integer.parseInt(partes[1]);
                    String operacion = partes[3];


                    if (tablaDePaginas.getMarco(pagina) == -1) {

                        Memoria.misses++;
                        Memoria.tiempo += 10; // 10ms

                        if( memoriaFisica.getMarcosDisponibles() > 0) {

                            int marco = memoriaFisica.buscarPrimerMarcoDisponible();
                            tablaDePaginas.putMarco(pagina, marco);
                            memoriaFisica.getMarcos()[marco].setPaginaAlmacenada(pagina);
                            memoriaFisica.setMarcosDisponibles(memoriaFisica.getMarcosDisponibles() - 1);

                            if (operacion.equals("W")) {
                                tablaDePaginas.getPagina(pagina).setBitDeModificado(1);
                                tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                            } else {
                                tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                            }

                        } else {

                            Integer[] primeraPaginaARemover = tablaDePaginas.getPaginaARemover();  // La primera posicion es la pagina a remover y la segunda es el marco
                            int paginaARemover = primeraPaginaARemover[0];
                            int marco = primeraPaginaARemover[1];

                            tablaDePaginas.removeMarco(paginaARemover);
                            tablaDePaginas.getPagina(paginaARemover).setBitDeModificado(0);
                            tablaDePaginas.getPagina(paginaARemover).setBitDeReferenciado(0);

                            tablaDePaginas.putMarco(pagina, marco);
                            memoriaFisica.getMarcos()[marco].setPaginaAlmacenada(pagina);

                            if (operacion.equals("W")) {
                                tablaDePaginas.getPagina(pagina).setBitDeModificado(1);
                                tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                            } else {
                                tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                            }

                        }

                    } else {
                        Memoria.tiempo += 0.00003; // 30ns

                        if (operacion.equals("W")) {
                            if (tablaDePaginas.getPagina(pagina).getBitDeModificado() == 0) {
                                Memoria.misses++;
                            } else {
                                Memoria.hits++;
                            }
                            tablaDePaginas.getPagina(pagina).setBitDeModificado(1);
                            tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                        } else {
                            tablaDePaginas.getPagina(pagina).setBitDeReferenciado(1);
                            Memoria.hits++;
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            // }

            repeticiones--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Memoria.finEjecucionT1 = true;
        
    }
    
}
