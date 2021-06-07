package batalhanaval;

public class Submarino implements Embarcacao {
    private int tamanhoEmbarcacao = 2;
    private final String nomeEmbarcacao = "Submarino";
    private boolean statusEmbarcacao = true;

    @Override
    public int getTamanhoEmbarcacao() {
        return tamanhoEmbarcacao;
    }

    @Override
    public String getNomeEmbarcacao() {
        return nomeEmbarcacao;
    }
    
    @Override
    public boolean getStatusEmbarcacao() {
        return statusEmbarcacao;
    }

    @Override
    public void explodirEmbarcacao() {
        if(tamanhoEmbarcacao > 0){
            // Explode uma unidade da embarcao
            tamanhoEmbarcacao--;
        } else if(tamanhoEmbarcacao == 0) {
            // Embarcacao totalmente explodida
            statusEmbarcacao = false;
        }
    }
}
