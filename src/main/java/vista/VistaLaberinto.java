package vista;

import javax.swing.JPanel;
import java.awt.Graphics;
import modelo.Laberinto;
import modelo.Celda;

public class VistaLaberinto extends JPanel {
    private Laberinto laberinto;
    private int celdaSize = 30; // Tamaño en píxeles de cada celda

    public VistaLaberinto(Laberinto laberinto) {
        this.laberinto = laberinto;
        int ancho = laberinto.getAncho() * celdaSize;
        int alto = laberinto.getAlto() * celdaSize;
        setPreferredSize(new java.awt.Dimension(ancho, alto));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar cada celda y sus paredes
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
    }
}
