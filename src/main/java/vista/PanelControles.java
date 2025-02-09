package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class PanelControles extends JPanel {
    public PanelControles() {
        setLayout(new FlowLayout());
        JButton btnIniciar = new JButton("Iniciar");
        JButton btnReiniciar = new JButton("Reiniciar");
        add(btnIniciar);
        add(btnReiniciar);

        // Agrega ActionListeners para manejar eventos según sea necesario
    }
}
