package batalhanaval;
import java.util.ArrayList;

// Cria um array de embarcacoes para o jogador e computador
// Retorna e modifica as embarcacoes do jogador e computador
// Sorteia e retorna a matriz do Jogador e do computador

public class Jogar {
    // Inicializa ArrayList de embarcacoes do jogador e do computador
    private ArrayList<Embarcacao> embarcacoesJogador = new ArrayList();
    private ArrayList<Embarcacao> embarcacoesComputador = new ArrayList();
    
    private final SorteiaJogo sorteioJogador = new SorteiaJogo();
    private final SorteiaJogo sorteioComputador = new SorteiaJogo();
    
    public Jogar() {
        // Preenche o ArrayList do Jogador
        embarcacoesJogador.add(new PortaAviao());
        embarcacoesJogador.add(new Submarino());
        embarcacoesJogador.add(new NavioEscolta());
        embarcacoesJogador.add(new AviaoCaca());
        
        System.out.println("Embarcacoes jogador = " + embarcacoesJogador);
        
        // Preenche o ArrayList do Computador
        embarcacoesComputador.add(new PortaAviao());
        embarcacoesComputador.add(new Submarino());
        embarcacoesComputador.add(new NavioEscolta());
        embarcacoesComputador.add(new AviaoCaca());
        
        System.out.println("Embarcacoes computador = " + embarcacoesComputador);
    }
    
    // Getters e setters
    public ArrayList<Embarcacao> getEmbarcacoesJogador() {
        return embarcacoesJogador;
    }

    public void setEmbarcacoesJogador(ArrayList<Embarcacao> embarcacoesJogador) {
        this.embarcacoesJogador = embarcacoesJogador;
    }

    public ArrayList<Embarcacao> getEmbarcacoesComputador() {
        return embarcacoesComputador;
    }

    public void setEmbarcacoesComputador(ArrayList<Embarcacao> embarcacoesComputador) {
        this.embarcacoesComputador = embarcacoesComputador;
    }
    
    // Retorna os tabuleiros do Jogador e do computador
    public int[][] getTabuleiroJogador() {
        return sorteioJogador.sortear(embarcacoesJogador);
    }
    
    public int[][] getTabuleiroComputador() {
        return sorteioComputador.sortear(embarcacoesComputador);
    }
}
