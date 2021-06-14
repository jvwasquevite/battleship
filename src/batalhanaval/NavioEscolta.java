package batalhanaval;

public class NavioEscolta implements Embarcacao {
    private final String nomeEmbarcacao;
    private int tamanhoEmbarcacao;

    public NavioEscolta() {
        this.nomeEmbarcacao = "Navio Escolta";
        this.tamanhoEmbarcacao = 3;
    }
    
    @Override
    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }

    @Override
    public int getTamanhoEmbarcacao() {
        return tamanhoEmbarcacao;
    }
    
    @Override
    public void setTamanhoEmbarcacao(int tamanho) {
        this.tamanhoEmbarcacao = tamanho;
    }

    @Override
    public void explodirEmbarcacao() {
        setTamanhoEmbarcacao(getTamanhoEmbarcacao() - 1);
    }

    @Override
    public void ler() {
        System.out.println("{" + "nomeEmbarcacao=" + nomeEmbarcacao + ", tamanhoEmbarcacao=" + tamanhoEmbarcacao + "}");
    }
    
}