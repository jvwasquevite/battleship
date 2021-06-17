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
    
    private int contadorRodadas = 0;
    private boolean jogoFinalizado = false;
    
    // Construtor para jogo aleatorio
    public Jogar(String nomeJogador) {
        SorteiaJogo sorteioJogador = new SorteiaJogo();
        SorteiaJogo sorteioComputador = new SorteiaJogo();
    
        this.embJogador = new ArrayList<Embarcacao>();
        this.embComputador = new ArrayList<Embarcacao>();
       
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
    
        this.embJogador = new ArrayList<Embarcacao>();
        this.embComputador = new ArrayList<Embarcacao>();;
       
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
    public void finalizarJogo(boolean ganhou) {
        this.jogoFinalizado = true;
        
        if(ganhou) {
            // Grava o tempo final do jogador
            jogador.setTempo(segundos);

            // Grava o jogador em um ArrayList
            insereJogador.adicionaJogador(jogador);

            // Grava a lista de jogadores no arquivo
            insereJogador.insereNoArquivo();
        }
    }
    
    public String getDica() {
        for(int i=0; i < 10; i++) {
            for(int j=0; j < 10; j++) {
                if(matrizComputador[i][j] != 0 && matrizComputador[i][j] != 5) {
                    return String.format((i+1) + "x" + (j+1));
                }
            }
        }
        
        return "";
    }
    
    public boolean tiroJogador(int linha, int coluna) {
        return atirar(linha, coluna, matrizComputador, embComputador);
    }
    
    public void tiroComputador(int linha, int coluna) {
        atirar(linha, coluna, matrizJogador, embJogador);
    }
    
    public boolean atirar(int linha, int coluna, int[][] matriz, ArrayList<Embarcacao> embarcacoes) {
        if (matriz[linha][coluna] == 0 || matriz[linha][coluna] == 5) {
            System.out.println("> " + linha + "x" + coluna + ": Errou.");
            
            // Insere 5 na posicao atingida
            matriz[linha][coluna] = 5;
            
            return false;
        } else if (matriz[linha][coluna] != 0 || matriz[linha][coluna] == 5) {
            System.out.println("> " + linha + "x" + coluna + ": Acertou.");
            
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
            
            // Insere 5 na embarcacao atingida
            matriz[linha][coluna] = 5;
            
            // Printa as embarcacoes quando acerta
            System.out.println("[UPDATE]: Embarcacacoes oponente:");
            for (int i = 0; i < embarcacoes.size(); i++) {
                System.out.print(embarcacoes.get(i) + ": ");
                embarcacoes.get(i).ler();
            }
            
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
    
    // Verifica posicao do tiro do computador para nao atirar novamente no mesmo local
    public boolean verificaTiro(int linha, int coluna) {
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                // Testa se a posicao ja foi atingida anteriormente
                return matrizJogador[linha][coluna] != 5;
            }
        }
        
        return false;
    }
    
    // Verifica rodadas do tiroAviao
    public boolean verificaRodadasTiroAviao(int rodadaInicial) {
        int rodadaFinal = rodadaInicial + 2;
        return rodadaFinal == this.getContadorRodadas();
    }
    
    public boolean jogadorGanhou() {
        int contador = 0;
        
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(matrizComputador[i][j] == 0 || matrizComputador[i][j] == 5) {
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
                if(matrizJogador[i][j] == 0 || matrizJogador[i][j] == 5) {
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

    public int getContadorRodadas() {
        return contadorRodadas;
    }

    public void setContadorRodadas(int contadorRodadas) {
        this.contadorRodadas = contadorRodadas;
    }

    public boolean isJogoFinalizado() {
        return jogoFinalizado;
    }
}
