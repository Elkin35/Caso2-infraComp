package Caso_2.V2;

import java.io.FileWriter;

public class Memoria {

    public static int tamanioPagina;
    public static int nF;
    public static int nC;

    public static Pagina[] ram;
    public static Pagina[] swap;

    public static Tabla_De_Paginas tabla;

    private String referencias;

    public Memoria(int tamanioPagina, int nF, int nC) {
        Memoria.tamanioPagina = tamanioPagina;
        Memoria.nF = nF;
        Memoria.nC = nC;
    }


    public void inicializarMemoria() {
        ram = new Pagina[nF * nC];
        swap = new Pagina[nF * nC];

        for (int i = 0; i < nF*nC; i++) {
            ram[i] = new Pagina(i, 0, 0, tamanioPagina);
            swap[i] = new Pagina(i, 0, 0, tamanioPagina);
        }

    }


    public void generarReferencias() {
        referencias = "";

        int numReferencias = ((nF-1)-1)*((nC-1)-1)*3*3*2 + ((nF-1)-1)*((nC-1)-1) + nC*2 + ((nF-1)-1)*2;
        int numPag = (int) Math.ceil((9+(nF*nC)+(nF*nC))*4 / (double) tamanioPagina);
        
        referencias += "TP="+tamanioPagina+"\n";
        referencias += "NF="+nF+"\n";
        referencias += "NC="+nC+"\n";
        referencias += "NF_NC_Filtro=3\n";
        referencias += "NR="+numReferencias+"\n";
        referencias += "NP="+numPag+"\n";

        Proceso proceso = new Proceso();

        referencias += proceso.ejecutarProceso();

        // System.out.println(referencias);
        
        try {
            FileWriter w = new FileWriter("referencias.txt");
            w.write(referencias);
            w.close();
            System.out.println("Referencias generadas correctamente");
        } catch (Exception e) {
            System.out.println("Error al escribir las referencias");
        }

    }
    
}
