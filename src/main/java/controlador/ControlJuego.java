package controlador;

import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import util.GeneradorLaberinto;
import vista.VistaLaberinto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * Controlador principal del juego. Gestiona la lógica del juego, el movimiento
 * del jugador y la actualización del estado del juego.
 */
public class ControlJuego {
    private static final Logger LOGGER = Logger.getLogger(ControlJuego.class.getName());
    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private boolean juegoTerminado;
    private Posicion posicionMeta;

    public ControlJuego(Laberinto laberinto, Jugador jugador, IAJugador iaJugador, VistaLaberinto vista) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.iaJugador = iaJugador;
        this.vista = vista;
        this.juegoTerminado = false;
        // Asumimos que la salida es la celda inferior derecha
        this.posicionMeta = new Posicion(laberinto.getAncho() - 1, laberinto.getAlto() - 1);
    }

    /**
     * Inicia el ciclo de juego en un hilo separado utilizando SwingWorker para
     * no bloquear la interfaz gráfica.
     */
    public void iniciarJuego() {
        SwingWorker<Void, Void> gameWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (!juegoTerminado) {
                    try {
                        actualizarJuego();
                        // Notificar a la vista para que se repinte.
                        vista.repaint();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LOGGER.log(Level.SEVERE, "El hilo de juego fue interrumpido", e);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error en el ciclo de juego", e);
                    }
                }
                return null;
            }
        };
        gameWorker.execute();
    }

    /**
     * Procesa el movimiento del jugador.
     * @param deltaX Variación en el eje X.
     * @param deltaY Variación en el eje Y.
     */
    public void procesarMovimiento(int deltaX, int deltaY) {
        try {
            jugador.mover(deltaX, deltaY, laberinto);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Movimiento no permitido: " + ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza el estado del juego: mueve la IA y verifica condiciones de victoria.
     */
    public void actualizarJuego() {
        try {
            iaJugador.avanzar();
            if (jugador.getPosicion().equals(posicionMeta)) {
                LOGGER.info("¡El jugador ha ganado!");
                finalizarJuego();
            } else if (iaJugador.getPosicion().equals(posicionMeta)) {
                LOGGER.info("La IA ha ganado. Fin del juego.");
                finalizarJuego();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar el juego", e);
        }
    }

    /**
     * Finaliza el juego.
     */
    public void finalizarJuego() {
        juegoTerminado = true;
    }

    /**
     * Reinicia el juego, generando un nuevo laberinto y reseteando posiciones.
     */
    public void reiniciarJuego() {
        try {
            int ancho = laberinto.getAncho();
            int alto = laberinto.getAlto();
            Laberinto laberintoAntiguo = laberinto;
            // Se utiliza el generador de laberintos; aquí se elige el método de Prim.
            laberinto = new Laberinto(ancho, alto);
            GeneradorLaberinto.generarConPrim(laberinto);

            // Reiniciar la posición del jugador e IA a la celda de inicio (0,0)
            jugador.getPosicion().setX(0);
            jugador.getPosicion().setY(0);
            iaJugador.getPosicion().setX(0);
            iaJugador.getPosicion().setY(0);

            // Actualizar la posición meta (celda inferior derecha)
            posicionMeta = new Posicion(ancho - 1, alto - 1);

            // Recalcular la ruta de la IA utilizando A*
            ControladorIA controladorIA = new ControladorIA(laberinto);
            iaJugador.setRuta(controladorIA.calcularRuta(new Posicion(0, 0), posicionMeta));

            juegoTerminado = false;

            // Actualizar la vista con el nuevo laberinto.
            if (vista != null) {
                vista.setLaberinto(laberinto);
                vista.repaint();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al reiniciar el juego", e);
        }
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
