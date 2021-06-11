package batalhanaval;
import java.util.ArrayList;

public class DefineJogo {
    private final int[][] matriz;
    private boolean cabe;
    
    public DefineJogo() {
        this.matriz = new int[10][10];
    }
    
    public int[][] definir(int linha, int coluna, int tipoEmbarcacao, ArrayList<Embarcacao> embarcacoes) {
        // Inicializa o tabuleiro com zeros
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){ 
                    this.matriz[i][j] = 0;
            }
        }
        
        // Percorre o ArrayList de embarcacoes
        for(int cont = 0; cont < embarcacoes.size(); cont++){
            cabe = cabeEmbarcacao(linha, coluna, embarcacoes.get(cont).getTamanhoEmbarcacao(), matriz);

            if(cabe == false){
                // se for falso
            }
            
            // Percorre o tamanho da embarcacao e preenche no tabuleiro
            for (int i = 0; i < embarcacoes.get(cont).getTamanhoEmbarcacao(); i++){
                if("Porta Avião".equals(embarcacoes.get(cont).getNomeEmbarcacao())) {
                    matriz[linha][coluna] = 1;
                }
                if("Submarino".equals(embarcacoes.get(cont).getNomeEmbarcacao())) {
                    matriz[linha][coluna] = 2;
                }
                if("Navio Escolta".equals(embarcacoes.get(cont).getNomeEmbarcacao())) {
                    matriz[linha][coluna] = 3;
                }
                if("Avião Caça".equals(embarcacoes.get(cont).getNomeEmbarcacao())) {
                    matriz[linha][coluna] = 4;
                }

                coluna++;
            }
        }
        
        return matriz;
    }
    
    private boolean cabeEmbarcacao(int linha, int coluna, int tamanho, int matriz[][]){
        if (coluna > (9 - tamanho)){
            return false;
        }
        
        for (int col = 0; col < tamanho; col++){
            if (matriz[linha][coluna] == 0){
                coluna++;
            }
            else {
                return false;
            }
        }
        
        return true;
    }
    
}
