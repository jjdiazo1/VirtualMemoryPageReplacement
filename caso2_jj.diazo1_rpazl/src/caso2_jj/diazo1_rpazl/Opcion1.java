package caso2_jj.diazo1_rpazl;

import java.io.*;
import java.util.Scanner;

public class Opcion1 {
	public static void generarReferencias() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Ingrese el tamaño de página: ");
	    int tamanoPagina = scanner.nextInt();
	    System.out.print("Ingrese el nombre del archivo que guarda la imagen con el mensaje escondido: ");
	    String nombreArchivoImagen = scanner.next();

	    Imagen imagen = new Imagen(nombreArchivoImagen);

	    // Datos iniciales a ser escritos en el archivo de referencias
	    int nf = imagen.alto;  // Número de filas de la imagen
	    int nc = imagen.ancho; // Número de columnas de la imagen
	    int nr = nf * nc * 3; // Total de referencias (3 por cada pixel: R, G, B)
	    int np = (int) Math.ceil((double) nr / tamanoPagina); // Número de páginas virtuales necesarias

	    String nombreArchivoReferencias = "referencias.txt";
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivoReferencias))) {
	        writer.write("TP=" + tamanoPagina);
	        writer.newLine();
	        writer.write("NF=" + nf);
	        writer.newLine();
	        writer.write("NC=" + nc);
	        writer.newLine();
	        writer.write("NR=" + nr);
	        writer.newLine();
	        writer.write("NP=" + np);
	        writer.newLine();

	        // Escribir las referencias generadas
	        for (int fila = 0; fila < nf; fila++) {
	            for (int columna = 0; columna < nc; columna++) {
	                for (int color = 0; color < 3; color++) { // 0 = R, 1 = G, 2 = B
	                    String identificador = "Imagen[" + fila + "][" + columna + "]." + (color == 0 ? "R" : color == 1 ? "G" : "B");
	                    int paginaVirtual = (fila * nc * 3 + columna * 3 + color) / tamanoPagina;
	                    int desplazamiento = (fila * nc * 3 + columna * 3 + color) % tamanoPagina;
	                    writer.write(identificador + "," + paginaVirtual + "," + desplazamiento + ",R");
	                    writer.newLine();
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Archivo de referencias generado: " + nombreArchivoReferencias);
	}

}
