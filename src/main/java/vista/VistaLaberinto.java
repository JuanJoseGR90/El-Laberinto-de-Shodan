package vista;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
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
    }

    public void setLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja las paredes de cada celda
        for (int i = 0; i < laberinto.getAlto(); i++) {
            for (int j = 0; j < laberinto.getAncho(); j++) {
                Celda celda = laberinto.getCelda(j, i);
                int x = j * celdaSize;
                int y = i * celdaSize;
                // Pared superior
                if (celda.isParedArriba())
                    g.drawLine(x, y, x + celdaSize, y);
                // Pared izquierda
                if (celda.isParedIzquierda())
                    g.drawLine(x, y, x, y + celdaSize);
                // Pared derecha
                if (celda.isParedDerecha())
                    g.drawLine(x + celdaSize, y, x + celdaSize, y + celdaSize);
                // Pared inferior
                if (celda.isParedAbajo())
                    g.drawLine(x, y + celdaSize, x + celdaSize, y + celdaSize);
            }
        }

        // Dibuja la celda de salida (por ejemplo, con un rectángulo verde)
        g.setColor(Color.GREEN);
        int salidaX = (laberinto.getAncho() - 1) * celdaSize;
        int salidaY = (laberinto.getAlto() - 1) * celdaSize;
        g.drawRect(salidaX + 2, salidaY + 2, celdaSize - 4, celdaSize - 4);

        // Dibuja al jugador (círculo azul)
        g.setColor(Color.BLUE);
        Posicion posJugador = jugador.getPosicion();
        int jugadorX = posJugador.getX() * celdaSize;
        int jugadorY = posJugador.getY() * celdaSize;
        g.fillOval(jugadorX + celdaSize / 4, jugadorY + celdaSize / 4, celdaSize / 2, celdaSize / 2);

        // Dibuja a la IA (círculo rojo)
        g.setColor(Color.RED);
        Posicion posIA = iaJugador.getPosicion();
        int iaX = posIA.getX() * celdaSize;
        int iaY = posIA.getY() * celdaSize;
        g.fillOval(iaX + celdaSize / 4, iaY + celdaSize / 4, celdaSize / 2, celdaSize / 2);
    }
}
