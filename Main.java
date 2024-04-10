import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
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

                    File archivo = new File(nombreArchivo);
                    try {
                        FileReader fr = new FileReader(archivo);
                        BufferedReader bf = new BufferedReader(fr);

                        Memoria memoria = new Memoria(numMarcos, bf);

                        memoria.ejecutarMemoria();

                    } catch (FileNotFoundException e) {
                        System.out.println("No se encontró el archivo\n");
                    }

                } else if (opcion == 3) {
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Opción no válida");
                }

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número");
            }

        }

    }

    private void imprimirMenu() {
        System.out.println("\n1. Generación de referencias");
        System.out.println("2. Calcular datos: Número de fallas de página, porcentaje de hits, tiempos");
        System.out.println("3. Salir\n");
    }

}
