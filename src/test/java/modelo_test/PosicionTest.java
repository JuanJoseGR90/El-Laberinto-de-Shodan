package modelo_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Posicion;

public class PosicionTest {

    @Test
    public void testEqualsYHashCode() {
        Posicion p1 = new Posicion(1, 2);
        Posicion p2 = new Posicion(1, 2);
        Posicion p3 = new Posicion(2, 3);

        assertEquals(p1, p2, "Las posiciones con mismos valores deben ser iguales");
        assertNotEquals(p1, p3, "Posiciones con valores distintos no deben ser iguales");
        assertEquals(p1.hashCode(), p2.hashCode(), "HashCode debe coincidir para posiciones iguales");
    }
}
