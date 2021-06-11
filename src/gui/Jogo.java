package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import batalhanaval.Jogar;

public class Jogo extends JFrame implements ActionListener {
    // Instanciando o painel do jogo
    private JPanel contentPane = new JPanel();
    
    // Instanciando os tabuleiros
    Jogar jogo = new Jogar();
    
    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroComputador;
    
    // Labels
    private JLabel labelTitulo = new JLabel("Bem vindo ao Jogo");
    private JLabel labelSubtitulo = new JLabel("Destrua as 4 embarcações do oponente.");
    private JLabel labelTimer = new JLabel("00:00");
    
    // Botoes
    private JButton dica = new JButton("Solicitar Dica");
    private JButton sair = new JButton("Sair do Jogo");
    
    // Botoes das embarcacoes
    private JButton tiroPortaAviao = new JButton("Tiro porta-avião");
    private JButton tiroUnico = new JButton("Tiro unico");
    private JButton tiroDuplo = new JButton("Tiro duplo");
    private JButton tiroEstrela = new JButton("Tiro estrela");
    
    // Labels dos botoes
    private JLabel labelPortaAviao = new JLabel("1 tiro a cada 2 rodadas");
    private JLabel labelUnico = new JLabel("Submarino");
    private JLabel labelDuplo = new JLabel("Navio escolta");
    private JLabel labelEstrela = new JLabel("Avião de caça");
    
    public Jogo() {
        setTitle("Batalha Naval em Java ➜ Jogo");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Estilização do painel
        setBounds(100, 100, 1000, 630);
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        
        // Insere o painel no frame
        setContentPane(contentPane);
        
        // Titulo
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
        
        // Botoes de tiros
        tiroPortaAviao.setBounds(340, 20, 150, 36);
        tiroPortaAviao.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroPortaAviao.addActionListener(this);		
        contentPane.add(tiroPortaAviao);
        
        tiroUnico.setBounds(500, 20, 150, 36);
        tiroUnico.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroUnico.addActionListener(this);		
        contentPane.add(tiroUnico);
        
        tiroDuplo.setBounds(660, 20, 150, 36);
        tiroDuplo.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroDuplo.addActionListener(this);		
        contentPane.add(tiroDuplo);
        
        tiroEstrela.setBounds(820, 20, 150, 36);
        tiroEstrela.setFont(new Font("Arial", Font.PLAIN, 12));
        tiroEstrela.addActionListener(this);		
        contentPane.add(tiroEstrela);
        
        // Descricao dos botoes
        labelPortaAviao.setFont(new Font("Arial", Font.ITALIC, 11));
        labelPortaAviao.setBounds(340, 60, 280, 15);
        contentPane.add(labelPortaAviao);
        
        labelUnico.setFont(new Font("Arial", Font.ITALIC, 11));
        labelUnico.setBounds(500, 60, 280, 15);
        contentPane.add(labelUnico);
        
        labelDuplo.setFont(new Font("Arial", Font.ITALIC, 11));
        labelDuplo.setBounds(660, 60, 280, 15);
        contentPane.add(labelDuplo);
        
        labelEstrela.setFont(new Font("Arial", Font.ITALIC, 11));
        labelEstrela.setBounds(820, 60, 280, 15);
        contentPane.add(labelEstrela);
        
        // Timer
        labelTimer.setFont(new Font("Arial", Font.BOLD, 18));
        labelTimer.setBounds(475, 550, 280, 15);
        contentPane.add(labelTimer);
        
        // Insere os tabuleiros
        this.tabuleiroJogador = new Tabuleiro(jogo.getTabuleiroJogador());
        this.tabuleiroComputador = new Tabuleiro(jogo.getTabuleiroComputador());
        
        tabuleiroJogador.setBounds(0, 90, 500, 500);
        contentPane.add(tabuleiroJogador);
        
        tabuleiroComputador.setBounds(500, 90, 500, 500);
        contentPane.add(tabuleiroComputador);
        
        // Centralizando a tela
        setLocationRelativeTo(null);
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
                        // Reiniar jogo
                        System.out.println("Reiniciar jogo");
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
    }
}
