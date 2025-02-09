package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.Jugador;

public class EventosControl extends KeyAdapter {
    private Jugador jugador;

    public EventosControl(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Movimiento básico usando las flechas del teclado
        if (key == KeyEvent.VK_UP) {
            jugador.mover(0, -1);
        } else if (key == KeyEvent.VK_DOWN) {
            jugador.mover(0, 1);
        } else if (key == KeyEvent.VK_LEFT) {
            jugador.mover(-1, 0);
        } else if (key == KeyEvent.VK_RIGHT) {
            jugador.mover(1, 0);
        }
    }
}
