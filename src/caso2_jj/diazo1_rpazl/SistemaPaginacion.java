package caso2_jj.diazo1_rpazl;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class SistemaPaginacion {
    private Map<Integer, Pagina> tablaPaginas;
    private Queue<Integer> marcosRAM;
    private int numeroMarcos;
    private int hits;
    private int fallas;

    public SistemaPaginacion(int numeroMarcos, int numeroPaginasVirtuales) {
        this.numeroMarcos = numeroMarcos;
        this.tablaPaginas = new HashMap<>();
        this.marcosRAM = new LinkedList<>();
        this.hits = 0;
        this.fallas = 0;

        for (int i = 0; i < numeroPaginasVirtuales; i++) {
            tablaPaginas.put(i, new Pagina(i));
        }
    }

    // Método para llenar la tabla, similar a llenarTabla() en TablaDePaginas
    public synchronized void llenarTabla() {
        for (int i = 0; i < tablaPaginas.size(); i++) {
            tablaPaginas.get(i).setPresenteEnRAM(false); // Reinicia el estado de cada página
        }
    }

    // Método para verificar si una página está cargada, similar a estaCargada() en TablaDePaginas
    public synchronized boolean estaCargada(int paginaACargar) {
        Pagina pagina = tablaPaginas.get(paginaACargar);
        return pagina != null && pagina.isPresenteEnRAM();
    }

    // Método para asignar valores a las páginas, similar a asignarValor() en TablaDePaginas
    public synchronized void asignarValor(int paginaIndice, int valor) {
        Pagina pagina = tablaPaginas.get(paginaIndice);
        if (pagina != null) {
            pagina.setPresenteEnRAM(valor == 1); // Asume que valor 1 significa que está en RAM
        }
    }

    

    public synchronized void manejarReferencia(Referencia ref) {
        Pagina pagina = tablaPaginas.get(ref.getPaginaVirtual());
    
        if (pagina.isPresenteEnRAM()) {
          
            if (pagina.isPresenteEnRAM()) {
                hits++;
            } else {
                
                fallas++;
                cargarPagina(ref, pagina);
            }
        } else {
            fallas++;
            cargarPagina(ref, pagina);
        }
    }
    
    
    private void cargarPagina(Referencia ref, Pagina pagina) {
        if (marcosRAM.size() < numeroMarcos) {
            marcosRAM.add(ref.getPaginaVirtual());
            pagina.setPresenteEnRAM(true);
        } else {
            reemplazarPagina();
            marcosRAM.add(ref.getPaginaVirtual());
            pagina.setPresenteEnRAM(true);
        }
    }
    

    private synchronized void reemplazarPagina() {
        int paginaARemplazar = -1;
        int categoriaMenor = 4;

        for (int numeroPagina : marcosRAM) {
            Pagina pagina = tablaPaginas.get(numeroPagina);
            int categoria = calcularCategoria(pagina);
            if (categoria < categoriaMenor) {
                categoriaMenor = categoria;
                paginaARemplazar = numeroPagina;
                if (categoria == 0) break;
            }
        }

        marcosRAM.remove(paginaARemplazar);
        Pagina paginaReemplazada = tablaPaginas.get(paginaARemplazar);
        paginaReemplazada.setPresenteEnRAM(false);
    }

    private int calcularCategoria(Pagina pagina) {
        int r = pagina.isBitR() ? 1 : 0;
        int m = pagina.isBitM() ? 1 : 0;
        if (r == 0 && m == 0) return 0;
        if (r == 0 && m == 1) return 1;
        if (r == 1 && m == 0) return 2;
        return 3;
    }

    public synchronized void resetearBitsR() {
        for (Pagina pagina : tablaPaginas.values()) {
            pagina.setBitR(false);
        }
    }

    public int getHits() {
        return hits;
    }

    public int getFallas() {
        return fallas;
    }
}
