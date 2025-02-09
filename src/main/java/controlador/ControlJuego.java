package controlador;

import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import vista.VistaLaberinto;

public class ControlJuego {
    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private VistaLaberinto vista;
    private boolean juegoTerminado;

    public ControlJuego(Laberinto laberinto, Jugador jugador, IAJugador iaJugador, VistaLaberinto vista) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.iaJugador = iaJugador;
        this.vista = vista;
        this.juegoTerminado = false;
    }

    // Ciclo principal del juego
    public void iniciarJuego() {
        while (!juegoTerminado) {
            actualizarJuego();
            vista.repaint();
            // Aquí se podría incluir un retardo (por ejemplo, Thread.sleep)
        }
    }

    // Actualiza el estado del juego: movimientos, colisiones, condiciones de victoria, etc.
    private void actualizarJuego() {
        // Ejemplo: avanzar la IA
        // iaJugador.avanzar();
        // Comprobar si el jugador o la IA han alcanzado la salida y, de ser así, finalizar el juego
    }

    public void finalizarJuego() {
        juegoTerminado = true;
    }
}
