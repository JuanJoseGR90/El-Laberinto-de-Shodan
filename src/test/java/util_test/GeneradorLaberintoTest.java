package util_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Celda;
import util.GeneradorLaberinto;

public class GeneradorLaberintoTest {

    @Test
    public void testGenerarLaberinto() {
        Laberinto laberinto = new Laberinto(5, 5);
        GeneradorLaberinto.generar(laberinto, 0, 0);
        // Verificamos que la celda de inicio (0,0) haya sido marcada como visitada.
        Celda inicio = laberinto.getCelda(0, 0);
        assertTrue(inicio.isVisitada(), "La celda de inicio debe haber sido visitada tras la generación");
    }
}
