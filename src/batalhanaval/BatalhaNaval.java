package batalhanaval;

import javax.swing.SwingUtilities;
import gui.TelaInicio;

public class BatalhaNaval {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicio telaInicio = new TelaInicio();
            telaInicio.setVisible(true);
        });
    }
}
