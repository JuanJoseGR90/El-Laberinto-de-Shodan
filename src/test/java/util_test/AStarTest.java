package util_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Celda;
import modelo.Posicion;
import util.AStar;
import java.util.List;

public class AStarTest {

    @Test
    public void testAStarRutaValida() {
        Laberinto laberinto = new Laberinto(3, 3);
        // Eliminar paredes para que el laberinto esté completamente abierto.
        for (int y = 0; y < laberinto.getAlto(); y++) {
            for (int x = 0; x < laberinto.getAncho(); x++) {
                Celda celda = laberinto.getCelda(x, y);
                celda.setParedArriba(false);
                celda.setParedAbajo(false);
                celda.setParedIzquierda(false);
                celda.setParedDerecha(false);
            }
        }
        AStar astar = new AStar(laberinto);
        List<Posicion> ruta = astar.buscar(new Posicion(0, 0), new Posicion(2, 2));
        assertNotNull(ruta, "La ruta no debe ser null en un laberinto abierto");
        assertFalse(ruta.isEmpty(), "La ruta no debe estar vacía");
        Posicion ultima = ruta.get(ruta.size() - 1);
        assertEquals(2, ultima.getX());
        assertEquals(2, ultima.getY());
    }
}
