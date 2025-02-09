package modelo_test;

import modelo.Celda;
import modelo.Nodo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodoTest {

    @Test
    public void testNodoSetYGet() {
        Celda celda = new Celda(1, 1);
        Nodo nodo = new Nodo(celda);
        nodo.setG(5);
        nodo.setH(3);
        nodo.setF(8);
        assertEquals(5, nodo.getG());
        assertEquals(3, nodo.getH());
        assertEquals(8, nodo.getF());
        assertEquals(1, nodo.getCelda().getX());
        assertEquals(1, nodo.getCelda().getY());
    }
}
