package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import controlador.ControlJuego;

/**
 * Panel de controles con botones para iniciar y reiniciar el juego.
 */
public class PanelControles extends JPanel {
    private JButton btnIniciar;
    private JButton btnReiniciar;
    private ControlJuego controlJuego;

    public PanelControles(ControlJuego controlJuego) {
        this.controlJuego = controlJuego;
        setLayout(new FlowLayout());
        btnIniciar = new JButton("Iniciar");
        btnReiniciar = new JButton("Reiniciar");
        add(btnIniciar);
        add(btnReiniciar);

        btnIniciar.addActionListener(e -> {
            new Thread(() -> controlJuego.iniciarJuego()).start();
        });

        btnReiniciar.addActionListener(e -> controlJuego.reiniciarJuego());
    }
}
