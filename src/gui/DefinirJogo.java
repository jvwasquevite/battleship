package gui;

import java.awt.Color;
import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import batalhanaval.DefineJogo;

public class DefinirJogo extends JFrame implements ActionListener {
    // Instanciando os paineis
    private final JPanel contentPane = new JPanel();
    private final JPanel grid = new JPanel();
    
    // Vetores do tabuleiro e dos botoes das embarcacoes
    private final JButton[][] botoes = new JButton[10][10];
    private final JButton[] botoesEmbarcacoes = new JButton[4];
    
    // Labels
    private final JLabel labelTitulo = new JLabel("Defina o seu Jogo");
    private final JLabel labelSubtitulo = new JLabel("Insira as embarcações em seu tabuleiro.");
    
    // Botoes
    private final JButton iniciarJogo = new JButton("Iniciar Jogo");
    private final JButton voltar = new JButton("Voltar");
    
    // Controla qual embarcacao esta selecionada
    private int embarcacaoSelecionada;
    
    // Controla se a matriz esta completa
    private int estaCompleta = 0;
    
    // Matriz do jogo
    private int[][] matriz;
    
    DefineJogo define = new DefineJogo();
    
    public DefinirJogo() {
        setTitle("Batalha Naval em Java ➜ Definir Jogo");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Estilização do painel
        setBounds(100, 100, 830, 650);
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        
        // Insere o painel no frame
        setContentPane(contentPane);
        
        // Titulo
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(30, 20, 290, 29);
        contentPane.add(labelTitulo);
        
        // Subtitulo
        labelSubtitulo.setFont(new Font("Arial", Font.ITALIC, 12));
        labelSubtitulo.setBounds(30, 50, 280, 15);
        contentPane.add(labelSubtitulo);
        
        // Botão de Iniciar Jogo
        iniciarJogo.setBounds(630, 30, 150, 36);
        iniciarJogo.setFont(new Font("Arial", Font.PLAIN, 12));
        iniciarJogo.setEnabled(false);
        iniciarJogo.addActionListener(this);		
        contentPane.add(iniciarJogo);
        
        // Botão de voltar
        voltar.setBounds(460, 30, 150, 36);
        voltar.setFont(new Font("Arial", Font.PLAIN, 12));
        voltar.addActionListener(this);		
        contentPane.add(voltar);
        
        // Botões de selecao das embarcacoes
        botoesEmbarcacoes[0] = new JButton();
        botoesEmbarcacoes[0].setBounds(580, 180, 200, 50);
        botoesEmbarcacoes[0].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/portaaviao.png")));
        botoesEmbarcacoes[0].setBackground(Color.decode("#d20000"));
        botoesEmbarcacoes[0].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[0]);
        
        botoesEmbarcacoes[1] = new JButton();
        botoesEmbarcacoes[1].setBounds(580, 240, 100, 50);
        botoesEmbarcacoes[1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/submarino.png")));
        botoesEmbarcacoes[1].setBackground(Color.decode("#1c52bb"));
        botoesEmbarcacoes[1].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[1]);
        
