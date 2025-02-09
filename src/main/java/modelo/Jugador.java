package modelo;

public class Jugador {
    private Posicion posicion;

    public Jugador(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void mover(int deltaX, int deltaY, Laberinto laberinto) {
        int nuevoX = posicion.getX() + deltaX;
        int nuevoY = posicion.getY() + deltaY;

        // Validar que la nueva posición se encuentre dentro de los límites
        if(nuevoX < 0 || nuevoX >= laberinto.getAncho() || nuevoY < 0 || nuevoY >= laberinto.getAlto()) {
            throw new IllegalArgumentException("Movimiento fuera de los límites del laberinto.");
        }

        // Validar que no se intente mover a través de una pared
        Celda celdaActual = laberinto.getCelda(posicion.getX(), posicion.getY());
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

        // Si la validación es exitosa, se procede a mover al jugador
        posicion.setX(nuevoX);
        posicion.setY(nuevoY);
    }

}
