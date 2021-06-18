package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import batalhanaval.Jogar;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Jogo extends JFrame implements ActionListener {
    // Instanciando o painel do jogo
    private final JPanel contentPane = new JPanel();
    
    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroComputador;
    
    // Labels
    private final JLabel labelTitulo;
    private final JLabel labelSubtitulo = new JLabel("Destrua as 4 embarcações do oponente.");
    
    // Botoes
    private final JButton dica = new JButton("Solicitar Dica");
    private final JButton sair = new JButton("Sair do Jogo");
    
    private int qntdDicas = 3;
    private final JLabel dicaLabel = new JLabel(this.qntdDicas + " restantes");
    
    // Botoes das embarcacoes
    private final JButton[] botoesTiro = new JButton[4];
    private int tiroSelecionado = 0;
            
    private final JButton tiroPortaAviao = new JButton("Tiro porta-avião");
    private final JButton tiroUnico = new JButton("Tiro unico");
    private final JButton tiroDuplo = new JButton("Tiro duplo");
    private final JButton tiroEstrela = new JButton("Tiro estrela");
    
    // Labels dos botoes
    private final JLabel labelPortaAviao = new JLabel("1 tiro a cada 2 rodadas");
    private final JLabel labelUnico = new JLabel("Submarino");
    private final JLabel labelDuplo = new JLabel("Navio escolta");
    private final JLabel labelEstrela = new JLabel("Avião de caça");
    
    // Contador
    private final JLabel labelTimer = new JLabel("");
    private Timer timer;
    private int contador;
    
    private Jogar jogo;
    private String nomeJogador;
    
    // Construtor da interface
    public Jogo() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Estilização do painel
        setBounds(100, 100, 1000, 630);
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        
        // Insere o painel no frame
        setContentPane(contentPane);
        
        // Titulo
        labelTitulo = new JLabel("Bem vindo, " + nomeJogador);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(30, 25, 290, 29);
        contentPane.add(labelTitulo);
        
        // Subtitulo
        labelSubtitulo.setFont(new Font("Arial", Font.ITALIC, 12));
        labelSubtitulo.setBounds(30, 55, 280, 15);
        contentPane.add(labelSubtitulo);
        
        // Botoes
        dica.setBounds(30, 540, 150, 30);
        dica.setFont(new Font("Arial", Font.PLAIN, 12));
        dica.addActionListener(this);		
        contentPane.add(dica);
        
        sair.setBounds(820, 540, 150, 30);
        sair.setFont(new Font("Arial", Font.PLAIN, 12));
        sair.addActionListener(this);		
        contentPane.add(sair);
        
        dicaLabel.setBounds(190, 540, 150, 30);
        dicaLabel.setFont(new Font("Arial", Font.PLAIN, 12));	
        contentPane.add(dicaLabel);
        
        // Botoes de tiros
        tiroPortaAviao.setBounds(340, 20, 150, 36);
        tiroPortaAviao.setFont(new Font("Arial", Font.BOLD, 12));
        tiroPortaAviao.setBorder(new LineBorder(Color.decode("#d20000"), 2));
        tiroPortaAviao.setForeground(Color.decode("#d20000"));
        tiroPortaAviao.addActionListener(this);
        contentPane.add(tiroPortaAviao);
        
        tiroUnico.setBounds(500, 20, 150, 36);
        tiroUnico.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroUnico.setBorder(new LineBorder(Color.decode("#1c52bb"), 2));
        tiroUnico.setForeground(Color.decode("#1c52bb"));
        tiroUnico.addActionListener(this);		
        contentPane.add(tiroUnico);
        
        tiroDuplo.setBounds(660, 20, 150, 36);
        tiroDuplo.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroDuplo.setBorder(new LineBorder(Color.decode("#019131"), 2));
        tiroDuplo.setForeground(Color.decode("#019131"));
        tiroDuplo.addActionListener(this);		
        contentPane.add(tiroDuplo);
        
        tiroEstrela.setBounds(820, 20, 150, 36);
        tiroEstrela.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroEstrela.setBorder(new LineBorder(Color.decode("#962879"), 2));
        tiroEstrela.setForeground(Color.decode("#962879"));
        tiroEstrela.addActionListener(this);		
        contentPane.add(tiroEstrela);
        
        // Adiciona os botoes de tiro em um array
        botoesTiro[0] = tiroPortaAviao;
        botoesTiro[1] = tiroUnico;
        botoesTiro[2] = tiroDuplo;
        botoesTiro[3] = tiroEstrela;
        
        // Descricao dos botoes
        labelPortaAviao.setFont(new Font("Arial", Font.ITALIC, 11));
        labelPortaAviao.setForeground(Color.decode("#d20000"));
        labelPortaAviao.setBounds(340, 60, 280, 15);
        contentPane.add(labelPortaAviao);
        
        labelUnico.setFont(new Font("Arial", Font.ITALIC, 11));
        labelUnico.setForeground(Color.decode("#1c52bb"));
        labelUnico.setBounds(500, 60, 280, 15);
        contentPane.add(labelUnico);
        
        labelDuplo.setFont(new Font("Arial", Font.ITALIC, 11));
        labelDuplo.setForeground(Color.decode("#019131"));
        labelDuplo.setBounds(660, 60, 280, 15);
        contentPane.add(labelDuplo);
        
        labelEstrela.setFont(new Font("Arial", Font.ITALIC, 11));
        labelEstrela.setForeground(Color.decode("#962879"));
        labelEstrela.setBounds(820, 60, 280, 15);
        contentPane.add(labelEstrela);
        
        // Timer
        labelTimer.setFont(new Font("Arial", Font.BOLD, 18));
        labelTimer.setBounds(475, 550, 280, 15);
        contentPane.add(labelTimer);
        
        // Inicializa o contador
        this.inicializaContador();
        
        // Centralizando a tela
        setLocationRelativeTo(null);
    }

    // Jogo Aleatorio
    public Jogo(String nomeJogador) {
      this(); // Chama o construtor da interface
      setTitle("Batalha Naval em Java ➜ Jogo Aleatorio");
      this.nomeJogador = nomeJogador;
      
      // Instancia e inicializa um novo jogo
      jogo = new Jogar(nomeJogador);
      
      // Inicializa os tabuleiros
      this.tabuleiroJogador = new Tabuleiro(jogo);
      this.tabuleiroComputador = new Tabuleiro(jogo, botoesTiro);
      
      // Referencia os tabuleiros dos oponentes
      this.tabuleiroJogador.oponente(tabuleiroComputador);
      this.tabuleiroComputador.oponente(tabuleiroJogador);
      
      tabuleiroJogador.setBounds(0, 90, 500, 500);
      contentPane.add(tabuleiroJogador);
      
      tabuleiroComputador.setBounds(500, 90, 500, 500);
      contentPane.add(tabuleiroComputador);
      
      // Centralizando a tela
      setLocationRelativeTo(null);
  }
    
    // Jogo definido
    public Jogo(String nomeJogador, int[][] matrizJogador) {
        this(); // Chama o construtor da interface
        setTitle("Batalha Naval em Java ➜ Jogo Definido");

        // Instancia e inicializa um novo jogo definido
        jogo = new Jogar(nomeJogador, matrizJogador);
        
        // Inicializa os tabuleiros
        this.tabuleiroJogador = new Tabuleiro(jogo);
        this.tabuleiroComputador = new Tabuleiro(jogo, botoesTiro);
        
        // Referencia os tabuleiros dos oponentes
        this.tabuleiroJogador.oponente(tabuleiroComputador);
        this.tabuleiroComputador.oponente(tabuleiroJogador);
        
        tabuleiroJogador.setBounds(0, 90, 500, 500);
        contentPane.add(tabuleiroJogador);
        
        tabuleiroComputador.setBounds(500, 90, 500, 500);
        contentPane.add(tabuleiroComputador);
        
        // Centralizando a tela
        setLocationRelativeTo(null);
    }
    
    // Getter e setter dos botoes de tiro
    public int getTiroSelecionado() {
        return tiroSelecionado;
    }

    public void setTiroSelecionado(int tiroSelecionado) {
        this.tiroSelecionado = tiroSelecionado;
    }
    
    // Inicializa o contador na interface
    private void inicializaContador() {
        timer = new Timer(1000, (ActionEvent e) -> {
            contador++;
            
            // Verifica se o jogo acabou
            if (jogo.isJogoFinalizado()) {
                ((Timer) (e.getSource())).stop();
                this.dispose();
            } else {
                labelTimer.setText(String.format("%02d:%02d", contador / 60, contador % 60));
            }
        });
        
        timer.setInitialDelay(0);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Evento para sair do jogo
        if (e.getSource() == sair){
            Object[] options = {"Reiniciar jogo", "Novo jogo"};
           
            int result = JOptionPane.showOptionDialog(null,"O que deseja fazer?", "Sair do jogo",
            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            
            switch (result) {
                    case 0:
                        // Reinicar jogo
                        System.out.println("Reiniciar jogo");
                        SwingUtilities.invokeLater(() -> {
                            this.dispose();
                            
                            Jogo aleatorio = new Jogo(this.nomeJogador);
                            aleatorio.setVisible(true);
                        });
                        
                        break;
                    case 1:
                        // Sair do jogo
                        SwingUtilities.invokeLater(() -> {
                            this.dispose();

                            TelaInicio inicio = new TelaInicio();
                            inicio.setVisible(true);
                        });
                        break;
            }
        }
        
        // Evento de selecao do tiro
        for (int i = 0; i < botoesTiro.length; i++){
            if (e.getSource() == botoesTiro[i]) {
                setTiroSelecionado(i);
                this.tabuleiroComputador.tipoTiro(tiroSelecionado); 
                botoesTiro[i].setFont(new Font("Arial", Font.BOLD, 12));
                
            } else {
                botoesTiro[i].setFont(new Font("Arial", Font.PLAIN, 12));
            }
        }
        
        // Evento de dicas
        if (e.getSource() == dica){
            this.qntdDicas--;
            dicaLabel.setText(this.qntdDicas + " restantes");
            
            JOptionPane.showMessageDialog(null, "Dica: " + jogo.getDica());
            
            if(this.qntdDicas == 0) {
                dica.setEnabled(false);
            }
        }
        
    }
}
