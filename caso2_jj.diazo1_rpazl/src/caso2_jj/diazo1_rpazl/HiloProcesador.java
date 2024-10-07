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
        for (Referencia ref : referencias) {
            sistema.manejarReferencia(ref);
            try {
                Thread.sleep(1); // Simula un acceso cada milisegundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