        botoesEmbarcacoes[2] = new JButton();
        botoesEmbarcacoes[2].setBounds(580, 300, 150, 50);
        botoesEmbarcacoes[2].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/navioescolta.png")));
        botoesEmbarcacoes[2].setBackground(Color.decode("#019131"));
        botoesEmbarcacoes[2].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[2]);
        
        botoesEmbarcacoes[3] = new JButton();
        botoesEmbarcacoes[3].setBounds(580, 360, 100, 50);
        botoesEmbarcacoes[3].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/aviaocaca.png")));
        botoesEmbarcacoes[3].setBackground(Color.decode("#962879"));
        botoesEmbarcacoes[3].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[3]);
        
        // Criando o tabuleiro
        grid.setLayout(new GridLayout(10, 10, 0, 0));
        
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++){
                botoes[linha][coluna] = new JButton("");
                botoes[linha][coluna].addActionListener(this);
                botoes[linha][coluna].setPreferredSize(new Dimension(30, 30));
                botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 8));
                botoes[linha][coluna].setFocusable(false);
                grid.add(botoes[linha][coluna]);
            }
        }
        
        grid.setBounds(30, 80, 500, 500);
        contentPane.add(grid);
        
        // Centralizando a tela
        setLocationRelativeTo(null);
     
    }
    
    // Getter e setter dos botoes
    public int getEmbarcacaoSelecionada() {
        return embarcacaoSelecionada;
    }

    public void setEmbarcacaoSelecionada(int embarcacaoSelecionada) {
        this.embarcacaoSelecionada = embarcacaoSelecionada;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Evento para voltar para a tela anterior
        if (e.getSource() == voltar){
            SwingUtilities.invokeLater(() -> {
                this.dispose();
                
                TelaInicio inicio = new TelaInicio();
                inicio.setVisible(true);
            });
        }
        
        // Evento de seleção de embarcações
        for (int i = 0; i < botoesEmbarcacoes.length; i++){
            if (e.getSource() == botoesEmbarcacoes[i]) {
                setEmbarcacaoSelecionada(i+1);
                botoesEmbarcacoes[i].setEnabled(false);
                
                System.out.println(getEmbarcacaoSelecionada());
            }
        }
        
        // Eventos de cliques no tabuleiro
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                matriz = define.getMatriz();
                 
                if (e.getSource() == botoes[linha][coluna]){
                    boolean coube = define.posicionar(linha, coluna, getEmbarcacaoSelecionada());
                    
                    if (coube == true) {
                        for (int i = 0; i < 10; i++){
                            for (int j = 0; j < 10; j++){
                               switch (matriz[linha][coluna]) {
                                    case 1:
                                        botoesEmbarcacoes[0].setEnabled(false);
                                        botoes[linha][coluna].setBackground(Color.decode("#d20000"));
                                        botoes[linha][coluna+1].setBackground(Color.decode("#d20000"));
                                        botoes[linha][coluna+2].setBackground(Color.decode("#d20000"));
                                        botoes[linha][coluna+3].setBackground(Color.decode("#d20000"));
                                        break;
                                    case 2:
                                        botoesEmbarcacoes[1].setEnabled(false);
                                        botoes[linha][coluna].setBackground(Color.decode("#1c52bb"));
                                        botoes[linha][coluna+1].setBackground(Color.decode("#1c52bb"));
                                        break;
                                    case 3:
                                        botoesEmbarcacoes[2].setEnabled(false);
                                        botoes[linha][coluna].setBackground(Color.decode("#019131"));
                                        botoes[linha][coluna+1].setBackground(Color.decode("#019131"));
                                        botoes[linha][coluna+2].setBackground(Color.decode("#019131"));
                                        break;
                                    case 4:
                                        botoesEmbarcacoes[3].setEnabled(false);
                                        botoes[linha][coluna].setBackground(Color.decode("#962879"));
                                        botoes[linha][coluna+1].setBackground(Color.decode("#962879"));
                                        break;
                                }
                            }
                        }
                        
                        for(int i=0; i<10; i++) {
                            for(int j=0; j<10; j++) {
                                System.out.print(matriz[i][j]);
                            }
                            System.out.print("\n");
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Posição inválida, escolha outra posição.");
                        botoesEmbarcacoes[getEmbarcacaoSelecionada() - 1].setEnabled(true);
                    }
                    
                    // Testa se a matriz ja possui todas embarcacoes
                    if(matriz[linha][coluna] != 0) {
                        estaCompleta++;
                    }
                    if(estaCompleta == 4) {
                        iniciarJogo.setEnabled(true);
                        setEmbarcacaoSelecionada(0);
                    }
                }
            }
        }
        
        // Evento para iniciar jogo
        if (e.getSource() == iniciarJogo){
            SwingUtilities.invokeLater(() -> {
                this.dispose();
                Jogo definido = new Jogo(matriz);
                definido.setVisible(true);
            });
        }
    }
}
