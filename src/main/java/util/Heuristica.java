package util;

import modelo.Posicion;

/**
 * Clase con métodos de heurística para algoritmos de búsqueda.
 */
public class Heuristica {
    /**
     * Calcula la distancia Manhattan entre dos posiciones.
     * @param a Posición a.
     * @param b Posición b.
     * @return Distancia Manhattan.
     */
    public static double manhattan(Posicion a, Posicion b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
