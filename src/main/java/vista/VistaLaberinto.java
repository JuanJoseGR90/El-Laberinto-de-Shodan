package vista;

import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import modelo.Laberinto;
import modelo.Celda;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;

public class VistaLaberinto extends JPanel {
    private Laberinto laberinto;
    private Jugador jugador;
    private IAJugador iaJugador;
    private int celdaSize = 30; // Tamaño en píxeles de cada celda

    public VistaLaberinto(Laberinto laberinto, Jugador jugador, IAJugador iaJugador) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.iaJugador = iaJugador;
        int ancho = laberinto.getAncho() * celdaSize;
        int alto = laberinto.getAlto() * celdaSize;
        setPreferredSize(new Dimension(ancho, alto));
        setFocusable(true);
        configurarKeyBindings();
    }

    private void configurarKeyBindings() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        // Asociamos las flechas a las acciones de movimiento
        im.put(KeyStroke.getKeyStroke("UP"), "moverArriba");
        im.put(KeyStroke.getKeyStroke("DOWN"), "moverAbajo");
        im.put(KeyStroke.getKeyStroke("LEFT"), "moverIzquierda");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "moverDerecha");

        am.put("moverArriba", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mueve al jugador hacia arriba y repinta la vista
                jugador.mover(0, -1, laberinto);
                repaint();
            }
        });
        am.put("moverAbajo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador.mover(0, 1, laberinto);
                repaint();
            }
        });
        am.put("moverIzquierda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador.mover(-1, 0, laberinto);
                repaint();
            }
        });
        am.put("moverDerecha", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador.mover(1, 0, laberinto);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar el laberinto (paredes de cada celda)
        for (int i = 0; i < laberinto.getAlto(); i++) {
            for (int j = 0; j < laberinto.getAncho(); j++) {
                Celda celda = laberinto.getCelda(j, i);
                int x = j * celdaSize;
                int y = i * celdaSize;
                if (celda.isParedArriba())
                    g.drawLine(x, y, x + celdaSize, y);
                if (celda.isParedIzquierda())
                    g.drawLine(x, y, x, y + celdaSize);
                if (celda.isParedDerecha())
                    g.drawLine(x + celdaSize, y, x + celdaSize, y + celdaSize);
                if (celda.isParedAbajo())
                    g.drawLine(x, y + celdaSize, x + celdaSize, y + celdaSize);
            }
        }
        // Dibujar la celda de salida (por ejemplo, un rectángulo verde)
        g.setColor(Color.GREEN);
        int salidaX = (laberinto.getAncho() - 1) * celdaSize;
        int salidaY = (laberinto.getAlto() - 1) * celdaSize;
        g.drawRect(salidaX + 2, salidaY + 2, celdaSize - 4, celdaSize - 4);

        // Dibujar al jugador (círculo azul)
        g.setColor(Color.BLUE);
        Posicion posJugador = jugador.getPosicion();
        int jugadorX = posJugador.getX() * celdaSize;
        int jugadorY = posJugador.getY() * celdaSize;
        g.fillOval(jugadorX + celdaSize / 4, jugadorY + celdaSize / 4, celdaSize / 2, celdaSize / 2);

        // Dibujar a la IA (círculo rojo)
        g.setColor(Color.RED);
        Posicion posIA = iaJugador.getPosicion();
        int iaX = posIA.getX() * celdaSize;
        int iaY = posIA.getY() * celdaSize;
        g.fillOval(iaX + celdaSize / 4, iaY + celdaSize / 4, celdaSize / 2, celdaSize / 2);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }
}
