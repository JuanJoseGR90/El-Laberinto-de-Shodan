package controlador_test;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controlador.EventosControl;
import controlador.ControlJuego;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Posicion;

// Clase dummy para capturar movimientos.
class DummyControlJuego extends ControlJuego {
    public int lastDeltaX = 0;
    public int lastDeltaY = 0;

    public DummyControlJuego(Laberinto laberinto, Jugador jugador) {
        // Creamos un controlador_test.DummyControlJuego pasando un laberinto y jugador válidos.
        // Para los parámetros IA y Vista, pasamos objetos dummy mínimos.
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
        // Se crea un laberinto de 3x3 (no nulo)
        laberinto = new Laberinto(3, 3);
        // Se inicializa el jugador en la posición (0,0)
        jugador = new Jugador(new Posicion(0, 0));
        // Se crea el dummy usando objetos válidos
        dummyControlJuego = new DummyControlJuego(laberinto, jugador);
        // Se crea la instancia de EventosControl con el jugador
        eventosControl = new EventosControl(jugador);
        // Se inyecta el dummy en EventosControl mediante el setter
        eventosControl.setControlJuego(dummyControlJuego);
    }

    @Test
    public void testKeyPressedUp() {
        // Simular pulsación de la tecla UP
        KeyEvent keyEvent = new KeyEvent(new java.awt.Panel(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'U');
        eventosControl.keyPressed(keyEvent);
        assertEquals(0, dummyControlJuego.lastDeltaX, "Delta X debe ser 0 para la tecla UP");
        assertEquals(-1, dummyControlJuego.lastDeltaY, "Delta Y debe ser -1 para la tecla UP");
    }

    @Test
    public void testKeyPressedRight() {
        // Simular pulsación de la tecla RIGHT
        KeyEvent keyEvent = new KeyEvent(new java.awt.Panel(), KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R');
        eventosControl.keyPressed(keyEvent);
        assertEquals(1, dummyControlJuego.lastDeltaX, "Delta X debe ser 1 para la tecla RIGHT");
        assertEquals(0, dummyControlJuego.lastDeltaY, "Delta Y debe ser 0 para la tecla RIGHT");
    }
}
