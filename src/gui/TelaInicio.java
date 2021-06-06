package gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicio extends JFrame implements ActionListener {   
    
    // Instanciando o painel inicial
    private JPanel contentPane = new JPanel();
    
    // Campo de texto que insere o nome do johador
    private JTextField caixaDeTexto = new JTextField(20);
    
    // Botoes
    private JButton jogoAleatorio = new JButton("Jogo Aleatório");
    private JButton jogoDefinido = new JButton("Definir Jogo");
    
    // Labels
    private JLabel labelTitulo = new JLabel("Batalha Naval: POO 2020/2");
    private JLabel labelSubtitulo = new JLabel("Criado por Diulia Deon e João Wasquevite");
    private JLabel labelNome = new JLabel("Qual o seu nome?");
    
    // Construtor
    public TelaInicio() {
        setTitle("Batalha Naval em Java");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Estilização do painel
        setBounds(100, 100, 500, 350);
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        
        // Insere o painel no frame
        setContentPane(contentPane);
        
        // Campo de texto com o nome do jogador
        caixaDeTexto.setBounds(133, 141, 223, 29);		
        contentPane.add(caixaDeTexto);
        
        // Titulo do Jogo
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(110, 20, 290, 29);
        contentPane.add(labelTitulo);
        
        // Subtitulo do Jogo
        labelSubtitulo.setFont(new Font("Arial", Font.ITALIC, 12));
        labelSubtitulo.setBounds(115, 50, 280, 15);
        contentPane.add(labelSubtitulo);
        
        // Nome do Jogador
        labelNome.setFont(new Font("Arial", Font.PLAIN, 14));
        labelNome.setBounds(135, 116, 150, 14);
        contentPane.add(labelNome);
        
        // Botão de Jogo Aleatório
        jogoAleatorio.setBounds(90, 238, 150, 36);
        jogoAleatorio.setFont(new Font("Arial", Font.PLAIN, 12));		
        jogoAleatorio.addActionListener(this);		
        contentPane.add(jogoAleatorio);
        
        // Botão de Jogo Definido
        jogoDefinido.setBounds(250, 238, 150, 36);
        jogoDefinido.setFont(new Font("Arial", Font.PLAIN, 12));	
        jogoDefinido.addActionListener(this);		
        contentPane.add(jogoDefinido);
        
        // Centralizando a tela
        setLocationRelativeTo(null);
        
    }
    
    // Configurando as actions dos botoes
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Evento de Jogo Aleatório
        if (e.getSource() == jogoAleatorio){
            String nome = caixaDeTexto.getText();
            
            // Verificacao de nome vazio
            if (nome.equals("")){
                JOptionPane.showMessageDialog(null, "Digite um nome válido");
            } else if (!(nome.equals(""))){
                // Iniciando um Jogo Aleatório
                SwingUtilities.invokeLater(() -> {						
                    Jogo aleatorio = new Jogo();
                    aleatorio.setVisible(true);
                });
                
                caixaDeTexto.setText("");
                this.dispose();
            }
            
        }
        
        // Evento de Jogo Definido
        if (e.getSource() == jogoDefinido){
            String nome = caixaDeTexto.getText();
            
            // Verificacao de nome vazio
            if (nome.equals("")){
                JOptionPane.showMessageDialog(null, "Digite um nome válido");
            } else if (!(nome.equals(""))){
                // Iniciando um Jogo Definido
                SwingUtilities.invokeLater(() -> {						
                    DefinirJogo definir = new DefinirJogo();
                    definir.setVisible(true);
                });
                
                caixaDeTexto.setText("");
                this.dispose();
            }
            
        }
    }
    
}
