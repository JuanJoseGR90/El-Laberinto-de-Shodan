package util;

import modelo.Posicion;

public class Heuristica {
    // Calcula la distancia Manhattan entre dos posiciones
    public static double manhattan(Posicion a, Posicion b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}
