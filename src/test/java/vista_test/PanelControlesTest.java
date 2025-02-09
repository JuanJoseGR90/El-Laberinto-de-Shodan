package vista_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import vista.VistaLaberinto;
import controlador.ControlJuego;
import vista.PanelControles;
import javax.swing.JButton;
import java.awt.Component;

public class PanelControlesTest {

    @Test
    public void testBotonesExistentes() {
        // Se crean objetos mínimos válidos para evitar null
        Laberinto lab = new Laberinto(1, 1);
        Jugador jug = new Jugador(new Posicion(0, 0));
        IAJugador iaJug = new IAJugador(new Posicion(0, 0));
        // Se crea la vista, que es necesaria para el ControlJuego
        VistaLaberinto vista = new VistaLaberinto(lab, jug, iaJug);
        // Se crea el controlador con objetos válidos
        ControlJuego control = new ControlJuego(lab, jug, iaJug, vista);
        // Ahora se pasa el controlador al panel de controles
        PanelControles panel = new PanelControles(control);

        int countButtons = 0;
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                countButtons++;
            }
        }
        assertEquals(2, countButtons, "El panel debe contener 2 botones (Iniciar y Reiniciar)");
    }
}
