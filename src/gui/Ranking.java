package gui;

import batalhanaval.InsereJogador;
import batalhanaval.Jogador;
import batalhanaval.ListaRankingException;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking extends JFrame implements ActionListener {
    // Instanciando o painel inicial
    private JPanel contentPane = new JPanel();
    
    // Labels
    private JLabel labelTitulo = new JLabel("Ranking de Jogadores");
    private JLabel labelSubtitulo = new JLabel("Os melhores jogadores por tempo");
    
    private final TextArea textArea = new TextArea("", 3, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
    
    private final JButton novoJogo = new JButton("Voltar para a tela inicial");
    private final JButton resetar = new JButton("Resetar ranking");
    
    public Ranking() {
        // Configura o nome da Janela
        setTitle("Ranking de Jogadores");	
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
         // Estilizacao do painel
        setBounds(100, 100, 507, 409);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        // Voltar
        novoJogo.setFont(new Font("Arial", Font.PLAIN, 12));
        novoJogo.setBounds(10, 330, 235, 30);
        novoJogo.addActionListener(this);
        contentPane.add(novoJogo);
        
        // Resetar ranking
        resetar.setFont(new Font("Arial", Font.PLAIN, 12));
        resetar.setBounds(255, 330, 235, 30);
        resetar.addActionListener(this);
        contentPane.add(resetar);

        // Titulo
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBounds(30, 20, 290, 29);
        contentPane.add(labelTitulo);
        
        // Subtitulo
        labelSubtitulo.setFont(new Font("Arial", Font.ITALIC, 12));
        labelSubtitulo.setBounds(30, 50, 280, 15);
        contentPane.add(labelSubtitulo);	

        // Area de texto
        textArea.setBounds(10, 80, 480, 240);
        contentPane.add(textArea);
        
        // Cria uma array de jogadores
        ArrayList<Jogador> jogadores = new InsereJogador().getJogadores();
        
        try {
            verificaLista(jogadores);
            
            // Ordena os jogadores com o metodo compareTo() em Jogador
            Collections.sort(jogadores, Collections.reverseOrder());
            
            StringBuilder ranking = new StringBuilder();
            
            jogadores.forEach((jog) -> {
                String tempoFormatado = String.format("%02d:%02d", jog.getTempo() / 60, jog.getTempo() % 60);
                ranking.append(jog.getNome()).append(" ganhou a partida em ").append(tempoFormatado).append("\n");
            });
            
            String rankingFinal = ranking.toString(); 
            textArea.setText(rankingFinal);	
            
        } catch (ListaRankingException e){
                    System.out.println(e.getMessage());
        }	

        
        // Centralizando na tela
        setLocationRelativeTo(null);
    }
    
    // Verifica se a lista esta vazia
    private void verificaLista(ArrayList<Jogador> jogadores) throws ListaRankingException{
        if (jogadores == null || jogadores.isEmpty()){
                throw new ListaRankingException("Arquivo vazio.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Volta para a tela inicial
        if (e.getSource() == novoJogo){
            SwingUtilities.invokeLater(() -> {
                this.dispose();
                
                TelaInicio inicio = new TelaInicio();
                inicio.setVisible(true);
            });
        }
        
        if (e.getSource() == resetar){
            SwingUtilities.invokeLater(() -> {
                // Esvazia arquivo de ranking
                try {
                    FileWriter out = new FileWriter("ranking.txt");
                    out.write("");
                    out.flush();
                    out.close();
                } catch (IOException erro) {
                    System.out.println(erro);
                }
                
                Ranking.this.dispose();
                
                TelaInicio inicio = new TelaInicio();
                inicio.setVisible(true);
            });
        }
    }
    
}
