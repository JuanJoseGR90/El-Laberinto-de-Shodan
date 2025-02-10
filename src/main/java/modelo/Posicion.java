package modelo;

/**
 * Clase que representa una posición en el laberinto.
 */
public class Posicion {
    private int x, y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Posicion)) return false;
        Posicion other = (Posicion) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}
