package util_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Posicion;
import util.Heuristica;

public class HeuristicaTest {

    @Test
    public void testManhattan() {
        Posicion a = new Posicion(0, 0);
        Posicion b = new Posicion(3, 4);
        double distancia = Heuristica.manhattan(a, b);
        assertEquals(7, distancia, "La distancia Manhattan entre (0,0) y (3,4) debe ser 7");
    }
}
