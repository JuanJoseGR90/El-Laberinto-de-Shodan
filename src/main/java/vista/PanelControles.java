package vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.FlowLayout;
import controlador.ControlJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel de controles que muestra botones y la información del juego (temporizador, movimientos y puntuación).
 */
public class PanelControles extends JPanel {
    private JButton btnIniciar;
    private JButton btnReiniciar;
    private ControlJuego controlJuego;
    private JLabel lblTimer;
    private JLabel lblMoves;
    private JLabel lblScore;
    private Timer timer;

    public PanelControles(ControlJuego controlJuego) {
        this.controlJuego = controlJuego;
        setLayout(new FlowLayout());
        btnIniciar = new JButton("Iniciar");
        btnReiniciar = new JButton("Reiniciar");
        lblTimer = new JLabel("Tiempo: 0s");
        lblMoves = new JLabel("Movimientos: 0");
        lblScore = new JLabel("Puntuación: 0");

        add(btnIniciar);
        add(btnReiniciar);
        add(lblTimer);
        add(lblMoves);
        add(lblScore);

        btnIniciar.addActionListener(e -> {
            new Thread(() -> controlJuego.iniciarJuego()).start();
            timer.start();
        });

        btnReiniciar.addActionListener(e -> controlJuego.reiniciarJuego());

        // Timer para actualizar la información cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = (System.currentTimeMillis() - controlJuego.getStartTime()) / 1000;
                lblTimer.setText("Tiempo: " + elapsed + "s");
                lblMoves.setText("Movimientos: " + controlJuego.getMoveCount());
                lblScore.setText("Puntuación: " + controlJuego.getScore());
            }
        });
    }
}
