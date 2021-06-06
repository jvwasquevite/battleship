package gui;

import java.awt.Color;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirJogo extends JFrame implements ActionListener {
    
    // Instanciando o painel da tela Definir Jogo
    private JPanel contentPane = new JPanel();
    
    // Labels
    private JLabel labelTitulo = new JLabel("Defina o seu Jogo");
    private JLabel labelSubtitulo = new JLabel("Insira as embarcações em seu tabuleiro.");
    
    // Botoes
    private JButton iniciarJogo = new JButton("Iniciar Jogo");
    private JButton voltar = new JButton("Voltar");
    
    // Botoes das embarcacoes
    private JButton portaAviao = new JButton();
    private JButton submarino = new JButton();
    private JButton navioEscolta = new JButton();
    private JButton aviaoCaca = new JButton();
    
    
    public DefinirJogo() {
        setTitle("Batalha Naval em Java ➜ Definir Jogo");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Estilização do painel
        setBounds(100, 100, 1000, 600);
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
        iniciarJogo.setBounds(820, 30, 150, 36);
        iniciarJogo.setFont(new Font("Arial", Font.PLAIN, 12));
        iniciarJogo.setEnabled(false);
        iniciarJogo.addActionListener(this);		
        contentPane.add(iniciarJogo);
        
        // Botão de voltar
        voltar.setBounds(660, 30, 150, 36);
        voltar.setFont(new Font("Arial", Font.PLAIN, 12));
        voltar.addActionListener(this);		
        contentPane.add(voltar);
        
        // Botões de selecao das embarcacoes
        portaAviao.setBounds(770, 180, 200, 50);
        portaAviao.setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/portaaviao.png")));
        portaAviao.setBackground(Color.cyan);
        portaAviao.addActionListener(this);
        contentPane.add(portaAviao);
        
        submarino.setBounds(770, 240, 100, 50);
        submarino.setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/submarino.png")));
        submarino.setBackground(Color.cyan);
        submarino.addActionListener(this);
        contentPane.add(submarino);
        
        navioEscolta.setBounds(770, 300, 150, 50);
        navioEscolta.setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/navioescolta.png")));
        navioEscolta.setBackground(Color.cyan);
        navioEscolta.addActionListener(this);
        contentPane.add(navioEscolta);
        
        aviaoCaca.setBounds(770, 360, 100, 50);
        aviaoCaca.setIcon(new ImageIcon(DefinirJogo.class.getResource("/gui/imagens/aviaocaca.png")));
        aviaoCaca.setBackground(Color.cyan);
        aviaoCaca.addActionListener(this);
        contentPane.add(aviaoCaca);
        
        // Centralizando a tela
        setLocationRelativeTo(null);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // Evento para voltar
        if (e.getSource() == voltar){
            SwingUtilities.invokeLater(() -> {
                this.dispose();
                
                TelaInicio inicio = new TelaInicio();
                inicio.setVisible(true);
            });
        }
    }
}
