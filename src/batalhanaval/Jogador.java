package batalhanaval;
import java.io.Serializable;

// Uso do Seriazible para manter os jogadores em um arquivo
// Uso do comparable para ordernar os jogadores pelo tempo

public class Jogador implements Serializable, Comparable<Jogador> {
    private String nome;
    private int tempo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    // Gerando uma string com o nome e o tempo
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Nome: ").append(this.nome).append(" Tempo: ").append(this.tempo);
        return str.toString();
    }
    
    // Ordenando os melhores tempos
    @Override
    public int compareTo(Jogador jog) {
        int compararTempo = ((Jogador) jog).getTempo();		
        return compararTempo - this.tempo;
    } 
}
