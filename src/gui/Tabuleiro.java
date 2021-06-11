package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Cria uma matriz de botoes para a interface
// Recebe a matriz gerada em Jogar

public class Tabuleiro extends JPanel implements ActionListener {
    private final JButton[][] Botoes;
    private final int matriz[][];
    
    private final JPanel contentPane;
    private final JPanel grid = new JPanel();
    
    public Tabuleiro(int matriz[][], boolean visible){
        this.contentPane = new JPanel();
        this.Botoes = new JButton[10][10];
        this.matriz = matriz;
        
        contentPane.setBackground(Color.decode("#999999"));
        grid.setLayout(new GridLayout(10, 10, 0, 0));
        
        if (visible == true) {
            // Tabuleiro do jogador
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                
                // Quando um botao eh clicado
                if (e.getSource() == Botoes[linha][coluna]) {
                    System.out.print(linha + "x" + coluna + ":");
                    
                    if (matriz[linha][coluna] == 0) {
                        // Se não acertou
                        Botoes[linha][coluna].setEnabled(false);
                        System.out.println("errou!");
                    } else if (matriz[linha][coluna] != 0) {
                        // Se acertou
                        System.out.println("acertou!");
                        
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
        }
    }
}
