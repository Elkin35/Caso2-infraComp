
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Memoria {

    private MemoriaFisica memoriaFisica;
    private Tabla_De_Paginas tablaPaginas;

    public static int tamanioPagina;
    public static int nF;
    public static int nC;

    private int nR;
    private int nP;
    private int nF_NC_Filtro;

    private int cantidadMarcos;
    private int marcosDisponibles;
    private BufferedReader br;
    private BufferedReader brSimulacion;

    private Map<Integer, String> ram = new HashMap<>();
    Map<Integer, String> tablaDePaginas = new HashMap<>();
    
    private String referencias;

    public static int hits;
    public static int misses;
    public static double tiempo; // En ms

    public static boolean finEjecucionT1 = false;

    public Memoria(int tamanioPagina, int nF, int nC) {
        Memoria.tamanioPagina = tamanioPagina;
        Memoria.nF = nF;
        Memoria.nC = nC;
    }

    public Memoria(int cantidadMarcos, BufferedReader br) {
        this.cantidadMarcos = cantidadMarcos;
        this.br = br;
        this.brSimulacion = br;

        this.marcosDisponibles = cantidadMarcos;

        inicializarMemoria();
    }

    public void inicializarMemoria() {

        // Ahora se obtienen los datos TP, NF, NC, NF_NC_Filtro, NR y NP de el archivo
        // br, sabiendo que cada uno de estos datos son enteros despues de un =
        for (int i = 0; i < 6; i++) {

            String[] linea;

            try {
                linea = br.readLine().split("=");
                int valor = Integer.parseInt(linea[1]);

                switch (i) {
                    case 0:
                        tamanioPagina = valor;
                    case 1:
                        nF = valor;
                        break;
                    case 2:
                        nC = valor;
                        break;
                    case 3:
                        nF_NC_Filtro = valor;
                    case 4:
                        nR = valor;
                    case 5:
                        nP = valor;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // Inicializamos la memoria fisica (ram) con marcos vacios
        memoriaFisica = new MemoriaFisica(cantidadMarcos, br);

        // Inicializamos la tabla de paginas
        tablaPaginas = new Tabla_De_Paginas(nP, tamanioPagina);

    }

    public void generarReferencias() {
        referencias = "";

        int numReferencias = ((nF - 1) - 1) * ((nC - 1) - 1) * 3 * 3 * 2 + ((nF - 1) - 1) * ((nC - 1) - 1) + nC * 2
                + ((nF - 1) - 1) * 2;
        int numPag = (int) Math.ceil((9 + (nF * nC) + (nF * nC)) * 4 / (double) tamanioPagina);

        referencias += "TP=" + tamanioPagina + "\n";
        referencias += "NF=" + nF + "\n";
        referencias += "NC=" + nC + "\n";
        referencias += "NF_NC_Filtro=3\n";
        referencias += "NR=" + numReferencias + "\n";
        referencias += "NP=" + numPag + "\n";

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

    public void ejecutarMemoria() {
        Thread t1 = new T1(tablaPaginas, memoriaFisica, br, nR);
        Thread t2 = new T2(tablaPaginas, nP);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
        System.out.println("Tiempo: " + tiempo);
        System.out.println("Porcentaje de hits: " + (hits / (double) (hits + misses)) * 100 + "%");

        tamanioPagina = 0;
        nF = 0;
        nC = 0;
        hits = 0;
        misses = 0;
        tiempo = 0;
        finEjecucionT1 = false;

        System.out.println("Terminé");
    }

}
