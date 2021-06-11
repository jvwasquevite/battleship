package batalhanaval;
import java.util.ArrayList;

// Inicializa os arrays de embarcacoes
// Retorna os arrays de embarcacoes

public class Jogar {
    private ArrayList<Embarcacao> embJogador;
    private ArrayList<Embarcacao> embComputador;
    
    SorteiaJogo sorteioJogador = new SorteiaJogo();
    SorteiaJogo sorteioComputador = new SorteiaJogo();
    
    private int[][] matrizJogador;
    private int[][] matrizComputador;
    
    public Jogar() {
        this.embJogador = new ArrayList();
        this.embComputador = new ArrayList();
       
        embJogador.add(new PortaAviao());
        embJogador.add(new Submarino());
        embJogador.add(new NavioEscolta());
        embJogador.add(new AviaoCaca());

        embComputador.add(new PortaAviao());
        embComputador.add(new Submarino());
        embComputador.add(new NavioEscolta());
        embComputador.add(new AviaoCaca());
        
        System.out.println("Embarcacoes embJogador = " + embJogador);
        System.out.println("Embarcacoes embComputador = " + embComputador);
        
        this.matrizJogador = sorteioJogador.sortear(embJogador);
        this.matrizComputador = sorteioComputador.sortear(embComputador);
    }
    
    // Getters e setters
    public ArrayList<Embarcacao> getEmbJogador() {
        return embJogador;
    }

    public void setEmbJogador(ArrayList<Embarcacao> embJogador) {
        this.embJogador = embJogador;
    }

    public ArrayList<Embarcacao> getEmbComputador() {
        return embComputador;
    }

    public void setEmbComputador(ArrayList<Embarcacao> embComputador) {
        this.embComputador = embComputador;
    }
    
     public int[][] getMatrizJogador() {
        return matrizJogador;
    }

    public void setMatrizJogador(int[][] matrizJogador) {
        this.matrizJogador = matrizJogador;
    }

    public int[][] getMatrizComputador() {
        return matrizComputador;
    }

    public void setMatrizComputador(int[][] matrizComputador) {
        this.matrizComputador = matrizComputador;
    }
}
