package batalhanaval;

import java.util.ArrayList;

public class Jogar {
    // Inicializa ArrayList de embarcacoes do jogador e do computador
    private ArrayList<Embarcacao> embarcacoesJogador = new ArrayList();
    private ArrayList<Embarcacao> embarcacoesComputador = new ArrayList();
    
    SorteiaJogo sorteioJogador = new SorteiaJogo();
    SorteiaJogo sorteioComputador = new SorteiaJogo();
    
    public Jogar() {
        // Preenche o ArrayList do Jogador
        embarcacoesJogador.add(new PortaAviao());
        embarcacoesJogador.add(new Submarino());
        embarcacoesJogador.add(new NavioEscolta());
        embarcacoesJogador.add(new AviaoCaca());
        
        System.out.println(embarcacoesJogador);
        
        // Preenche o ArrayList do Computador
        embarcacoesComputador.add(new PortaAviao());
        embarcacoesComputador.add(new Submarino());
        embarcacoesComputador.add(new NavioEscolta());
        embarcacoesComputador.add(new AviaoCaca());
        
        System.out.println(embarcacoesComputador);
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
    
    public int[][] getTabuleiroJogador() {
        return sorteioJogador.sortear(embarcacoesJogador);
    }
    
    public int[][] getTabuleiroComputador() {
        return sorteioComputador.sortear(embarcacoesComputador);
    }
}
