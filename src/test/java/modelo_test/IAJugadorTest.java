package modelo_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.IAJugador;
import modelo.Posicion;
import java.util.ArrayList;
import java.util.List;

public class IAJugadorTest {

    @Test
    public void testAvanzar() {
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));
        List<Posicion> ruta = new ArrayList<>();
        ruta.add(new Posicion(0, 1));
        ruta.add(new Posicion(0, 2));
        iaJugador.setRuta(ruta);

        iaJugador.avanzar();
        assertEquals(0, iaJugador.getPosicion().getX());
        assertEquals(1, iaJugador.getPosicion().getY());

        iaJugador.avanzar();
        assertEquals(0, iaJugador.getPosicion().getX());
        assertEquals(2, iaJugador.getPosicion().getY());

        // Si la ruta ya se consumió, la posición permanece igual.
        iaJugador.avanzar();
        assertEquals(0, iaJugador.getPosicion().getX());
        assertEquals(2, iaJugador.getPosicion().getY());
    }
}
