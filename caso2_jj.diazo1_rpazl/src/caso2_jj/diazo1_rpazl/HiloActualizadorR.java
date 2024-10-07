package caso2_jj.diazo1_rpazl;

public class HiloActualizadorR implements Runnable {
    private SistemaPaginacion sistema;
    private volatile boolean running = true;

    public HiloActualizadorR(SistemaPaginacion sistema) {
        this.sistema = sistema;
    }

    @Override
    public void run() {
        while (running) {
            sistema.resetearBitsR();
            try {
                Thread.sleep(2); // Corre cada dos milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void detener() {
        running = false;
    }
}
