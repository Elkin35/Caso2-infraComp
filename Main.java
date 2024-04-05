package Caso_2.V2;

import java.util.Scanner;


public class Main {

    
    public static void main(String[] args) {

        Main main = new Main();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            main.imprimirMenu();
            String strOpcion = scanner.next();

            try {

                int opcion = Integer.parseInt(strOpcion);

                if (opcion == 1) {

                    int tamanioPagina;
                    int nF;
                    int nC;

                    System.out.println("Ingrese el tamaño de la página");
                    tamanioPagina = scanner.nextInt();
                    System.out.println("Ingrese el número de filas de la matriz");
                    nF = scanner.nextInt();
                    System.out.println("Ingrese el número de columnas de la matriz");
                    nC = scanner.nextInt();

                    Memoria memoria = new Memoria(tamanioPagina, nF, nC);

                    memoria.generarReferencias();

                } else if (opcion == 2) {

                    int numMarcos;
                    String nombreArchivo;

                    System.out.println("Ingrese el número de marcos");
                    numMarcos = scanner.nextInt();
                    System.out.println("Ingrese el nombre del archivo");
                    nombreArchivo = scanner.next();

                } else if (opcion == 3) {
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Opción no válida");
                }

            } catch (Exception e) {
                System.out.println("Debe ingresar un número");
            }

        }

    }

    private void imprimirMenu() {
        System.out.println("1. Generación de referencias");
        System.out.println("2. Calcular datos: Número de fallas de página, porcentaje de hits, tiempos");
        System.out.println("3. Salir");
    }

}
