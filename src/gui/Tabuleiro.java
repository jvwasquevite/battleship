package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import batalhanaval.Jogar;

public class Tabuleiro extends JPanel implements ActionListener {
    JPanel grid = new JPanel();

    // Instanciando o painel do tabuleiro
    private JPanel contentPane = new JPanel();

    // Matriz de botoes da GUI
    JButton[][] Botoes = new JButton[10][10];
    
    // Matriz que referencia os botoes da GUI
    int matriz[][] = new int[10][10];
    
    // Classe Jogar
    Jogar jogo = new Jogar();
    
    public Tabuleiro(){
        contentPane.setBackground(Color.BLACK);
        grid.setLayout(new GridLayout(10, 10, 2, 2));
        
        // Adiciona os Bot�es no Grid
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
        
        contentPane.add(grid);
        add(contentPane);
        
        jogo.inicializarJogo(matriz);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                
                // Quando um botao eh clicado
                if (e.getSource() == Botoes[linha][coluna]) {
                    System.out.println(linha + "x" + coluna);
                    
                    if (matriz[linha][coluna] == 0) {
                        // Se não acertou
                        Botoes[linha][coluna].setEnabled(false);
                        System.out.println("errou");
                    } else if (matriz[linha][coluna] != 0) {
                        // Se acertou
                        System.out.println("acertou");                      
                        
                        if(matriz[linha][coluna] == 1) {
                            //Botoes[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/gui/imagens/portaaviao.png")));
                            Botoes[linha][coluna].setText(String.valueOf(matriz[linha][coluna]));
                            jogo.getEmbarcacao("Porta Avião").explodirEmbarcacao();
                        }
                        if(matriz[linha][coluna] == 2) {
                            //Botoes[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/gui/imagens/submarino.png")));
                            Botoes[linha][coluna].setText(String.valueOf(matriz[linha][coluna]));
                            jogo.getEmbarcacao("Submarino").explodirEmbarcacao();
                        }
                        if(matriz[linha][coluna] == 3) {
                            //Botoes[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/gui/imagens/navioescolta.png")));
                            Botoes[linha][coluna].setText(String.valueOf(matriz[linha][coluna]));
                            jogo.getEmbarcacao("Navio Escolta").explodirEmbarcacao();
                        }
                        if(matriz[linha][coluna] == 4) {
                            //Botoes[linha][coluna].setIcon(new ImageIcon(Tabuleiro.class.getResource("/gui/imagens/aviaocava.png")));
                            Botoes[linha][coluna].setText(String.valueOf(matriz[linha][coluna]));
                            jogo.getEmbarcacao("Avião Caça").explodirEmbarcacao();
                        }
                        
                        Botoes[linha][coluna].setEnabled(false);
                    }
                }
            }
        }
    }
}
