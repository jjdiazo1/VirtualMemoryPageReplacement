package caso2_jj.diazo1_rpazl;

public class Pagina {
    private int numeroPagina;
    private boolean bitR;
    private boolean bitM;
    private boolean presenteEnRAM;

    public Pagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
        this.bitR = false;
        this.bitM = false;
        this.presenteEnRAM = false;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public boolean isBitR() {
        return bitR;
    }

    public void setBitR(boolean bitR) {
        this.bitR = bitR;
    }

    public boolean isBitM() {
        return bitM;
    }

    public void setBitM(boolean bitM) {
        this.bitM = bitM;
    }

    public boolean isPresenteEnRAM() {
        return presenteEnRAM;
    }

    public void setPresenteEnRAM(boolean presenteEnRAM) {
        this.presenteEnRAM = presenteEnRAM;
    }
}
