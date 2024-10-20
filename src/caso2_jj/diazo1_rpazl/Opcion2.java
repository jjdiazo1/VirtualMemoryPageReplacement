package caso2_jj.diazo1_rpazl;

import java.io.*;
import java.util.*;

public class Opcion2 {
    public static void calcularDatosBuscados() {
        try {
            InputStreamReader isr = new InputStreamReader(System.in); 
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Ingrese la dirección del archivo de referencias: ");
            String dirArchivo = br.readLine();
            
            System.out.print("Ingrese el número de marcos de página: ");
            int numeroMarcos = Integer.parseInt(br.readLine());

            File file = new File("src/Referencias/" + dirArchivo);
            Scanner lector = new Scanner(file);
            List<Referencia> referencias = new ArrayList<>();

            int tamanoPagina = Integer.parseInt(lector.nextLine().split("=")[1]);
            int numFilas = Integer.parseInt(lector.nextLine().split("=")[1]);
            int numColumnas = Integer.parseInt(lector.nextLine().split("=")[1]);
            int totalReferencias = Integer.parseInt(lector.nextLine().split("=")[1]);
            int numeroPaginasVirtuales = Integer.parseInt(lector.nextLine().split("=")[1]);

            // Crear objetos necesarios
            SistemaPaginacion sistema = new SistemaPaginacion(numeroMarcos, numeroPaginasVirtuales);

            // Leer y procesar las referencias
            while (lector.hasNextLine()) {
                String[] linea = lector.nextLine().split(",");
                String identificador = linea[0];
                int paginaVirtual = Integer.parseInt(linea[1]);
                int desplazamiento = Integer.parseInt(linea[2]);
                char accion = linea[3].charAt(0);

                referencias.add(new Referencia(identificador, paginaVirtual, desplazamiento, accion));
            }

            HiloProcesador hiloProcesador = new HiloProcesador(sistema, referencias);
            HiloActualizadorR hiloActualizadorR = new HiloActualizadorR(sistema);

            // Iniciar los hilos
            Thread threadProcesador = new Thread(hiloProcesador);
            Thread threadActualizadorR = new Thread(hiloActualizadorR);

            threadProcesador.start();
            threadActualizadorR.start();

            try {
                threadProcesador.join(); // Esperar a que termine el hilo procesador
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hiloActualizadorR.detener(); // Detener el hilo actualizador R
            threadActualizadorR.interrupt(); // Interrumpir el hilo para detener el sleep

            // Mostrar resultados de la simulación
            int hits = sistema.getHits();
            int fallas = sistema.getFallas();
            double porcentajeHits = (hits * 100.0) / totalReferencias;
            double porcentajeFallas = (fallas * 100.0) / totalReferencias;

            System.out.println("Total de referencias: " + totalReferencias);
            System.out.println("Hits: " + hits + " (" + porcentajeHits + "%)");
            System.out.println("Fallas de página: " + fallas + " (" + porcentajeFallas + "%)");

            // Cálculo de tiempos en milisegundos
            double tiempoHitMs = 0.000025; // 25 ns convertido a ms
            double tiempoSwapMs = 10.0; // 10 ms por swap

            double tiempoTotalHits = tiempoHitMs * hits;
            double tiempoTotalMisses = (fallas * tiempoSwapMs) + (fallas * tiempoHitMs);
            double tiempoTotal = tiempoTotalHits + tiempoTotalMisses;

            System.out.println("Tiempo total necesario para descifrar el mensaje: " + tiempoTotal + " ms");

            lector.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
