package batalhanaval;

public class AviaoCaca implements Embarcacao {
    private final String nomeEmbarcacao;
    private int tamanhoEmbarcacao;

    public AviaoCaca() {
        this.nomeEmbarcacao = "Avião Caça";
        this.tamanhoEmbarcacao = 2;
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
