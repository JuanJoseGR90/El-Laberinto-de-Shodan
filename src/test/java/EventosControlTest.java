import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controlador.EventosControl;
import controlador.ControlJuego;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Posicion;

// Clase dummy para capturar el movimiento
class DummyControlJuego extends ControlJuego {
    public int lastDeltaX = 0;
    public int lastDeltaY = 0;

    public DummyControlJuego(Laberinto laberinto, Jugador jugador) {
        // Se pasa null para IA y vista, ya que no se usarán en el test.
        super(laberinto, jugador, null, null);
    }

    @Override
    public void procesarMovimiento(int deltaX, int deltaY) {
        lastDeltaX = deltaX;
        lastDeltaY = deltaY;
    }
}

public class EventosControlTest {
    private Jugador jugador;
    private Laberinto laberinto;
    private DummyControlJuego dummyControlJuego;
    private EventosControl eventosControl;

    @BeforeEach
    public void setUp() {
        laberinto = new Laberinto(3, 3);
        jugador = new Jugador(new Posicion(0, 0));
        dummyControlJuego = new DummyControlJuego(laberinto, jugador);
        eventosControl = new EventosControl(jugador);
        // Inyectamos la instancia dummy en EventosControl
        eventosControl.setControlJuego(dummyControlJuego);
    }

    @Test
    public void testKeyPressedUp() {
        // Simulamos la pulsación de la tecla UP
        KeyEvent keyEvent = new KeyEvent(new java.awt.Panel(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        eventosControl.keyPressed(keyEvent);
        assertEquals(0, dummyControlJuego.lastDeltaX, "Delta X debe ser 0 para la tecla UP");
        assertEquals(-1, dummyControlJuego.lastDeltaY, "Delta Y debe ser -1 para la tecla UP");
    }

    @Test
    public void testKeyPressedRight() {
        // Simulamos la pulsación de la tecla RIGHT
        KeyEvent keyEvent = new KeyEvent(new java.awt.Panel(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        eventosControl.keyPressed(keyEvent);
        assertEquals(1, dummyControlJuego.lastDeltaX, "Delta X debe ser 1 para la tecla RIGHT");
        assertEquals(0, dummyControlJuego.lastDeltaY, "Delta Y debe ser 0 para la tecla RIGHT");
    }
}
