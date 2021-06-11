package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;
import batalhanaval.Embarcacao;
import batalhanaval.JogadaComputador;
import java.util.Random;

// Cria uma matriz de botoes para a interface
// Recebe uma matriz de inteiros + boolean visible
// Retorna a interface do Tabuleiro

// Eventos implementados:
// Se erra, desativa o botao
// Se acerta, muda a cor do botao para a cor da embarcacao atingida

public class Tabuleiro extends JPanel implements ActionListener {
    private final JButton[][] Botoes;
    
    private ArrayList<Embarcacao> embarcacoes;
    private ArrayList<Embarcacao> embOponente;
    private Tabuleiro tabOponente;
    private final int matriz[][];
    
    private final JPanel contentPane;
    private final JPanel grid = new JPanel();
    private final ArrayList<JButton> botoesTiro;
    
    public Tabuleiro(ArrayList<Embarcacao> embarcacoes, ArrayList<Embarcacao> embOponente, Tabuleiro oponente, int matriz[][], ArrayList<JButton> botoesTiro, boolean visible){
        this.contentPane = new JPanel();
        this.Botoes = new JButton[10][10];
        this.matriz = matriz;
        this.embarcacoes = embarcacoes;
        this.embOponente = embOponente;
        this.tabOponente = oponente;
        this.botoesTiro = botoesTiro;
        
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
    
    public void jogadaComputador() {
        Random sorteio = new Random();
        int linha = sorteio.nextInt(10);
        int coluna = sorteio.nextInt(10);
        
        tabOponente.Botoes[linha][coluna].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/explosao.png")));
        
        if(tabOponente.matriz[linha][coluna] == 0) {
            System.out.println("pc errou");
        } else if (tabOponente.matriz[linha][coluna] != 0) {
            switch (tabOponente.matriz[linha][coluna]) {
                case 1:
                    embarcacoes.get(0).explodirEmbarcacao();
                    // Se tiver totalmente destruida
                    if(!embarcacoes.get(0).getStatusEmbarcacao()) {
                        tabOponente.botoesTiro.get(0).setEnabled(false);
                    }
                    break;
                case 2:
                    embarcacoes.get(1).explodirEmbarcacao();
                    // Se tiver totalmente destruida
                    if(!embarcacoes.get(1).getStatusEmbarcacao()) {
                        tabOponente.botoesTiro.get(1).setEnabled(false);
                    }
                    break;
                case 3:
                    embarcacoes.get(2).explodirEmbarcacao();
                    // Se tiver totalmente destruida
                    if(!embarcacoes.get(2).getStatusEmbarcacao()) {
                        tabOponente.botoesTiro.get(2).setEnabled(false);
                    }
                    break;
                case 4:
                    embarcacoes.get(3).explodirEmbarcacao();
                    // Se tiver totalmente destruida
                    if(!embarcacoes.get(3).getStatusEmbarcacao()) {
                        tabOponente.botoesTiro.get(3).setEnabled(false);
                    }
                    break;
                }
        }
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
                        System.out.println("errou!");
                        Botoes[linha][coluna].setEnabled(false);
                        jogadaComputador();
                        
                    } else if (matriz[linha][coluna] != 0) {
                        // Se acertou
                        System.out.println("acertou!");
                        
                        switch (matriz[linha][coluna]) {
                        case 1:
                            Botoes[linha][coluna].setBackground(Color.decode("#d20000"));
                            Botoes[linha][coluna].setEnabled(false);
                            embOponente.get(0).explodirEmbarcacao();
                            break;
                        case 2:
                            Botoes[linha][coluna].setBackground(Color.decode("#1c52bb"));
                            Botoes[linha][coluna].setEnabled(false);
                            embOponente.get(1).explodirEmbarcacao();
                            System.out.println(embOponente.get(1).getTamanhoEmbarcacao());
                            break;
                        case 3:
                            Botoes[linha][coluna].setBackground(Color.decode("#019131"));
                            Botoes[linha][coluna].setEnabled(false);
                            embOponente.get(2).explodirEmbarcacao();
                            System.out.println(embOponente.get(2).getTamanhoEmbarcacao());
                            break;
                        case 4:
                            Botoes[linha][coluna].setBackground(Color.decode("#962879"));
                            Botoes[linha][coluna].setEnabled(false);
                            embOponente.get(3).explodirEmbarcacao();
                            System.out.println(embOponente.get(3).getTamanhoEmbarcacao());
                            break;
                        }
                        
                        jogadaComputador();
                    }
                }
            }
        }
    }
}
