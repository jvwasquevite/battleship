package batalhanaval;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Armazena Jogadores em um ArrayList
// Insere jogadores no arquivo

public class InsereJogador {
    private static InsereJogador instancia;
    private final ArrayList<Jogador> jogadores = new ArrayList<>();
    
    public InsereJogador() {};
    
    // Permite que o jogador seja instanciado apenas uma vez
    // E realize diversos jogos em sequencia
    public static synchronized InsereJogador getInstance(){
        if (instancia == null) {
            instancia = new InsereJogador();
        }
        
        return instancia;
    }
    
    // Armazena o jogador no ArrayList
    public void adicionaJogador(Jogador jogador){
        jogadores.add(jogador);
    }
    
    // Armazena o ArrayList de jogadores no arquivo
    public void insereNoArquivo() {
        // Array temporario guarda os jogadores atuais
        ArrayList<Jogador> arquivo = getJogadores();
        
        // Se ja tiver algo no arquivo, concatena com os novos jogadores
        if (arquivo != null && !arquivo.isEmpty()){
            try {
                FileOutputStream file = new FileOutputStream("ranking.txt");
                ObjectOutputStream stream = new ObjectOutputStream(file);
                
                // Concatena as listas de jogadores
                jogadores.addAll(arquivo);
                stream.writeObject(jogadores);
                stream.close();
                file.close();
            } catch (IOException e){
                System.out.println("Erro ao acessar o arquivo: " + e);
            }
        }
        
        // Se o arquivo estiver vazio
        try {
            FileOutputStream file = new FileOutputStream("ranking.txt");
            ObjectOutputStream stream = new ObjectOutputStream(file);
            
            stream.writeObject(jogadores);
            stream.close();
            file.close();
        } catch (IOException e){
            System.out.println("Erro ao acessar o arquivo: " + e);
        }
    }
    
    // Retorna a lista de jogadores contida no arquivo
    public ArrayList<Jogador> getJogadores(){
        ArrayList<Jogador> lista = null;
        
        try {
                FileInputStream file = new FileInputStream("ranking.txt");
                ObjectInputStream in = new ObjectInputStream(file);
                
                lista = (ArrayList<Jogador>) in.readObject();
                in.close();
                file.close();
                
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Erro ao acessar os jogadores: " + e);
        }
        
        return lista;
    }
    
}
