package caso2_jj.diazo1_rpazl;

import java.util.List;

public class HiloProcesador implements Runnable {
    private SistemaPaginacion sistema;
    private List<Referencia> referencias;

    public HiloProcesador(SistemaPaginacion sistema, List<Referencia> referencias) {
        this.sistema = sistema;
        this.referencias = referencias;
    }

    @Override
    public void run() {
        int contador = 0;
        for (Referencia ref : referencias) {
            sistema.manejarReferencia(ref);
            contador++;
            if (contador % 1000 == 0) {
                System.out.println("Procesadas " + contador + " referencias.");
            }
            try {
                Thread.sleep(1); // Simula un acceso cada milisegundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("HiloProcesador ha terminado de procesar las referencias.");
    }
}
