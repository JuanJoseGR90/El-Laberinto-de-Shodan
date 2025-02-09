package modelo_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Celda;

public class CeldaTest {

    @Test
    public void testParedesIniciales() {
        Celda celda = new Celda(0, 0);
        // Todas las paredes deben estar activas inicialmente.
        assertTrue(celda.isParedArriba());
        assertTrue(celda.isParedAbajo());
        assertTrue(celda.isParedIzquierda());
        assertTrue(celda.isParedDerecha());
    }

    @Test
    public void testSetParedesYVisitada() {
        Celda celda = new Celda(0, 0);
        celda.setParedArriba(false);
        celda.setVisitada(true);
        assertFalse(celda.isParedArriba());
        assertTrue(celda.isVisitada());
    }
}
