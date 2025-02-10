package modelo;

/**
 * Clase que representa un nodo para el algoritmo de búsqueda.
 */
public class Nodo {
    private Celda celda;
    private Nodo padre;
    private double g; // Costo desde el inicio.
    private double h; // Heurística.
    private double f; // g + h.

    public Nodo(Celda celda) {
        this.celda = celda;
    }

    public Celda getCelda() { return celda; }
    public Nodo getPadre() { return padre; }
    public void setPadre(Nodo padre) { this.padre = padre; }
    public double getG() { return g; }
    public void setG(double g) { this.g = g; }
    public double getH() { return h; }
    public void setH(double h) { this.h = h; }
    public double getF() { return f; }
    public void setF(double f) { this.f = f; }
}
