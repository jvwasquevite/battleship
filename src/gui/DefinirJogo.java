package gui;

import java.awt.Dimension;
import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirJogo extends JFrame implements ActionListener {
    // Instanciando os paineis
    private JPanel contentPane = new JPanel();
    private JPanel grid = new JPanel();
    
    // Vetores do tabuleiro e dos botoes das embarcacoes
    private JButton[][] botoes = new JButton[10][10];
    private JButton[] botoesEmbarcacoes = new JButton[4];
    
    // Controla qual embarcacao esta selecionada
    private int embarcacaoSelecionada;
    
    // Labels
    private JLabel labelTitulo = new JLabel("Defina o seu Jogo");
    private JLabel labelSubtitulo = new JLabel("Insira as embarcações em seu tabuleiro.");
    
    // Botoes
    private JButton iniciarJogo = new JButton("Iniciar Jogo");
    private JButton voltar = new JButton("Voltar");
    
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
        botoesEmbarcacoes[0].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[0]);
        
        botoesEmbarcacoes[1] = new JButton();
        botoesEmbarcacoes[1].setBounds(580, 240, 100, 50);
        botoesEmbarcacoes[1].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/submarino.png")));
        botoesEmbarcacoes[1].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[1]);
        
        botoesEmbarcacoes[2] = new JButton();
        botoesEmbarcacoes[2].setBounds(580, 300, 150, 50);
        botoesEmbarcacoes[2].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/navioescolta.png")));
        botoesEmbarcacoes[2].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[2]);
        
        botoesEmbarcacoes[3] = new JButton();
        botoesEmbarcacoes[3].setBounds(580, 360, 100, 50);
        botoesEmbarcacoes[3].setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/aviaocaca.png")));
        botoesEmbarcacoes[3].addActionListener(this);
        contentPane.add(botoesEmbarcacoes[3]);
        
        // Criando o tabuleiro
        grid.setLayout(new GridLayout(10, 10, 0, 0));
        
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++){
                botoes[linha][coluna] = new JButton("");
                botoes[linha][coluna].addActionListener(this);
                botoes[linha][coluna].setPreferredSize(new Dimension(40, 40));
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
                setEmbarcacaoSelecionada(i);
                System.out.println(getEmbarcacaoSelecionada());
            }
        }
        
        // Eventos de cliques no tabuleiro
        for (int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10; coluna++) {
                if (e.getSource() == botoes[linha][coluna]){
                     System.out.println(linha + "x" + coluna);
                     // inserir aqui controle se cabe ou nao
                }
            }
        }
    }
}
