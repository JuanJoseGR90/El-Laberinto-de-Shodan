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
 * Controlador principal del juego. Gestiona la lógica, el movimiento del jugador,
 * la IA, y el sistema de temporizador y puntuación.
 */
public class ControlJuego {
    private static final Logger LOGGER = Logger.getLogger(ControlJuego.class.getName());
    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private boolean juegoTerminado;
    private Posicion posicionMeta;

    // Configuración para la velocidad de la IA
    private int iaDelay = 100; // valor en milisegundos; aumentar este número reduce la velocidad de la IA

    // Variables para el sistema de puntuación y temporizador
    private long startTime;
    private int moveCount;
    private int score;

    public ControlJuego(Laberinto laberinto, Jugador jugador, IAJugador iaJugador, VistaLaberinto vista) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.iaJugador = iaJugador;
        this.vista = vista;
        this.juegoTerminado = false;
        this.posicionMeta = new Posicion(laberinto.getAncho() - 1, laberinto.getAlto() - 1);
    }

    /**
     * Inicia el juego en un hilo separado y reinicia el temporizador y el contador de movimientos.
     */
    public void iniciarJuego() {
        startTime = System.currentTimeMillis();
        moveCount = 0;
        SwingWorker<Void, Void> gameWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (!juegoTerminado) {
                    try {
                        actualizarJuego();
                        vista.repaint();
                        // Se utiliza iaDelay para controlar la velocidad de la IA
                        Thread.sleep(iaDelay);
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
     * Procesa el movimiento del jugador y actualiza el contador de movimientos.
     */
    public void procesarMovimiento(int deltaX, int deltaY) {
        try {
            jugador.mover(deltaX, deltaY, laberinto);
            moveCount++;
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
     * Finaliza el juego y se podría mostrar la puntuación final.
     */
    public void finalizarJuego() {
        juegoTerminado = true;
        // Aquí se podría invocar una pantalla final que muestre la puntuación
    }

    /**
     * Reinicia el juego generando un nuevo laberinto y reseteando temporizador y contador.
     */
    public void reiniciarJuego() {
        try {
            int ancho = laberinto.getAncho();
            int alto = laberinto.getAlto();
            laberinto = new Laberinto(ancho, alto);
            GeneradorLaberinto.generar(laberinto, 0, 0);
            jugador.getPosicion().setX(0);
            jugador.getPosicion().setY(0);
            iaJugador.getPosicion().setX(0);
            iaJugador.getPosicion().setY(0);
            posicionMeta = new Posicion(ancho - 1, alto - 1);

            controlador.ControladorIA controladorIA = new controlador.ControladorIA(laberinto);
            iaJugador.setRuta(controladorIA.calcularRuta(new Posicion(0, 0), posicionMeta));

            juegoTerminado = false;
            startTime = System.currentTimeMillis();
            moveCount = 0;
            vista.setLaberinto(laberinto);
            vista.repaint();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al reiniciar el juego", e);
        }
    }

    /**
     * Ajusta la velocidad de la IA mediante el retraso (delay) en milisegundos.
     * @param delay Valor en milisegundos.
     */
    public void setIaDelay(int delay) {
        this.iaDelay = delay;
    }

    // Getters para temporizador y contador.
    public long getStartTime() { return startTime; }
    public int getMoveCount() { return moveCount; }

    /**
     * Calcula la puntuación del juego según los movimientos y el tiempo transcurrido.
     * @return Puntuación calculada.
     */
    public int getScore() {
        long elapsed = System.currentTimeMillis() - startTime;
        score = 10000 - moveCount - (int)(elapsed / 1000);
        return score;
    }
}
