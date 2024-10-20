package caso2_jj.diazo1_rpazl;

public class Referencia {
    private String identificador;
    private int paginaVirtual;
    private int desplazamiento;
    private char accion; // 'R' o 'W'

    public Referencia(String identificador, int paginaVirtual, int desplazamiento, char accion) {
        this.identificador = identificador;
        this.paginaVirtual = paginaVirtual;
        this.desplazamiento = desplazamiento;
        this.accion = accion;
    }

    // Getters
    public String getIdentificador() {
        return identificador;
    }

    public int getPaginaVirtual() {
        return paginaVirtual;
    }

    public int getDesplazamiento() {
        return desplazamiento;
    }

    public char getAccion() {
        return accion;
    }

    @Override
    public String toString() {
        return identificador + "," + paginaVirtual + "," + desplazamiento + "," + accion;
    }
}
