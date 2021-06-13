package batalhanaval;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Timer;

public class Jogar {
    private ArrayList<Embarcacao> embJogador;
    private ArrayList<Embarcacao> embComputador;
    
    private int[][] matrizJogador;
    private int[][] matrizComputador;
    
    private final Jogador jogador = new Jogador();
    private final InsereJogador insereJogador = new InsereJogador();
    
    private Timer timer;
    private int segundos;
    
    private boolean jogoFinalizado = false;
    
    // Construtor para jogo aleatorio
    public Jogar(String nomeJogador) {
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
        
        // Define o nome do jogador
        jogador.setNome(nomeJogador);
        this.inicializaContador();
    }
    
    // Construtor para jogo definido
    public Jogar(String nomeJogador, int[][] matriz) {
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
        
        // Define o nome do jogador
        jogador.setNome(nomeJogador);    
        this.inicializaContador();
    }
    
    private void inicializaContador() {
        timer = new Timer(1000, (ActionEvent e) -> {
            segundos++;
            
            // Para o contador se o jogador ganhou
            if(jogoFinalizado == true) {
                ((Timer) (e.getSource())).stop();
            }
        });
        
        timer.setInitialDelay(0);
        timer.start();
    }
    
    // Retorna o tempo final da partida
    public int getTempoFinal() {
        return segundos;
    }
    
    // Finaliza o jogo
    public void finalizarJogo() {
        this.jogoFinalizado = true;
        
        // Grava o tempo final do jogador
        jogador.setTempo(segundos);
        
        // Grava o jogador em um ArrayList
        insereJogador.adicionaJogador(jogador);
        
        // Grava a lista de jogadores no arquivo
        insereJogador.insereNoArquivo();
    }
    
    public String getDica() {
        for(int i=0; i < 10; i++) {
            for(int j=0; j < 10; j++) {
                if(matrizComputador[i][j] != 0) {
                    return String.format((i+1) + "x" + (j+1));
                }
            }
        }
        
        return "";
    }
    
    public boolean vezJogador(int linha, int coluna, int tipoTiro) {
        System.out.println("Tipo tiro: " + tipoTiro);
        
        switch (tipoTiro) {
            case 0:
                return tiroUnico(linha, coluna, matrizComputador, embComputador);
            case 1:
                return tiroUnico(linha, coluna, matrizComputador, embComputador);
            case 2:
                return tiroDuplo(linha, coluna, matrizComputador, embComputador);
            case 3:
                return tiroUnico(linha, coluna, matrizComputador, embComputador);
        }
        
        return false;
    }
    
    public boolean tiroUnico(int linha, int coluna, int[][] matriz, ArrayList<Embarcacao> embarcacoes) {
        if (matriz[linha][coluna] == 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você errou.");
            return false;
            
        } else if (matriz[linha][coluna] != 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você acertou.");
            
            // Explode a embarcacao do computador
            switch (matriz[linha][coluna]) {
                case 1: // PortaAviao
                    embarcacoes.get(0).explodirEmbarcacao();
                    break;
                case 2: // Submarino
                    embarcacoes.get(1).explodirEmbarcacao();
                    break;
                case 3: // NavioEscolta
                    embarcacoes.get(2).explodirEmbarcacao();
                    break;
                case 4: // AviaoCaca
                    embarcacoes.get(3).explodirEmbarcacao();
                    break;
            }
            
            // Zera a posicao onde estava a embarcacao explodida
            matriz[linha][coluna] = 0;
            
            // Printa as embarcacoes
            System.out.println("[UPDATE]: Embarcacacoes oponente:");
            for (int i = 0; i < embarcacoes.size(); i++) {
                System.out.print(embarcacoes.get(i) + ": ");
                embarcacoes.get(i).ler();
            }
            
            // Printa as embarcacoes
            System.out.println("[UPDATE]: Matriz oponente:");
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    System.out.print(matriz[i][j]);
                }
                System.out.print("\n");
            }
            System.out.print("\n");

            return true;
        }
        
        return false;
    }
    
    public boolean tiroDuplo(int linha, int coluna, int[][] matriz, ArrayList<Embarcacao> embarcacoes) {
        if (matriz[linha][coluna] == 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você errou.");
            return false;
            
        }else if (matriz[linha][coluna] != 0) {
            System.out.println("> " + linha + "x" + coluna + ": Você acertou.");
            
            // Explode a embarcacao do computador
            switch (matriz[linha][coluna]) {
                case 1: // PortaAviao
                    embarcacoes.get(0).explodirEmbarcacao();
                    break;
                case 2: // Submarino
                    embarcacoes.get(1).explodirEmbarcacao();
                    break;
                case 3: // NavioEscolta
                    embarcacoes.get(2).explodirEmbarcacao();
                    break;
                case 4: // AviaoCaca
                    embarcacoes.get(3).explodirEmbarcacao();
                    break;
            }
            
            // Zera a posicao onde estava a embarcacao explodida
            matriz[linha][coluna] = 0;
            
            // Printa as embarcacoes
            System.out.println("[UPDATE]: Embarcacacoes oponente:");
            for (int i = 0; i < embarcacoes.size(); i++) {
                System.out.print(embarcacoes.get(i) + ": ");
                embarcacoes.get(i).ler();
            }
            
            // Printa as embarcacoes
            System.out.println("[UPDATE]: Matriz oponente:");
            for(int i=0; i<10; i++) {
                for(int j=0; j<10; j++) {
                    System.out.print(matriz[i][j]);
                }
                System.out.print("\n");
            }
            System.out.print("\n");

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
        
        // Armazena o tempo final do jogador
        if (contador == 100) {
            jogador.setTempo(this.getTempoFinal());
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

    public boolean isJogoFinalizado() {
        return jogoFinalizado;
    }
}
