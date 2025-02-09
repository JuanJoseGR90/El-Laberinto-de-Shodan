package modelo_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Celda;
import modelo.Posicion;

public class JugadorTest {

    private Laberinto laberinto;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        // Crear un laberinto pequeño para pruebas, por ejemplo 3x3
        laberinto = new Laberinto(3, 3);
        jugador = new Jugador(new Posicion(0, 0));
    }

    @Test
    public void testMovimientoValido() {
        // Suponiendo que en (0,0) la pared derecha está eliminada para la prueba
        Celda celdaInicio = laberinto.getCelda(0, 0);
        celdaInicio.setParedDerecha(false);

        // Mover al jugador a la derecha
        assertDoesNotThrow(() -> jugador.mover(1, 0, laberinto));
        assertEquals(1, jugador.getPosicion().getX());
    }

    @Test
    public void testMovimientoInvalidoConPared() {
        // En (0,0) la pared hacia la derecha sigue activa
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            jugador.mover(1, 0, laberinto);
        });
        String expectedMessage = "Movimiento no permitido, hay una pared a la derecha.";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
