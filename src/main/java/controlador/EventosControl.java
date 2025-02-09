package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.Jugador;

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
        // Se delega el manejo del movimiento al controlador principal
        try {
            controlJuego.procesarMovimiento(deltaX, deltaY);
        } catch (Exception ex) {
            System.err.println("Movimiento no permitido: " + ex.getMessage());
        }
    }
}

