package batalhanaval;

public interface Embarcacao {
    public String getNomeEmbarcacao();
    public int getTamanhoEmbarcacao();
    public void setTamanhoEmbarcacao(int tamanho);
    public boolean getStatusEmbarcacao();
    public void setStatusEmbarcacao(boolean status);
    public void explodirEmbarcacao();
    public void ler();
}