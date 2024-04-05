
public class Proceso {

    private int nf;
    private int nc;

    private String referenciasProceso;

    public Proceso() {
        
        this.nf = Memoria.nF;
        this.nc = Memoria.nC;

        this.referenciasProceso = "";
       
    }
    

    public String ejecutarProceso() {
        /*Este es el algoritmo del proceso que se da en el enunciado.
         * Está modificado para que en lugar de realizar operaciones sobre la matriz, en cada acceso a memoria, 
         * se registre la referencia a memoria que se está realizando, con las funciones buscarEnMatriz y escribirEnMatriz.
         */

        for (int i = 1; i < nf - 1; i++) { // 2
            for (int j = 1; j < nc - 1; j++) { // 2
                // Recorrer los vecinos y aplicar el filtro
                for (int a = -1; a <= 1; a++) { // 3
                    for (int b = -1; b <= 1; b++) {  // 3 * 2
                        int i2 = i + a;
                        int j2 = j + b;
                        int i3 = 1 + a;
                        int j3 = 1 + b;

                        buscarEnMatriz(1, i2, j2);
                        buscarEnMatriz(2, i3, j3);

                    }
                }

                escribirEnMatriz(3, i, j); // 2 * 2

            }
        }

        // Asignar valores a los bordes
        for (int i = 0; i < nc; i++) { // 4 * 2

            escribirEnMatriz(3, 0, i);
            escribirEnMatriz(3, nf-1, i);

        }
        for (int i = 1; i < nf - 1; i++) { // 2 * 2

            escribirEnMatriz(3, i, 0);
            escribirEnMatriz(3, i, nc-1);

            }

        return referenciasProceso;

        }
        


    public void buscarEnMatriz(int tipo, int i, int j){
        /*El tipo 1 es para la matriz de datos, que se almacena en la segunda posición en memoria
         * El tipo 2 es para la matriz con el filtro, que se almacena en la primera posición en memoria
         * El tipo 3 es para la matriz resultante, que se almacena en la tercera posición en memoria
         */

        if (tipo == 1) {

            // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz de datos
            int pagina = ((i * nc + j)+9)*4 / Memoria.tamanioPagina;
            // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
            int desplazamiento = ((i * nc + j)+9)*4 % Memoria.tamanioPagina;

            referenciasProceso += "M["+i+"]["+j+"],"+pagina+","+desplazamiento+",R\n";

        } else if (tipo == 2) {
            
            // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz con el filtro
            int pagina = ((i * 3 + j))*4 / Memoria.tamanioPagina;
            // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
            int desplazamiento = ((i * 3 + j))*4 % Memoria.tamanioPagina;

            referenciasProceso += "F["+i+"]["+j+"],"+pagina+","+desplazamiento+",R\n";

        } else {
                
                // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz resultante
                int pagina = ((i * nc + j)+9+nf*nc)*4 / Memoria.tamanioPagina;
                // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
                int desplazamiento = ((i * nc + j)+9+nf*nc)*4 % Memoria.tamanioPagina;
    
                referenciasProceso += "R["+i+"]["+j+"],"+pagina+","+desplazamiento+",R\n";
        }
    }


    public void escribirEnMatriz(int tipo, int i, int j){
        /*El tipo 1 es para la matriz de datos, que se almacena en la segunda posición en memoria
         * El tipo 2 es para la matriz con el filtro, que se almacena en la primera posición en memoria
         * El tipo 3 es para la matriz resultante, que se almacena en la tercera posición en memoria
         */

        if (tipo == 1) {
            
            // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz de datos
            int pagina = ((i * nc + j)+9)*4 / Memoria.tamanioPagina;
            // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
            int desplazamiento = ((i * nc + j)+9)*4 % Memoria.tamanioPagina;

            referenciasProceso += "M["+i+"]["+j+"],"+pagina+","+desplazamiento+",W\n";

        } else if (tipo == 2) {
            
            // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz con el filtro
            int pagina = ((i * 3 + j))*4 / Memoria.tamanioPagina;
            // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
            int desplazamiento = ((i * 3 + j))*4 % Memoria.tamanioPagina;

            referenciasProceso += "F["+i+"]["+j+"],"+pagina+","+desplazamiento+",W\n";

        } else {
                
                // Primero se obtiene la pagina donde se almacena el dato i,j de la matriz resultante
                int pagina = ((i * nc + j)+9+nf*nc)*4 / Memoria.tamanioPagina;
                // Luego se obtiene el desplazamiento dentro de la pagina correspondiente
                int desplazamiento = ((i * nc + j)+9+nf*nc)*4 % Memoria.tamanioPagina;
    
                referenciasProceso += "R["+i+"]["+j+"],"+pagina+","+desplazamiento+",W\n";

        }
    }
    
}
