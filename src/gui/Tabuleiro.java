package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import batalhanaval.Jogar;
import java.util.Random;

// Cria uma matriz de botoes para a interface
// Recebe uma matriz de inteiros + boolean visible
// Retorna a interface do Tabuleiro

// Eventos implementados:
// Se erra, desativa o botao
// Se acerta, muda a cor do botao para a cor da embarcacao atingida

public class Tabuleiro extends JPanel implements ActionListener {
    private final JPanel contentPane;
    private final JPanel grid = new JPanel();
    private final Botao[][] Botoes;
    
    // Atributos com o jogo e a matriz gerada
    private final Jogar jogo;
    private final int[][] matriz;
    
    // Atributo com o Tabuleiro do oponente
    private Tabuleiro tabOponente;
    
    // Botoes de tiros
    private JButton[] botoesTiro;
    private int tipoTiro;
    
    private int rodadaTiroAviao = 0;

    // Construtor da interface
    public Tabuleiro(Jogar jogo, int[][] matriz) {
      this.contentPane = new JPanel();
      this.Botoes = new Botao[10][10];

      this.jogo = jogo;
      this.matriz = matriz;

      contentPane.setBackground(Color.decode("#999999"));
      grid.setLayout(new GridLayout(10, 10, 0, 0));

      for (int linha = 0; linha < 10; linha++){
        for (int coluna = 0; coluna < 10; coluna++){
            Botoes[linha][coluna] = new Botao();
            Botoes[linha][coluna].setPreferredSize(new Dimension(40, 40));
            Botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 8));
                
            grid.add(Botoes[linha][coluna]);
        }
      }

      contentPane.add(grid);
      this.add(contentPane);
    }
    
    // Tabuleiro do jogador
    public Tabuleiro(Jogar jogo){
      this(jogo, jogo.getMatrizJogador()); // Chama o construtor da interface
      
      // Estiliza o tabuleiro do jogador
      for (int linha = 0; linha < 10; linha++){
        for (int coluna = 0; coluna < 10; coluna++){
          Botoes[linha][coluna].setEnabled(false);

          switch (matriz[linha][coluna]) {
              case 1:
                  Botoes[linha][coluna].setBackground(Color.decode("#d20000"));
                  break;
              case 2:
                  Botoes[linha][coluna].setBackground(Color.decode("#1c52bb"));
                  break;
              case 3:
                  Botoes[linha][coluna].setBackground(Color.decode("#019131"));
                  break;
              case 4:
                  Botoes[linha][coluna].setBackground(Color.decode("#962879"));
                  break;
          }
        }
      }
    }

    // Tabuleiro do computador
    public Tabuleiro(Jogar jogo, JButton[] botoesTiro){
      this(jogo, jogo.getMatrizComputador()); // Chama o construtor da interface
      this.botoesTiro = botoesTiro;
      
      // Estiliza o tabuleiro do computador
      for (int linha = 0; linha < 10; linha++){
        for (int coluna = 0; coluna < 10; coluna++){
          Botoes[linha][coluna].addActionListener(this);
          Botoes[linha][coluna].setPreferredSize(new Dimension(40, 40));
          Botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 8));
              
          grid.add(Botoes[linha][coluna]);
        }
      }
    }
    
    // Getters para o tabuleiro do oponente e botoes de tiro
    public void oponente(Tabuleiro oponente) {
        this.tabOponente = oponente;
    }
    
    public void tipoTiro(int tipo) {
        this.tipoTiro = tipo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                if (e.getSource() == Botoes[linha][coluna]) {
                    System.out.println("\nNOVO ROUND [" + jogo.getContadorRodadas() + "]:");
                    System.out.println("Seu tiro: " + botoesTiro[tipoTiro].getText());
                    
                    // Implementa a jogada do Jogador com testes de index
                    switch(tipoTiro) {
                        case 0: // Tiro unico de 2 em 2 rodadas
                            Botoes[linha][coluna].setEnabled(false);
                            if (jogo.tiroJogador(linha, coluna)) {
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            // Armazena a rodada do tiro
                            rodadaTiroAviao = jogo.getContadorRodadas();
                            botoesTiro[0].setEnabled(false);
                            botoesTiro[0].setFont(new Font("Arial", Font.PLAIN, 12));
                            
                            this.tipoTiro = 1;
                            botoesTiro[1].setEnabled(true);
                            botoesTiro[1].setFont(new Font("Arial", Font.BOLD, 12));
                            break;
                        case 1: // Tiro unico
                            Botoes[linha][coluna].setEnabled(false);
                            if (jogo.tiroJogador(linha, coluna)) {
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            break;
                        case 2: // Tiro duplo
                            // Centro
                            Botoes[linha][coluna].setEnabled(false);
                            if (jogo.tiroJogador(linha, coluna)) {
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            // Direito
                            if(!((coluna+1) == 10)) {
                                Botoes[linha][coluna+1].setEnabled(false);
                                if (jogo.tiroJogador(linha, coluna+1)) {
                                    Botoes[linha][coluna+1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                            }
                            break;
                        case 3: // Tiro estrela
                            // Centro
                            Botoes[linha][coluna].setEnabled(false);
                            if (jogo.tiroJogador(linha, coluna)) {
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            // Direito
                            if(!((coluna+1) == 10)) {
                                Botoes[linha][coluna+1].setEnabled(false);
                                if (jogo.tiroJogador(linha, coluna+1)) {
                                    Botoes[linha][coluna+1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                            }
                            
                            // Esquerdo
                            if(!((coluna-1) == -1)) {
                                Botoes[linha][coluna-1].setEnabled(false);
                                if (jogo.tiroJogador(linha, coluna-1)) {
                                    Botoes[linha][coluna-1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                            }

                            // Topo
                            if(!((linha-1) == -1)) {
                                Botoes[linha-1][coluna].setEnabled(false);
                                if (jogo.tiroJogador(linha-1, coluna)) {
                                    Botoes[linha-1][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                            }
                            
                            // Baixo
                            if(!((linha+1) == 10)) {
                                Botoes[linha+1][coluna].setEnabled(false);
                                if (jogo.tiroJogador(linha+1, coluna)) {
                                    Botoes[linha+1][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                            }
                            break;
                    }

                    // Implementa a jogada do Computador
                    Random sorteio = new Random();
                    int linhaSort = sorteio.nextInt(10);
                    int colunaSort = sorteio.nextInt(10);
                    int tiroSort = sorteio.nextInt(4);
                    
                    System.out.println("Tiro do oponente: " + botoesTiro[tiroSort].getText());
                    
                    // Verifica se a posicao sorteada ja nao foi atingida
                    boolean verifica = jogo.verificaTiro(linhaSort, colunaSort);
                    
                    if(verifica == false) {
                        while(verifica == false) {
                            linhaSort = sorteio.nextInt(10);
                            colunaSort = sorteio.nextInt(10);
                            
                            verifica = jogo.verificaTiro(linhaSort, colunaSort);
                        }
                    }
                    
                    switch(tiroSort) {
                        case 0: // Tiro unico de 2 em 2 rodadas
                            tabOponente.Botoes[linhaSort][colunaSort].setEnabled(false);
                            jogo.tiroComputador(linhaSort, colunaSort);
                            tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            break;
                        case 1: // Tiro unico
                            tabOponente.Botoes[linhaSort][colunaSort].setEnabled(false);
                            jogo.tiroComputador(linhaSort, colunaSort);
                            tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            break;
                        case 2: // Tiro duplo
                            // Centro
                            tabOponente.Botoes[linhaSort][colunaSort].setEnabled(false);
                            jogo.tiroComputador(linhaSort, colunaSort);
                            tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            
                            // Direito
                            if(!((colunaSort+1) == 10)) {
                                tabOponente.Botoes[linhaSort][colunaSort+1].setEnabled(false);
                                jogo.tiroComputador(linhaSort, colunaSort+1);
                                tabOponente.Botoes[linhaSort][colunaSort+1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            break;
                        case 3: // Tiro estrela
                            // Centro
                            tabOponente.Botoes[linhaSort][colunaSort].setEnabled(false);
                            jogo.tiroComputador(linhaSort, colunaSort);
                            tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            
                            // Direito
                            if(!((colunaSort+1) == 10)) {
                                tabOponente.Botoes[linhaSort][colunaSort+1].setEnabled(false);
                                jogo.tiroComputador(linhaSort, colunaSort+1);
                                tabOponente.Botoes[linhaSort][colunaSort+1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            // Esquerdo
                            if(!((colunaSort-1) == -1)) {
                                tabOponente.Botoes[linhaSort][colunaSort-1].setEnabled(false);
                                jogo.tiroComputador(linhaSort, colunaSort-1);
                                tabOponente.Botoes[linhaSort][colunaSort-1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }

                            // Topo
                            if(!((linhaSort-1) == -1)) {
                                tabOponente.Botoes[linhaSort-1][colunaSort].setEnabled(false);
                                jogo.tiroComputador(linhaSort-1, colunaSort);
                                tabOponente.Botoes[linhaSort-1][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            // Baixo
                            if(!((linhaSort+1) == 10)) {
                                tabOponente.Botoes[linhaSort+1][colunaSort].setEnabled(false);
                                jogo.tiroComputador(linhaSort+1, colunaSort);
                                tabOponente.Botoes[linhaSort+1][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                            }
                            
                            break;
                    }
                    
                    // Testa se existe alguma embarcacao totalmente atingida
                    for(int i=0; i<4; i++) {
                        if(jogo.getEmbJogador().get(i).getTamanhoEmbarcacao() == 0) {
                            botoesTiro[i].setEnabled(false);
                        }
                    }
                    
                    // Testa se ja passou duas rodadas
                    if(!botoesTiro[0].isEnabled()) {
                        if(jogo.verificaRodadasTiroAviao(rodadaTiroAviao)) {
                            botoesTiro[0].setEnabled(true);
                        }
                    }
                    
                    // Testa se alguem ganhou
                    if(jogo.jogadorGanhou()) {
                        jogo.finalizarJogo(true);
                        
                        String tempoFinal = String.format("%02d:%02d", jogo.getTempoFinal() / 60, jogo.getTempoFinal() % 60);
                        Object[] options = {"Jogar novamente", "Ver ranking"};
                        
                        int result = JOptionPane.showOptionDialog(null,"Parabéns! Você ganhou. \n" + "Tempo final: " + tempoFinal, "Jogo finalizado",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                        switch (result) {
                            case 0:
                                // Sair do jogo
                                SwingUtilities.invokeLater(() -> {
                                    TelaInicio inicio = new TelaInicio();
                                    inicio.setVisible(true);
                                });
                                break;
                            case 1:
                                SwingUtilities.invokeLater(() -> {
                                    Ranking ranking = new Ranking();
                                    ranking.setVisible(true);
                                });
                                break;
                        }
                    }
                    
                    if(jogo.computadorGanhou()) {
                        jogo.finalizarJogo(false);
                        
                        String tempoFinal = String.format("%02d:%02d", jogo.getTempoFinal() / 60, jogo.getTempoFinal() % 60);
                        Object[] options = {"Jogar novamente", "Ver ranking"};
                        
                        int result = JOptionPane.showOptionDialog(null,"Você perdeu. \n" + "Tempo final: " + tempoFinal, "Jogo finalizado",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                        switch (result) {
                            case 0:
                                // Sair do jogo
                                SwingUtilities.invokeLater(() -> {
                                    TelaInicio inicio = new TelaInicio();
                                    inicio.setVisible(true);
                                });
                                break;
                            case 1:
                                SwingUtilities.invokeLater(() -> {
                                    Ranking ranking = new Ranking();
                                    ranking.setVisible(true);
                                });
                                break;
                        }
                    }
                    
                    // Incrementa o numero de rodadas
                    jogo.setContadorRodadas(jogo.getContadorRodadas()+1);
                }
            }
        }
    }
}
