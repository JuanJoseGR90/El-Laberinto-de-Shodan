package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.Jugador;

/**
 * Clase para manejar eventos de teclado.
 */
public class EventosControl extends KeyAdapter {
    private Jugador jugador;
    private ControlJuego controlJuego;

    public EventosControl(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setControlJuego(ControlJuego controlJuego) {
        this.controlJuego = controlJuego;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Si el controlador es nulo o la partida no se ha iniciado, no se procesa el movimiento.
        if (controlJuego == null || !controlJuego.isGameStarted()) return;

        int key = e.getKeyCode();
        int deltaX = 0, deltaY = 0;
        if (key == KeyEvent.VK_UP) {
            deltaY = -1;
        } else if (key == KeyEvent.VK_DOWN) {
            deltaY = 1;
        } else if (key == KeyEvent.VK_LEFT) {
            deltaX = -1;
        } else if (key == KeyEvent.VK_RIGHT) {
            deltaX = 1;
        }
        controlJuego.procesarMovimiento(deltaX, deltaY);
    }
}
