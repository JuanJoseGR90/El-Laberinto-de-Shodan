package modelo_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Celda;

public class LaberintoTest {

    @Test
    public void testDimensionLaberinto() {
        int ancho = 5;
        int alto = 4;
        Laberinto laberinto = new Laberinto(ancho, alto);
        assertEquals(ancho, laberinto.getAncho());
        assertEquals(alto, laberinto.getAlto());
    }

    @Test
    public void testGetCelda() {
        Laberinto laberinto = new Laberinto(3, 3);
        Celda celda = laberinto.getCelda(1, 1);
        assertNotNull(celda);
        assertEquals(1, celda.getX());
        assertEquals(1, celda.getY());
        assertNull(laberinto.getCelda(-1, 0));
        assertNull(laberinto.getCelda(3, 3));
    }
}
