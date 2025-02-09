package modelo;

public class Jugador {
    private Posicion posicion;

    public Jugador(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    // Método para mover al jugador (se puede validar según las paredes del laberinto)
    public void mover(int deltaX, int deltaY) {
        posicion.setX(posicion.getX() + deltaX);
        posicion.setY(posicion.getY() + deltaY);
    }
}
