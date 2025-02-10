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
 * Controlador principal del juego. Gestiona la lógica, el movimiento del jugador, la IA,
 * el temporizador, la puntuación y los estados de inicio/pausa.
 */
public class ControlJuego {
    private static final Logger LOGGER = Logger.getLogger(ControlJuego.class.getName());
    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private boolean juegoTerminado;
    private boolean gameStarted;  // Flag que indica si la partida ha sido iniciada
    private boolean paused;       // Flag para indicar pausa
    private Posicion posicionMeta;

    // Control de velocidad de la IA
    private int iaDelay = 200; // En milisegundos; aumentar este valor reduce la velocidad

    // Variables para puntuación y temporizador
    private long startTime;
    private int moveCount;
    private int score;

    public ControlJuego(Laberinto laberinto, Jugador jugador, IAJugador iaJugador, VistaLaberinto vista) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.iaJugador = iaJugador;
        this.vista = vista;
        this.juegoTerminado = false;
        this.gameStarted = false;
        this.paused = false;
        // La meta es la celda inferior derecha
        this.posicionMeta = new Posicion(laberinto.getAncho() - 1, laberinto.getAlto() - 1);
    }

    /**
     * Inicia el juego, activa la bandera y reinicia el temporizador y contador.
     */
    public void iniciarJuego() {
        gameStarted = true;
        startTime = System.currentTimeMillis();
        moveCount = 0;
        SwingWorker<Void, Void> gameWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                ControladorIA controladorIA = new ControladorIA(laberinto);
                while (!juegoTerminado) {
                    if (paused) {
                        try { Thread.sleep(100); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
                        continue;
                    }
                    try {
                        // La IA calcula su ruta en tiempo real y avanza un paso
                        iaJugador.avanzar(controladorIA, posicionMeta);
                        actualizarJuego();
                        vista.repaint();
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
     * Procesa el movimiento del jugador solo si la partida ha iniciado.
     */
    public void procesarMovimiento(int deltaX, int deltaY) {
        if (!gameStarted) return; // No se permite mover antes de iniciar la partida
        try {
            jugador.mover(deltaX, deltaY, laberinto);
            moveCount++;
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Movimiento no permitido: " + ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza el estado del juego y verifica si se ha alcanzado la meta.
     */
    public void actualizarJuego() {
        if (jugador.getPosicion().equals(posicionMeta)) {
            LOGGER.info("¡El jugador ha ganado!");
            finalizarJuego();
        } else if (iaJugador.getPosicion().equals(posicionMeta)) {
            LOGGER.info("La IA ha ganado. Fin del juego.");
            finalizarJuego();
        }
    }

    /**
     * Finaliza el juego y calcula la puntuación.
     */
    public void finalizarJuego() {
        juegoTerminado = true;
        long elapsed = System.currentTimeMillis() - startTime;
        score = 10000 - moveCount - (int)(elapsed / 1000);
        // Aquí se podría mostrar un diálogo final con la puntuación.
    }

    /**
     * Reinicia el juego reiniciando las posiciones sin crear nuevos objetos y reseteando flags.
     */
    public void reiniciarJuego() {
        try {
            int ancho = laberinto.getAncho();
            int alto = laberinto.getAlto();
            laberinto = new Laberinto(ancho, alto);
            // Se utiliza el generador deseado; aquí se usa DFS (puedes cambiarlo según configuración)
            GeneradorLaberinto.generar(laberinto, 0, 0);

            // Reiniciar las posiciones sin crear nuevos objetos
            jugador.getPosicion().setX(0);
            jugador.getPosicion().setY(0);
            iaJugador.getPosicion().setX(0);
            iaJugador.getPosicion().setY(0);

            posicionMeta = new Posicion(ancho - 1, alto - 1);
            juegoTerminado = false;
            gameStarted = false; // La partida no está iniciada hasta pulsar "Iniciar"
            startTime = System.currentTimeMillis();
            moveCount = 0;
            vista.setLaberinto(laberinto);
            vista.repaint();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al reiniciar el juego", e);
        }
    }

    /**
     * Ajusta la velocidad de la IA (retraso en milisegundos).
     */
    public void setIaDelay(int delay) {
        this.iaDelay = delay;
    }

    // Métodos de control de flags.
    public boolean isGameStarted() { return gameStarted; }
    public void setGameStarted(boolean started) { this.gameStarted = started; }
    public void pauseGame() { paused = true; }
    public void resumeGame() { paused = false; }

    public long getStartTime() { return startTime; }
    public int getMoveCount() { return moveCount; }
    public int getScore() {
        long elapsed = System.currentTimeMillis() - startTime;
        score = 10000 - moveCount - (int)(elapsed / 1000);
        return score;
    }
}
