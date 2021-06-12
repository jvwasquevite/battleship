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

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                if (e.getSource() == Botoes[linha][coluna]) {
                    System.out.println("\nNOVO ROUND:");
                    
                    // Implementa a jogada do Jogador
                    if(jogo.vezJogador(linha, coluna)) {
                        Botoes[linha][coluna].setEnabled(false);
                        Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
                        
                    } else {
                        // Se errou
                        Botoes[linha][coluna].setEnabled(false);
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
                    
                    // Teste se alguem ganhou
                    if(jogo.jogadorGanhou()) {
                        JOptionPane.showMessageDialog(null, "Parabens! Você ganhou.");
                    }
                    
                    if(jogo.computadorGanhou()) {
                        JOptionPane.showMessageDialog(null, "Você perdeu.");
                    }
                }
            }
        }
    }
}
