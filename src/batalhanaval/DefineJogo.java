package batalhanaval;

// Cria uma matriz de inteiros
// Recebe as posicoes + tipoEmbarcacao
// Posiciona a embarcacao na matriz
// Retorna a matriz final de inteiros

public class DefineJogo {
    private final int[][] matriz;
    private boolean cabe;
    
    public DefineJogo() {
        this.matriz = new int[10][10];
        
        // Inicializa o tabuleiro com zeros
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){ 
                    this.matriz[i][j] = 0;
            }
        }
    }
    
    // Determina o tamanho da embarcacao
    private int getTamanho(int tipoEmbarcacao) {
        switch (tipoEmbarcacao) {
                case 1: // Porta aviao
                    return 4;
                case 2: // Submarino
                    return 2;
                case 3: // Navio escolta
                    return 3;
                case 4: // Aviao caca
                    return 2;
            }
        return 0;
    }
    
    // Posiciona a embarcacao na matriz
    public boolean posicionar(int linha, int coluna, int tipoEmbarcacao) {
            cabe = cabeEmbarcacao(linha, coluna, getTamanho(tipoEmbarcacao), matriz);

            if(cabe == false){
                return false;
            }
            
            // Percorre o tamanho da embarcacao e preenche no tabuleiro
            for (int i = 0; i < getTamanho(tipoEmbarcacao); i++){
                switch(tipoEmbarcacao) {
                    case 1:
                        matriz[linha][coluna] = 1;
                        break;
                    case 2:
                        matriz[linha][coluna] = 2;
                        break;
                    case 3:
                        matriz[linha][coluna] = 3;
                        break;
                    case 4:
                        matriz[linha][coluna] = 4;
                        break;
                }
                
                coluna++;
            }
            
        return true;
    }
    
    public int[][] getMatriz() {
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
