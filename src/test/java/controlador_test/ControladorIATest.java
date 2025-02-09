package controlador_test;

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
        // Abrir el laberinto: quitar paredes en todas las celdas.
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
        assertNotNull(ruta, "La ruta no debe ser null en un laberinto abierto");
        assertFalse(ruta.isEmpty(), "La ruta no debe estar vacía");
        Posicion inicio = ruta.get(0);
        Posicion fin = ruta.get(ruta.size() - 1);
        assertEquals(0, inicio.getX());
        assertEquals(0, inicio.getY());
        assertEquals(2, fin.getX());
        assertEquals(2, fin.getY());
    }

    @Test
    public void testCalcularRutaSinSolucion() {
        // Bloqueamos el laberinto: todas las celdas con paredes activas.
        for (int y = 0; y < laberinto.getAlto(); y++) {
            for (int x = 0; x < laberinto.getAncho(); x++) {
                Celda celda = laberinto.getCelda(x, y);
                celda.setParedArriba(true);
                celda.setParedAbajo(true);
                celda.setParedIzquierda(true);
                celda.setParedDerecha(true);
            }
        }
        // Desbloqueamos únicamente la celda inicial y la meta (sin conexión real).
        laberinto.getCelda(0, 0).setParedDerecha(false);
        laberinto.getCelda(2, 2).setParedIzquierda(false);
        ControladorIA controladorIA = new ControladorIA(laberinto);
        List<Posicion> ruta = controladorIA.calcularRuta(new Posicion(0, 0), new Posicion(2, 2));
        assertNull(ruta, "La ruta debe ser null si no existe un camino");
    }
}
