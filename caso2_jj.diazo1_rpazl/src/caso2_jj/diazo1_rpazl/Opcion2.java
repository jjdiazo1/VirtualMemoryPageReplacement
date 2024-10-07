package caso2_jj.diazo1_rpazl;

import java.io.*;
import java.util.*;

public class Opcion2 {
	public static void calcularDatosBuscados() {
	    Scanner scanner = new Scanner(System.in);
	    try {
	        System.out.print("Ingrese el número de marcos de página: ");
	        int numeroMarcos = scanner.nextInt();
	        System.out.print("Ingrese el nombre del archivo de referencias: ");
	        String nombreArchivoReferencias = scanner.next();

	        List<Referencia> referencias = leerReferencias(nombreArchivoReferencias);
	        int numeroPaginasVirtuales = obtenerNumeroPaginasVirtuales(nombreArchivoReferencias);

	        SistemaPaginacion sistema = new SistemaPaginacion(numeroMarcos, numeroPaginasVirtuales);

	        HiloProcesador hiloProcesador = new HiloProcesador(sistema, referencias);
	        HiloActualizadorR hiloActualizadorR = new HiloActualizadorR(sistema);

	        Thread threadProcesador = new Thread(hiloProcesador);
	        Thread threadActualizadorR = new Thread(hiloActualizadorR);

	        threadProcesador.start();
	        threadActualizadorR.start();

	        try {
	            threadProcesador.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        hiloActualizadorR.detener();

	        int totalReferencias = referencias.size();
	        int hits = sistema.getHits();
	        int fallas = sistema.getFallas();
	        double porcentajeHits = (hits * 100.0) / totalReferencias;
	        double porcentajeFallas = (fallas * 100.0) / totalReferencias;

	        System.out.println("Total de referencias: " + totalReferencias);
	        System.out.println("Hits: " + hits + " (" + porcentajeHits + "%)");
	        System.out.println("Fallas de página: " + fallas + " (" + porcentajeFallas + "%)");

	        long tiempoHits = hits * 25; // En nanosegundos
	        long tiempoFallas = fallas * 10_000_000; // En nanosegundos (10 ms = 10.000.000 NS)
	        long tiempoTotal = tiempoHits + tiempoFallas;

	        System.out.println("Tiempo total (ns): " + tiempoTotal);
	    } finally {
	        scanner.close(); 
	    }
	}



    private static List<Referencia> leerReferencias(String nombreArchivoReferencias) {
        List<Referencia> referencias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoReferencias))) {
            String linea;
            while ((linea = reader.readLine()) != null && !linea.startsWith("Imagen")) {
                // Ignorar o almacenar los datos si es necesario
            }
            do {
                String[] partes = linea.split(",");
                String identificador = partes[0];
                int paginaVirtual = Integer.parseInt(partes[1]);
                int desplazamiento = Integer.parseInt(partes[2]);
                char accion = partes[3].charAt(0);
                referencias.add(new Referencia(identificador, paginaVirtual, desplazamiento, accion));
            } while ((linea = reader.readLine()) != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return referencias;
    }

    private static int obtenerNumeroPaginasVirtuales(String nombreArchivoReferencias) {
        int numeroPaginasVirtuales = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivoReferencias))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("NP=")) {
                    numeroPaginasVirtuales = Integer.parseInt(linea.substring(3));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numeroPaginasVirtuales;
    }
}
