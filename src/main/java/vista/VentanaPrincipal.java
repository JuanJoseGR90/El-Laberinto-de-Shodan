package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import controlador.EventosControl;
import controlador.ControlJuego;
import modelo.Jugador;
import modelo.IAJugador;

/**
 * Ventana principal que integra la vista, el panel de controles y el control del juego.
 */
public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Jugador jugador, IAJugador iaJugador, VistaLaberinto vistaLaberinto, PanelControles panelControles, ControlJuego controlJuego) {
        super("Juego del Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Agrega la vista gráfica del laberinto y el panel de controles.
        add(vistaLaberinto, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);

        // Registrar el KeyListener y asignarle el controlJuego
        EventosControl ec = new EventosControl(jugador);
        ec.setControlJuego(controlJuego);
        addKeyListener(ec);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
