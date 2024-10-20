package caso2_jj.diazo1_rpazl;

import java.io.*;
import java.util.Scanner;

public class Opcion1 {
	public static void generarReferencias() {
	    Scanner scanner = new Scanner(System.in);
	    
	    try {
	    	
	    System.out.print("Ingrese el tamaño de página: ");
	    int tamanoPagina = scanner.nextInt();
	    System.out.print("Ingrese el nombre del archivo que guarda la imagen con el mensaje escondido: ");
	    String nombreArchivoImagen = scanner.next();

	    String nombre = "src/Archivos/" + nombreArchivoImagen;
	    Imagen imagen = new Imagen(nombre);

        int nf = imagen.alto;  
        int nc = imagen.ancho; 
        int tamanoMensaje = imagen.leerLongitud(); 
        if (tamanoPagina == 0) {
            throw new IllegalArgumentException("El tamaño de pagina no puede ser cero.");
            }

        // Cálculos:
        int nr = (tamanoMensaje * 8 * 2) + 16 + tamanoMensaje; 
        int paginasFoto = (int) Math.ceil((double) (nf * nc * 3) / tamanoPagina);
        int paginasMensaje = (int) Math.ceil((double) tamanoMensaje / tamanoPagina); 
        int np = paginasFoto + paginasMensaje; 
        
     // Escribir datos iniciales en el archivo de referencias
        
        String baseNombre = nombreArchivoImagen.replace(".bmp", "");
        String nombreArchivoReferencias = "src/Referencias/"+ baseNombre + "Referencias.txt";
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

            // Generar referencias detalladas para la imagen
            int f = 0, c = 0, col = 0;
            String[] color = {"R", "G", "B"};
            int pagina = 0, desplazamiento = 0;

            for (int i = 0; i < 16; i++) {
                writer.write("Imagen[" + f + "][" + c + "]." + color[col] + "," + pagina + "," + desplazamiento + ",R");
                writer.newLine();
                col = col + 1;
                desplazamiento = desplazamiento + 1;

                if (f >= nf) { f = 0; }
                if (col >= 3) { c = c + 1; col = 0; }
                if (c >= nc) { c = 0; f = f + 1; }
                if (desplazamiento >= tamanoPagina) {
                    desplazamiento = 0;
                    pagina = pagina + 1;
                }
            }

            int paginaMensaje = pagina + paginasFoto;
            int desplazamientoMen = 0;

            // Referencias para el mensaje
            for (int posCaracter = 0; posCaracter < tamanoMensaje; posCaracter++) {
                writer.write("Mensaje[" + posCaracter + "]" + "," + paginaMensaje + "," + desplazamientoMen + ",W");
                writer.newLine();

                for (int bitsContados = 0; bitsContados < 8; bitsContados++) {
                    writer.write("Imagen[" + f + "][" + c + "]." + color[col] + "," + pagina + "," + desplazamiento + ",R");
                    writer.newLine();
                    writer.write("Mensaje[" + posCaracter + "]" + "," + paginaMensaje + "," + desplazamientoMen + ",W");
                    writer.newLine();

                    col = col + 1;
                    desplazamiento = desplazamiento + 1;

                    if (desplazamiento >= tamanoPagina) {
                        desplazamiento = 0;
                        pagina = pagina + 1;
                    }

                    if (col >= 3) {
                        c = c + 1;
                        col = 0;
                    }

                    if (c >= nc) {
                        c = 0;
                        f = f + 1;
                    }

                    if (f >= nf) {
                        f = 0;
                    }
                }

                desplazamientoMen = desplazamientoMen + 1;
                if (desplazamientoMen >= tamanoPagina) {
                    desplazamientoMen = 0;
                    paginaMensaje = paginaMensaje + 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Archivo de referencias generado: " + nombreArchivoReferencias);
    } finally {
        scanner.close();
    }
}
}
