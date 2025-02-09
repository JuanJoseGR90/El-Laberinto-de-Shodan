import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Jugador;
import modelo.IAJugador;
import modelo.Laberinto;
import modelo.Posicion;
import controlador.ControlJuego;
import controlador.ControladorIA;
import util.GeneradorLaberinto;
import vista.VistaLaberinto;

public class ControlJuegoTest {

    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private ControlJuego controlJuego;

    @BeforeEach
    public void setUp() {
        // Creamos un laberinto pequeño para pruebas (por ejemplo, 3x3)
        laberinto = new Laberinto(3, 3);
        // Para simplificar la prueba, generamos el laberinto
        GeneradorLaberinto.generar(laberinto, 0, 0);
        // Inicializamos el jugador y la IA en la posición (0,0)
        jugador = new Jugador(new Posicion(0, 0));
        iaJugador = new IAJugador(new Posicion(0, 0));
        // Creamos una vista dummy (la vista se usará para repaint, pero en test no se visualizará)
        vista = new VistaLaberinto(laberinto, jugador, iaJugador);
        // Creamos el controlador con la vista y demás componentes
        controlJuego = new ControlJuego(laberinto, jugador, iaJugador, vista);
    }

    /**
     * Caso de uso: El jugador se mueve hacia una celda sin pared.
     * Se espera que su posición se actualice correctamente.
     */
    @Test
    public void testIntegracionMovimientoJugador() {
        // Configuramos que en la celda (0,0) la pared derecha esté eliminada para permitir movimiento a la derecha.
        laberinto.getCelda(0, 0).setParedDerecha(false);
        // Se procesa el movimiento hacia la derecha (deltaX = 1, deltaY = 0)
        controlJuego.procesarMovimiento(1, 0);
        // Verificamos que la posición del jugador se actualizó a (1,0)
        assertEquals(1, jugador.getPosicion().getX(), "El jugador debería moverse a X=1");
        assertEquals(0, jugador.getPosicion().getY(), "El jugador debería permanecer en Y=0");
    }

    /**
     * Caso de uso: El jugador alcanza la meta.
     * Se simula que el jugador llega a la celda meta (inferior derecha) y se verifica que el juego se termina.
     */
    @Test
    public void testJugadorAlcanzaMeta() {
        // Colocamos al jugador en la celda meta: (ancho-1, alto-1)
        jugador.getPosicion().setX(laberinto.getAncho() - 1);
        jugador.getPosicion().setY(laberinto.getAlto() - 1);
        // Simulamos la actualización del juego (por ejemplo, invocando el método actualizarJuego() a través de reflexión,
        // o en este ejemplo asumimos que se puede llamar directamente o mediante un método público para test)
        // Para esta prueba se asume que al actualizar, se verifica la condición de victoria.
        // (Si no dispones de un método público, podrías crear uno solo para test o exponer la variable a través de un getter).
        controlJuego.actualizarJuego();  // Asegúrate de que este método esté accesible o utilízalo vía reflexión.
        // Se espera que el juego se termine al haber alcanzado la meta
        assertTrue(controlJuego.isJuegoTerminado(), "El juego debe finalizar cuando el jugador alcanza la meta");
    }

    /**
     * Caso de uso: Reiniciar el juego.
     * Se simula que se han realizado algunos movimientos y se llama a reiniciarJuego().
     * Se espera que las posiciones del jugador y de la IA se reseteen a (0,0).
     */
    @Test
    public void testReiniciarJuegoReseteaPosiciones() {
        // Simulamos que el jugador y la IA se han movido
        jugador.getPosicion().setX(1);
        jugador.getPosicion().setY(1);
        iaJugador.getPosicion().setX(2);
        iaJugador.getPosicion().setY(2);
        // Se invoca el reinicio del juego
        controlJuego.reiniciarJuego();
        // Se verifica que ambas posiciones se han reseteado a (0,0)
        assertEquals(0, jugador.getPosicion().getX(), "El jugador debe reiniciarse en la posición 0 en X");
        assertEquals(0, jugador.getPosicion().getY(), "El jugador debe reiniciarse en la posición 0 en Y");
        assertEquals(0, iaJugador.getPosicion().getX(), "La IA debe reiniciarse en la posición 0 en X");
        assertEquals(0, iaJugador.getPosicion().getY(), "La IA debe reiniciarse en la posición 0 en Y");
    }

    /**
     * Prueba de integración: Cálculo de la ruta de la IA usando A*.
     * Se verifica que la ruta calculada no es nula, no está vacía y que la última posición corresponde a la meta.
     */
    @Test
    public void testRutaIACalculada() {
        Posicion meta = new Posicion(laberinto.getAncho() - 1, laberinto.getAlto() - 1);
        ControladorIA controladorIA = new ControladorIA(laberinto);
        var ruta = controladorIA.calcularRuta(new Posicion(0, 0), meta);
        assertNotNull(ruta, "La ruta calculada por A* no debe ser null");
        assertFalse(ruta.isEmpty(), "La ruta calculada por A* no debe estar vacía");
        // Verificar que la última posición de la ruta es la meta
        Posicion ultima = ruta.get(ruta.size() - 1);
        assertEquals(meta.getX(), ultima.getX(), "La última posición de la ruta debe tener la misma X que la meta");
        assertEquals(meta.getY(), ultima.getY(), "La última posición de la ruta debe tener la misma Y que la meta");
    }
}
