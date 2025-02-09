package controlador_test;

import controlador.ControlJuego;
import modelo.IAJugador;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Posicion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.GeneradorLaberinto;
import vista.VistaLaberinto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControlJuegoTest {

    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private ControlJuego controlJuego;

    @BeforeEach
    public void setUp() {
        laberinto = new Laberinto(3, 3);
        GeneradorLaberinto.generar(laberinto, 0, 0);
        jugador = new Jugador(new Posicion(0, 0));
        iaJugador = new IAJugador(new Posicion(0, 0));
        vista = new VistaLaberinto(laberinto, jugador, iaJugador);
        controlJuego = new ControlJuego(laberinto, jugador, iaJugador, vista);
    }

    @Test
    public void testIntegracionMovimientoJugador() {
        laberinto.getCelda(0, 0).setParedDerecha(false);
        controlJuego.procesarMovimiento(1, 0);
        assertEquals(1, jugador.getPosicion().getX());
        assertEquals(0, jugador.getPosicion().getY());
    }

    @Test
    public void testJugadorAlcanzaMeta() {
        // Simulamos que el jugador se encuentra en la celda meta.
        jugador.getPosicion().setX(laberinto.getAncho() - 1);
        jugador.getPosicion().setY(laberinto.getAlto() - 1);
        controlJuego.actualizarJuego();
        // Se asume que se expone un getter isJuegoTerminado() para verificar el estado.
        assertTrue(controlJuego.isJuegoTerminado(), "El juego debe finalizar al alcanzar la meta");
    }

    @Test
    public void testReiniciarJuegoReseteaPosiciones() {
        jugador.getPosicion().setX(1);
        jugador.getPosicion().setY(1);
        iaJugador.getPosicion().setX(2);
        iaJugador.getPosicion().setY(2);
        controlJuego.reiniciarJuego();
        assertEquals(0, jugador.getPosicion().getX());
        assertEquals(0, jugador.getPosicion().getY());
        assertEquals(0, iaJugador.getPosicion().getX());
        assertEquals(0, iaJugador.getPosicion().getY());
    }
}
