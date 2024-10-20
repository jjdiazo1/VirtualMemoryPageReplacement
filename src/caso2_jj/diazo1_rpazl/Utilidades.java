package caso2_jj.diazo1_rpazl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Utilidades {

	/**
     * Método para esconder un mensaje en una imagen BMP.
     * Pide al usuario el archivo de la imagen y el archivo del mensaje para esconderlo.
     */
    public static void esconderMensaje() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    try {
	
	        System.out.println("Nombre del archivo con la imagen a procesar:");
	        String nombreArchivo = br.readLine();
	        String rutaArchivo = "Archivos" + File.separator + nombreArchivo; 
	        
	        Imagen imagen = new Imagen(rutaArchivo);
	
	        System.out.println("Nombre del archivo con el mensaje a esconder: ");
	        String nombreArchivoMensaje = br.readLine();
	        String rutaArchivoMensaje = "Archivos" + File.separator + nombreArchivoMensaje; 
	        
	        int longitud = leerArchivoTexto(rutaArchivoMensaje);
	        char[] mensaje = new char[longitud];
	        leerContenidoMensaje(rutaArchivoMensaje, mensaje);
	
	        imagen.esconder(mensaje, longitud);
	        
	        String nombreArchivoSalida = "Archivos" + File.separator + "salida_" + nombreArchivo;
	        imagen.escribirImagen(nombreArchivoSalida);
	        
	        System.out.println("Imagen con el mensaje escondido generada: " + nombreArchivoSalida);
	
	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

    /**
     * Método para recuperar un mensaje escondido en una imagen BMP.
     * Pide al usuario el archivo con la imagen que contiene el mensaje escondido.
     */
    public static void recuperarMensaje() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("Nombre del archivo con el mensaje escondido: ");
            String nombreArchivo = br.readLine();
            
            String rutaArchivo = "Archivos" + File.separator + nombreArchivo; 

            Imagen imagen = new Imagen(rutaArchivo);

            int longitud = imagen.leerLongitud();
            char[] mensaje = new char[longitud];
            imagen.recuperar(mensaje, longitud);

            System.out.println("Nombre del archivo para almacenar el mensaje recuperado: ");
            String salida = br.readLine();
            escribirArchivoTexto(salida, mensaje);
            System.out.println("Mensaje recuperado almacenado en: " + salida);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para leer el archivo de texto y devolver la longitud del mensaje.
     * @param rutaMensaje Ruta del archivo de texto que contiene el mensaje a esconder.
     * @return La longitud del mensaje.
     */
    private static int leerArchivoTexto(String rutaMensaje) {
        int longitud = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaMensaje)))) {
            while (br.read() != -1) {
                longitud++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return longitud;
    }

    /**
     * Método para leer el contenido del archivo de texto y almacenarlo en un array de caracteres.
     * @param rutaMensaje Ruta del archivo de texto que contiene el mensaje a esconder.
     * @param mensaje Array de caracteres donde se almacenará el mensaje.
     */
    private static void leerContenidoMensaje(String rutaMensaje, char[] mensaje) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaMensaje)))) {
            int index = 0;
            int character;
            while ((character = br.read()) != -1) {
                mensaje[index++] = (char) character;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para escribir el mensaje recuperado en un archivo de texto.
     * @param rutaSalida Ruta del archivo donde se escribirá el mensaje recuperado.
     * @param mensaje Array de caracteres que contiene el mensaje recuperado.
     */
    private static void escribirArchivoTexto(String rutaSalida, char[] mensaje) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaSalida)))) {
            for (char c : mensaje) {
                bw.write(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
