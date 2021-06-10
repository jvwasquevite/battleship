package batalhanaval;

import java.util.ArrayList;
import java.util.Random;

public class Jogar {
    // Sorteia uma posicao de 0 a 9 no tabuleiro
    private final Random sorteio = new Random();
    int linha = sorteio.nextInt(10);
    int coluna = sorteio.nextInt(10);
    boolean cabe = false;
    
    ArrayList<Embarcacao> embarcacoes = new ArrayList();
    
    public void inicializarJogo(int[][] matriz) {
        // Armazena as embarcacoes em um ArrayList
        embarcacoes.add(new PortaAviao());
        embarcacoes.add(new Submarino());
        embarcacoes.add(new NavioEscolta());
        embarcacoes.add(new AviaoCaca());
        
        System.out.println(embarcacoes);
        
        // Inicializa a matriz com zeros
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){ 
                    matriz[i][j] = 0;
            }
        }
        
        // Percorre o ArrayList de embarcacoes
        for(int cont = 0; cont < embarcacoes.size(); cont++){
            cabe = cabeEmbarcacao(linha, coluna, embarcacoes.get(cont).getTamanhoEmbarcacao(), matriz);

            // Enquanto o navio nao couber na matriz, sorteia outra posicao
            if(cabe == false){
                while(cabe == false){
                    linha = sorteio.nextInt(10);
                    coluna = sorteio.nextInt(10);
                    
                    cabe = cabeEmbarcacao(linha, coluna, embarcacoes.get(cont).getTamanhoEmbarcacao(), matriz);
                }
            }
            
            // Percorre o tamanho da embarcação e preenche
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
            
            // Sorteia nova posição para a próxima embarcação
            linha = sorteio.nextInt(10);
            coluna = sorteio.nextInt(10);
            
        }
        
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    public boolean cabeEmbarcacao(int linha, int coluna, int tamanho, int matriz[][]){
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

    public Embarcacao getEmbarcacao(String nome) {
        // Percorre o ArrayList de embarcacoes
        for(int cont = 0; cont < embarcacoes.size(); cont++){
            if(nome.equals(embarcacoes.get(cont).getNomeEmbarcacao())) {
                return embarcacoes.get(cont);
            }
        }
        
        return null;
    }
    
    
}
