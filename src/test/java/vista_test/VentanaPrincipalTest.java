package vista_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Laberinto;
import modelo.Posicion;
import vista.VentanaPrincipal;
import vista.VistaLaberinto;
import vista.PanelControles;
import java.awt.BorderLayout;

public class VentanaPrincipalTest {

    @Test
    public void testComponentesEnVentanaPrincipal() {
        Laberinto laberinto = new Laberinto(3, 3);
        Jugador jugador = new Jugador(new Posicion(0, 0));
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));
        VistaLaberinto vista = new VistaLaberinto(laberinto, jugador, iaJugador);
        PanelControles panel = new PanelControles(null); // Se puede pasar un control dummy
        VentanaPrincipal ventana = new VentanaPrincipal(jugador, iaJugador, vista, panel);

        assertInstanceOf(BorderLayout.class, ventana.getContentPane().getLayout(), "El layout debe ser BorderLayout");
        assertNotNull(vista, "La vista no debe ser null");
        assertNotNull(panel, "El panel de controles no debe ser null");
    }
}
