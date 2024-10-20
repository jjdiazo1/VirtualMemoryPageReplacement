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
            sistema.resetearBitsR(); // Limpia los bits R periódicamente
            try {
                Thread.sleep(2000); // Cada 2 segundos
            } catch (InterruptedException e) {
                System.out.println("El hilo actualizador R ha sido interrumpido.");
            }
        }
    }

    public void detener() {
        running = false;
    }
}
