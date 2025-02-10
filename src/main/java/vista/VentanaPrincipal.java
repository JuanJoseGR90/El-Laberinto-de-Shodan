package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import controlador.EventosControl;
import modelo.Jugador;
import modelo.IAJugador;

/**
 * Ventana principal que integra la vista y el panel de controles.
 */
public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Jugador jugador, IAJugador iaJugador, VistaLaberinto vistaLaberinto, PanelControles panelControles) {
        super("Juego del Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(vistaLaberinto, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);
        // Registrar el listener para eventos de teclado.
        addKeyListener(new EventosControl(jugador));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
