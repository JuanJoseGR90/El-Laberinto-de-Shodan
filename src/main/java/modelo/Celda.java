package modelo;

public class Celda {
    private int x, y;
    private boolean paredArriba, paredAbajo, paredIzquierda, paredDerecha;
    private boolean visitada;

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        // Inicializamos todas las paredes activas
        this.paredArriba = true;
        this.paredAbajo = true;
        this.paredIzquierda = true;
        this.paredDerecha = true;
        this.visitada = false;
    }

    // Getters y Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isParedArriba() { return paredArriba; }
    public boolean isParedAbajo() { return paredAbajo; }
    public boolean isParedIzquierda() { return paredIzquierda; }
    public boolean isParedDerecha() { return paredDerecha; }
    public boolean isVisitada() { return visitada; }

    public void setParedArriba(boolean paredArriba) { this.paredArriba = paredArriba; }
    public void setParedAbajo(boolean paredAbajo) { this.paredAbajo = paredAbajo; }
    public void setParedIzquierda(boolean paredIzquierda) { this.paredIzquierda = paredIzquierda; }
    public void setParedDerecha(boolean paredDerecha) { this.paredDerecha = paredDerecha; }
    public void setVisitada(boolean visitada) { this.visitada = visitada; }
}
