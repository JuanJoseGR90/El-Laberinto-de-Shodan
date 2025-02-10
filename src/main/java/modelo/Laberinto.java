package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Clase que representa el laberinto y gestiona las celdas.
 */
public class Laberinto {
    private Celda[][] celdas;
    private int ancho, alto;
    private Celda celdaSalida;

    // Soporte para el patrón Observer: notifica cambios en el laberinto.
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Laberinto(int ancho, int alto) {
        if(ancho <= 0 || alto <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores a cero.");
        }
        this.ancho = ancho;
        this.alto = alto;
        celdas = new Celda[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j] = new Celda(j, i);
            }
        }
        // Definición: la celda de salida es la inferior derecha.
        celdaSalida = getCelda(ancho - 1, alto - 1);
    }

    public Celda getCeldaSalida() {
        return celdaSalida;
    }

    public Celda getCelda(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return celdas[y][x];
        }
        return null;
    }

    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    /**
     * Método de depuración para imprimir el laberinto en consola.
     */
    public void imprimir() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alto; i++) {
            // Imprime las paredes superiores de cada celda
            for (int j = 0; j < ancho; j++) {
                sb.append(celdas[i][j].isParedArriba() ? "+---" : "+   ");
            }
            sb.append("+\n");
            // Imprime las paredes laterales
            for (int j = 0; j < ancho; j++) {
                sb.append(celdas[i][j].isParedIzquierda() ? "|   " : "    ");
            }
            sb.append("|\n");
        }
        // Línea final inferior
        for (int j = 0; j < ancho; j++) {
            sb.append("+---");
        }
        sb.append("+");
        System.out.println(sb.toString());
    }

    /**
     * Agrega un listener para recibir notificaciones de cambios.
     * @param listener El listener a agregar.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Notifica un cambio en el laberinto.
     * @param property Nombre de la propiedad.
     * @param oldValue Valor antiguo.
     * @param newValue Valor nuevo.
     */
    public void notifyChange(String property, Object oldValue, Object newValue) {
        pcs.firePropertyChange(property, oldValue, newValue);
    }
}
