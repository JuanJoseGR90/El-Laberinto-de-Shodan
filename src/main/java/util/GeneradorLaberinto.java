package util;

import modelo.Laberinto;
import modelo.Celda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorLaberinto {

    // Genera el laberinto utilizando el algoritmo DFS (backtracking)
    public static void generar(Laberinto laberinto, int startX, int startY) {
        Celda celdaInicio = laberinto.getCelda(startX, startY);
        if(celdaInicio != null) {
            generarRecursivo(laberinto, celdaInicio);
        }
    }

    private static void generarRecursivo(Laberinto laberinto, Celda actual) {
        actual.setVisitada(true);
        List<Celda> vecinos = obtenerVecinosNoVisitados(laberinto, actual);
        Collections.shuffle(vecinos);
        for (Celda vecino : vecinos) {
            if (!vecino.isVisitada()) {
                eliminarPared(actual, vecino);
                generarRecursivo(laberinto, vecino);
            }
        }
    }

    private static List<Celda> obtenerVecinosNoVisitados(Laberinto laberinto, Celda actual) {
        List<Celda> vecinos = new ArrayList<>();
        int x = actual.getX();
        int y = actual.getY();
        // Vecino superior
        Celda arriba = laberinto.getCelda(x, y - 1);
        if (arriba != null && !arriba.isVisitada()) vecinos.add(arriba);
        // Vecino inferior
        Celda abajo = laberinto.getCelda(x, y + 1);
        if (abajo != null && !abajo.isVisitada()) vecinos.add(abajo);
        // Vecino izquierdo
        Celda izquierda = laberinto.getCelda(x - 1, y);
        if (izquierda != null && !izquierda.isVisitada()) vecinos.add(izquierda);
        // Vecino derecho
        Celda derecha = laberinto.getCelda(x + 1, y);
        if (derecha != null && !derecha.isVisitada()) vecinos.add(derecha);
        return vecinos;
    }

    private static void eliminarPared(Celda actual, Celda vecino) {
        int dx = vecino.getX() - actual.getX();
        int dy = vecino.getY() - actual.getY();

        if (dx == 1) {
            actual.setParedDerecha(false);
            vecino.setParedIzquierda(false);
        } else if (dx == -1) {
            actual.setParedIzquierda(false);
            vecino.setParedDerecha(false);
        } else if (dy == 1) {
            actual.setParedAbajo(false);
            vecino.setParedArriba(false);
        } else if (dy == -1) {
            actual.setParedArriba(false);
            vecino.setParedAbajo(false);
        }
    }
}
