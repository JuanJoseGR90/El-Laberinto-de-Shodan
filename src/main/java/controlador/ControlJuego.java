package controlador;

import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import vista.VistaLaberinto;

public class ControlJuego {
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


    // Ciclo principal del juego
    public void iniciarJuego() {
        while (!juegoTerminado) {
            try {
                actualizarJuego();
                vista.repaint();
                // Se podría incluir un retardo para una animación fluida, por ejemplo:
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Vuelve a marcar el hilo como interrumpido
                System.err.println("El hilo de juego fue interrumpido: " + e.getMessage());
            } catch (Exception e) {
                // Captura cualquier otra excepción para evitar que el juego se detenga inesperadamente
                System.err.println("Error en el ciclo de juego: " + e.getMessage());
                // Aquí se podría registrar el error o incluso finalizar el juego de forma controlada
            }
        }
    }

    public void procesarMovimiento(int deltaX, int deltaY) {
        // Se puede incluir la validación de la celda destino
        jugador.mover(deltaX, deltaY, laberinto);
        // Aquí se puede actualizar el estado del juego, por ejemplo, comprobar si se alcanzó la meta
    }


    // Actualiza el estado del juego: movimientos, colisiones, condiciones de victoria, etc.
    private void actualizarJuego() {
        // Avanza la IA y comprueba condiciones de colisión o victoria
        try {
            iaJugador.avanzar();
            // Supongamos que tenemos una posición meta (por ejemplo, la celda de salida)
            if (jugador.getPosicion().equals(posicionMeta)) {
                System.out.println("¡Has ganado!");
                finalizarJuego();
            } else if (iaJugador.getPosicion().equals(posicionMeta)) {
                System.out.println("La IA ha ganado. Fin del juego.");
                finalizarJuego();
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el juego: " + e.getMessage());
        }
    }


    public void finalizarJuego() {
        juegoTerminado = true;
    }
}
