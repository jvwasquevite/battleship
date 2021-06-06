package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DefinirJogo extends JFrame implements ActionListener {
    
    // Instanciando o painel da tela Definir Jogo
    private JPanel contentPane = new JPanel();
    
    // Labels
    private JLabel labelTitulo = new JLabel("Defina o seu Jogo");
    private JLabel labelSubtitulo = new JLabel("Insira as embarcações em sua matriz.");
    
    // Botoes
    private JButton iniciarJogo = new JButton("Iniciar Jogo");
    
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
        
        // Centralizando a tela
        setLocationRelativeTo(null);
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
