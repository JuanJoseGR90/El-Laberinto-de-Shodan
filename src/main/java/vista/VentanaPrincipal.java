package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import controlador.EventosControl;
import modelo.Jugador;
import modelo.IAJugador;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Jugador jugador, IAJugador iaJugador, VistaLaberinto vistaLaberinto, PanelControles panelControles) {
        super("Juego del Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Agrega la vista gráfica del laberinto
        add(vistaLaberinto, BorderLayout.CENTER);
        // Agrega el panel de controles en la parte inferior
        add(panelControles, BorderLayout.SOUTH);

        // Se registra el KeyListener para controlar el movimiento del jugador
        addKeyListener(new EventosControl(jugador));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
