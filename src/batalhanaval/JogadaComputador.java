package batalhanaval;

import java.util.Random;

public class JogadaComputador {
    private final Random sorteio = new Random();
    private final int[][] matrizOponente;
    
    private int linha;
    private int coluna;
    
    public JogadaComputador(int[][] matriz) {
        this.matrizOponente = matriz;
        
        this.linha = sorteio.nextInt(10);
        this.coluna = sorteio.nextInt(10);
        
        switch (matrizOponente[linha][coluna]) {
            case 0:
                
                break;
        }
    }
}
