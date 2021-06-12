package batalhanaval;
import java.util.ArrayList;
import java.util.Random;

// Inicializa os arrays de embarcacoes
// Retorna os arrays de embarcacoes

public class Jogar {
    private ArrayList<Embarcacao> embJogador;
    private ArrayList<Embarcacao> embComputador;
    
    private int[][] matrizJogador;
    private int[][] matrizComputador;
    
    // Construtor para jogo aleatorio
    public Jogar() {
        SorteiaJogo sorteioJogador = new SorteiaJogo();
        SorteiaJogo sorteioComputador = new SorteiaJogo();
    
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
        
        // Retorna uma matriz de inteiros com 0, 1, 2, 3, 4
        this.matrizJogador = sorteioJogador.sortear(embJogador);
        this.matrizComputador = sorteioComputador.sortear(embComputador);
    }
    
    // Construtor para jogo definido
    public Jogar(int[][] matriz) {
        SorteiaJogo sorteioComputador = new SorteiaJogo();
    
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
        
        // Retorna uma matriz de inteiros com 0, 1, 2, 3, 4
        this.matrizJogador = matriz;
        this.matrizComputador = sorteioComputador.sortear(embComputador);
    }
    
    public boolean vezJogador(int linha, int coluna) {
        if (matrizComputador[linha][coluna] == 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você errou.");
            
            return false;
        }else if (matrizComputador[linha][coluna] != 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você acertou.");
            
            // Explode a embarcacao do computador
            switch (matrizComputador[linha][coluna]) {
                case 1: // PortaAviao
                    embComputador.get(0).explodirEmbarcacao();
                    break;
                case 2: // Submarino
                    embComputador.get(1).explodirEmbarcacao();
                    break;
                case 3: // NavioEscolta
                    embComputador.get(2).explodirEmbarcacao();
                    break;
                case 4: // AviaoCaca
                    embComputador.get(3).explodirEmbarcacao();
                    break;
            }
            
            // Zera a posicao onde estava a embarcacao explodida
            matrizComputador[linha][coluna] = 0;
            
            // Printa as embarcacoes do computador
            System.out.println("[UPDATE]: Embarcacacoes computador:");
            for (int i = 0; i < embComputador.size(); i++) {
                System.out.print(embComputador.get(i) + ": ");
                embComputador.get(i).ler();
            }

            return true;
        }
        
        return false;
    }
    
    public boolean vezComputador(int linha, int coluna) {
        if (matrizJogador[linha][coluna] == 0) {
            System.out.println("> " + linha + "x" + coluna + ": Computador errou.");
            
            return false;
        }else if (matrizJogador[linha][coluna] != 0) {
            System.out.println("> " + linha + "x" + coluna + ": Computador acertou.");
            
            // Explode a embarcacao do jogador
            switch (matrizJogador[linha][coluna]) {
                case 1: // PortaAviao
                    embJogador.get(0).explodirEmbarcacao();
                    break;
                case 2: // Submarino
                    embJogador.get(1).explodirEmbarcacao();
                    break;
                case 3: // NavioEscolta
                    embJogador.get(2).explodirEmbarcacao();
                    break;
                case 4: // AviaoCaca
                    embJogador.get(3).explodirEmbarcacao();
                    break;
            }
            
            // Zera a posicao onde estava a embarcacao explodida
            matrizJogador[linha][coluna] = 0;
            
            // Printa as embarcacoes do jogador
            System.out.println("[UPDATE]: Embarcacacoes jogador:");
            for (int i = 0; i < embJogador.size(); i++) {
                System.out.print(embJogador.get(i) + ": ");
                embJogador.get(i).ler();
            }

            return true;
        }
        
        return false;
    }
    
    public boolean jogadorGanhou() {
        int contador = 0;
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(matrizComputador[i][j] == 0) {
                    contador++;
                }
            }
        }
        
        return contador == 100;
    }
    
    public boolean computadorGanhou() {
        int contador = 0;
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(matrizJogador[i][j] == 0) {
                    contador++;
                }
            }
        }
        
        return contador == 100;
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
