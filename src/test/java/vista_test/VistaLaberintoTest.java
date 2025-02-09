package vista_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import vista.VistaLaberinto;
import java.awt.Dimension;

public class VistaLaberintoTest {

    @Test
    public void testPreferredSize() {
        Laberinto laberinto = new Laberinto(5, 5);
        Jugador jugador = new Jugador(new Posicion(0, 0));
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));
        VistaLaberinto vista = new VistaLaberinto(laberinto, jugador, iaJugador);
        Dimension prefSize = vista.getPreferredSize();
        assertEquals(5 * 30, prefSize.width);
        assertEquals(5 * 30, prefSize.height);
    }
}
