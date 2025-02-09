package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import controlador.ControlJuego;

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
            // Se inicia el juego en un hilo aparte para no bloquear la interfaz gráfica
            new Thread(() -> {
                controlJuego.iniciarJuego();
            }).start();
        });

        btnReiniciar.addActionListener(e -> {
            // Se asume que en el controlador se implementa el método reiniciarJuego()
            controlJuego.reiniciarJuego();
        });
    }
}
