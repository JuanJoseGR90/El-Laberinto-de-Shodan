import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelo.Laberinto;
import modelo.Celda;
import modelo.Posicion;
import controlador.ControladorIA;

public class ControladorIATest {
    private Laberinto laberinto;

    @BeforeEach
    public void setUp() {
        laberinto = new Laberinto(3, 3);
        // Para que todas las celdas sean transitables, eliminamos todas las paredes.
        for (int y = 0; y < laberinto.getAlto(); y++) {
            for (int x = 0; x < laberinto.getAncho(); x++) {
                Celda celda = laberinto.getCelda(x, y);
                celda.setParedArriba(false);
                celda.setParedAbajo(false);
                celda.setParedIzquierda(false);
                celda.setParedDerecha(false);
            }
        }
    }

    @Test
    public void testCalcularRutaAbierta() {
        ControladorIA controladorIA = new ControladorIA(laberinto);
        List<Posicion> ruta = controladorIA.calcularRuta(new Posicion(0, 0), new Posicion(2, 2));
        assertNotNull(ruta, "La ruta calculada no debe ser null");
        assertFalse(ruta.isEmpty(), "La ruta calculada no debe estar vacía");

        // Verificar que la ruta inicie en (0,0)
        Posicion inicio = ruta.get(0);
        assertEquals(0, inicio.getX(), "La primera posición debe ser X=0");
        assertEquals(0, inicio.getY(), "La primera posición debe ser Y=0");

        // Verificar que la ruta termine en (2,2)
        Posicion fin = ruta.get(ruta.size() - 1);
        assertEquals(2, fin.getX(), "La última posición debe ser X=2");
        assertEquals(2, fin.getY(), "La última posición debe ser Y=2");
    }

    @Test
    public void testCalcularRutaSinSolucion() {
        // Restablecemos el laberinto: ponemos todas las paredes activas para bloquear el camino.
        for (int y = 0; y < laberinto.getAlto(); y++) {
            for (int x = 0; x < laberinto.getAncho(); x++) {
                Celda celda = laberinto.getCelda(x, y);
                celda.setParedArriba(true);
                celda.setParedAbajo(true);
                celda.setParedIzquierda(true);
                celda.setParedDerecha(true);
            }
        }
        // Para el test, desbloqueamos solo la celda inicial y la meta (sin conexión entre ellas)
        laberinto.getCelda(0, 0).setParedDerecha(false);
        laberinto.getCelda(2, 2).setParedIzquierda(false);

        ControladorIA controladorIA = new ControladorIA(laberinto);
        List<Posicion> ruta = controladorIA.calcularRuta(new Posicion(0, 0), new Posicion(2, 2));
        // Si no hay camino, se espera que la ruta sea null.
        assertNull(ruta, "La ruta debe ser null si no existe un camino");
    }
}
