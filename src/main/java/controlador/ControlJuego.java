package controlador;

import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import util.GeneradorLaberinto;
import vista.VistaLaberinto;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            // Suponiendo que la salida es la celda inferior derecha:
            this.posicionMeta = new Posicion(laberinto.getAncho() - 1, laberinto.getAlto() - 1);
        }

        public void iniciarJuego() {
            while (!juegoTerminado) {
                try {
                    actualizarJuego();
                    vista.repaint();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.log(Level.SEVERE, "El hilo de juego fue interrumpido", e);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error en el ciclo de juego", e);
                }
            }
        }

        public void procesarMovimiento(int deltaX, int deltaY) {
            // Se puede incluir la validación de la celda destino
            jugador.mover(deltaX, deltaY, laberinto);
            // Aquí se puede actualizar el estado del juego, por ejemplo, comprobar si se alcanzó la meta
        }

        private void actualizarJuego() {
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

        public void finalizarJuego() {
            juegoTerminado = true;
        }

        public void reiniciarJuego() {
            // Obtener las dimensiones actuales del laberinto
            int ancho = laberinto.getAncho();
            int alto = laberinto.getAlto();

            // Crear y generar un nuevo laberinto
            laberinto = new Laberinto(ancho, alto);
            GeneradorLaberinto.generar(laberinto, 0, 0);

            // Reiniciar la posición del jugador (asumimos que la posición inicial es (0,0))
            jugador.getPosicion().setX(0);
            jugador.getPosicion().setY(0);

            // Reiniciar la posición de la IA de forma similar
            iaJugador.getPosicion().setX(0);
            iaJugador.getPosicion().setY(0);

            // Actualizar la posición meta (por ejemplo, celda inferior derecha)
            posicionMeta = new Posicion(ancho - 1, alto - 1);

            // Recalcular la ruta de la IA utilizando A*
            ControladorIA controladorIA = new ControladorIA(laberinto);
            iaJugador.setRuta(controladorIA.calcularRuta(new Posicion(0, 0), posicionMeta));

            // Reiniciar el estado del juego
            juegoTerminado = false;

            // Actualizar la vista (si la referencia de laberinto cambia, actualizarla en la vista)
            if (vista != null) {
                vista.setLaberinto(laberinto);
                vista.repaint();
            }
        }
    }
