package vista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import controlador.EventosControl;
import modelo.Jugador;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(Jugador jugador, VistaLaberinto vistaLaberinto) {
        super("Juego del Laberinto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(vistaLaberinto, BorderLayout.CENTER);
        // Se puede agregar un panel de controles en otra región, por ejemplo:
        // add(new PanelControles(), BorderLayout.SOUTH);

        // Registrar el controlador de eventos de teclado
        addKeyListener(new EventosControl(jugador));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
