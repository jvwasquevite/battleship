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
    private final JButton[][] Botoes;
    
    // Atributos com o jogo e a matriz gerada
    private final Jogar jogo;
    private final int[][] matriz;
    
    // Atributo com o Tabuleiro do oponente
    private Tabuleiro tabOponente;
    
    // Botoes de tiros
    private final JButton[] botoesTiro;
    private int tipoTiro;
    
    private int contadorRodadas;
    
    public Tabuleiro(Jogar jogo, JButton[] botoesTiro, boolean visible){
        this.jogo = jogo;
        this.contentPane = new JPanel();
        this.Botoes = new JButton[10][10];
        this.botoesTiro = botoesTiro;
        
        contentPane.setBackground(Color.decode("#999999"));
        grid.setLayout(new GridLayout(10, 10, 0, 0));
        
        if (visible == true) {
            // Tabuleiro do jogador
            this.matriz = jogo.getMatrizJogador();
            
            for (int linha = 0; linha < 10; linha++){
                for (int coluna = 0; coluna < 10; coluna++){
                    Botoes[linha][coluna] = new JButton("");
                    Botoes[linha][coluna].setPreferredSize(new Dimension(40, 40));
                    Botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 8));
                    Botoes[linha][coluna].setFocusable(true);
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

                    grid.add(Botoes[linha][coluna]);
                }
            }
        } else {
            // Tabuleiro do computador
            this.matriz = jogo.getMatrizComputador();
            
            for (int linha = 0; linha < 10; linha++){
                for (int coluna = 0; coluna < 10; coluna++){
                    Botoes[linha][coluna] = new JButton("");
                    Botoes[linha][coluna].addActionListener(this);
                    Botoes[linha][coluna].setPreferredSize(new Dimension(40, 40));
                    Botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 8));
                    Botoes[linha][coluna].setFocusable(false);
                    grid.add(Botoes[linha][coluna]);
                }
            }
        }
        
        contentPane.add(grid);
        add(contentPane);
    }
    
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
                    contadorRodadas++;
                    System.out.println("\nNOVO ROUND:");
                    
                    // Implementa a jogada do Jogador
                    if(jogo.vezJogador(linha, coluna, tipoTiro)) {
                        switch (tipoTiro) {
                            case 0: // Unico a cada duas todadas
                                Botoes[linha][coluna].setEnabled(false);
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                botoesTiro[0].setEnabled(false);
                                break;
                            case 1: // Unico
                                Botoes[linha][coluna].setEnabled(false);
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                break;
                            case 2: // Duplo
                                for (int  i=0; i<2; i++) {
                                    Botoes[linha][coluna++].setEnabled(false);
                                    Botoes[linha][coluna++].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                }
                                break;
                            case 3: // Estrela
                                Botoes[linha][coluna].setEnabled(false);
                                Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                                break;
                        }                  
                    } else {
                        // Se errou
                        switch (tipoTiro) {
                            case 0: // Unico a cada duas todadas
                                Botoes[linha][coluna].setEnabled(false);
                                break;
                            case 1: // Unico
                                Botoes[linha][coluna].setEnabled(false);
                                break;
                            case 2: // Duplo
                                    if(coluna < 9) {
                                        for (int  i=0; i<2; i++) {
                                            Botoes[linha][coluna++].setEnabled(false);
                                        } 
                                    } else {
                                        Botoes[linha][coluna].setEnabled(false);
                                    }
                                break;
                            case 3: // Estrela
                                Botoes[linha][coluna].setEnabled(false);
                                Botoes[linha][coluna+1].setEnabled(false);
                                Botoes[linha][coluna-1].setEnabled(false);
                                Botoes[linha+1][coluna].setEnabled(false);
                                Botoes[linha-1][coluna].setEnabled(false);
                                break;
                        }
                    }
                    
                    // Implementa a jogada do Computador
                    Random sorteio = new Random();
                    int linhaSort = sorteio.nextInt(10);
                    int colunaSort = sorteio.nextInt(10);
                    
                    if(jogo.vezComputador(linhaSort, colunaSort)) {
                        // Se acertou
                        tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                        
                        // Testa se tem alguma embarcacao totalmente atingida
                        for(int i = 0; i < 4; i++) {
                            if(jogo.getEmbJogador().get(i).getTamanhoEmbarcacao() == 0) {
                                botoesTiro[i].setEnabled(false);
                            }
                        }
                    } else {
                        // Se errou
                        tabOponente.Botoes[linhaSort][colunaSort].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                    }
                    
                    // Testa se alguem ganhou
                    if(jogo.jogadorGanhou()) {
                        jogo.finalizarJogo();
                        
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
                        jogo.finalizarJogo();
                        JOptionPane.showMessageDialog(null, "Você perdeu.");
                    }
                }
            }
        }
    }
}
