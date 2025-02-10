package modelo;

import modelo.Laberinto;

/**
 * Clase que representa al jugador humano en el laberinto.
 */
public class Jugador {
    private Posicion posicion;

    public Jugador(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Mueve al jugador desde su posición actual a una nueva posición.
     * @param deltaX Variación en el eje X.
     * @param deltaY Variación en el eje Y.
     * @param laberinto Laberinto en el que se realiza el movimiento.
     * @throws IllegalArgumentException Si el movimiento sale de los límites.
     * @throws IllegalStateException Si se intenta mover a través de una pared.
     */
    public void mover(int deltaX, int deltaY, Laberinto laberinto) {
        int nuevoX = posicion.getX() + deltaX;
        int nuevoY = posicion.getY() + deltaY;

        // Validar límites del laberinto.
        if (nuevoX < 0 || nuevoX >= laberinto.getAncho() || nuevoY < 0 || nuevoY >= laberinto.getAlto()) {
            throw new IllegalArgumentException("Movimiento fuera de los límites del laberinto.");
        }

        Celda celdaActual = laberinto.getCelda(posicion.getX(), posicion.getY());
        // Validar movimiento a través de una pared.
        if(deltaX == 1 && celdaActual.isParedDerecha()) {
            throw new IllegalStateException("Movimiento no permitido, hay una pared a la derecha.");
        }
        if(deltaX == -1 && celdaActual.isParedIzquierda()) {
            throw new IllegalStateException("Movimiento no permitido, hay una pared a la izquierda.");
        }
        if(deltaY == 1 && celdaActual.isParedAbajo()) {
            throw new IllegalStateException("Movimiento no permitido, hay una pared abajo.");
        }
        if(deltaY == -1 && celdaActual.isParedArriba()) {
            throw new IllegalStateException("Movimiento no permitido, hay una pared arriba.");
        }

        // Actualiza la posición.
        posicion.setX(nuevoX);
        posicion.setY(nuevoY);
    }
}
