package caso2_jj.diazo1_rpazl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Generación de las referencias");
            System.out.println("2. Calcular datos buscados");
            System.out.println("3. Esconder un mensaje en una imagen");
            System.out.println("4. Recuperar un mensaje de una imagen");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Opcion1.generarReferencias();
                    break;
                case 2:
                    Opcion2.calcularDatosBuscados();
                    break;
                case 3:
                    Utilidades.esconderMensaje();
                    break;
                case 4:
                    Utilidades.recuperarMensaje();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
                    break;
            }
        } while (opcion != 5);
        scanner.close();
    }
}
