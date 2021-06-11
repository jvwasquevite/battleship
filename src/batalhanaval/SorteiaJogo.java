package batalhanaval;
import java.util.ArrayList;
import java.util.Random;

// Cria uma matriz de inteiros
// Recebe um array de Embarcacoes
// Distribui as 4 Embarcacoes aleatoriamente
// Retorna a matriz final de inteiros

public class SorteiaJogo {
    private final Random sorteio = new Random();
    private final int[][] matriz;
    private boolean cabe;
    
    private int linha;
    private int coluna;
    
    public SorteiaJogo() {
        this.matriz = new int[10][10];
        this.linha = sorteio.nextInt(10);
        this.coluna = sorteio.nextInt(10);
        
        // Inicializa o tabuleiro com zeros
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                matriz[i][j] = 0;
            }
        }
    }
    
    public int[][] sortear(ArrayList<Embarcacao> embarcacoes) {
        // Percorre o ArrayList de embarcacoes
        for(int cont = 0; cont < embarcacoes.size(); cont++){
            cabe = cabeEmbarcacao(linha, coluna, embarcacoes.get(cont).getTamanhoEmbarcacao(), matriz);

            // Enquanto o navio nao couber no tabuleiro, sorteia outra posicao
            if(cabe == false){
                while(cabe == false){
                    linha = sorteio.nextInt(10);
                    coluna = sorteio.nextInt(10);
                    
                    cabe = cabeEmbarcacao(linha, coluna, embarcacoes.get(cont).getTamanhoEmbarcacao(), matriz);
                }
            }
            
            // Percorre o tamanho da embarcacao e preenche no tabuleiro
            for (int i = 0; i < embarcacoes.get(cont).getTamanhoEmbarcacao(); i++){
                switch(embarcacoes.get(cont).getNomeEmbarcacao()) {
                    case "Porta Avião":
                        matriz[linha][coluna] = 1;
                        break;
                    case "Submarino":
                        matriz[linha][coluna] = 2;
                        break;
                    case "Navio Escolta":
                        matriz[linha][coluna] = 3;
                        break;
                    case "Avião Caça":
                        matriz[linha][coluna] = 4;
                        break;
                }

                coluna++;
            }
            
            // Sorteia nova posicao para a proxima embarcacao
            linha = sorteio.nextInt(10);
            coluna = sorteio.nextInt(10);
        }
        
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        
        return matriz;
    }
    
    private boolean cabeEmbarcacao(int linha, int coluna, int tamanho, int matriz[][]){
        if (coluna > (10 - tamanho)){
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
