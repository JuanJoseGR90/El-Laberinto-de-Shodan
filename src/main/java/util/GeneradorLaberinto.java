package util;

import modelo.Laberinto;
import modelo.Celda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Clase que contiene métodos para generar laberintos.
 */
public class GeneradorLaberinto {

    /**
     * Genera el laberinto utilizando el algoritmo DFS (backtracking) a partir de una celda inicial.
     * @param laberinto El laberinto a generar.
     * @param startX Coordenada X inicial.
     * @param startY Coordenada Y inicial.
     */
    public static void generar(Laberinto laberinto, int startX, int startY) {
        Celda celdaInicio = laberinto.getCelda(startX, startY);
        if (celdaInicio != null) {
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
        // Vecino superior.
        Celda arriba = laberinto.getCelda(x, y - 1);
        if (arriba != null && !arriba.isVisitada()) vecinos.add(arriba);
        // Vecino inferior.
        Celda abajo = laberinto.getCelda(x, y + 1);
        if (abajo != null && !abajo.isVisitada()) vecinos.add(abajo);
        // Vecino izquierdo.
        Celda izquierda = laberinto.getCelda(x - 1, y);
        if (izquierda != null && !izquierda.isVisitada()) vecinos.add(izquierda);
        // Vecino derecho.
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

    /**
     * Genera el laberinto utilizando el algoritmo de Prim para obtener un laberinto aleatorio.
     * @param laberinto El laberinto a generar.
     */
    public static void generarConPrim(Laberinto laberinto) {
        int ancho = laberinto.getAncho();
        int alto = laberinto.getAlto();
        // Inicializamos todas las celdas como no visitadas.
        boolean[][] visitado = new boolean[alto][ancho];
        // Lista de paredes: cada elemento es un array {x1, y1, x2, y2} que representa la pared entre dos celdas.
        List<int[]> paredes = new ArrayList<>();
        Random rand = new Random();

        // Seleccionar una celda aleatoria inicial.
        int xInicial = rand.nextInt(ancho);
        int yInicial = rand.nextInt(alto);
        visitado[yInicial][xInicial] = true;
        // Agregar paredes de la celda inicial.
        agregarParedes(laberinto, xInicial, yInicial, visitado, paredes);

        // Mientras existan paredes en la lista.
        while (!paredes.isEmpty()) {
            // Seleccionar una pared aleatoria.
            int[] pared = paredes.remove(rand.nextInt(paredes.size()));
            int x1 = pared[0], y1 = pared[1];
            int x2 = pared[2], y2 = pared[3];

            // Verificar si solo una de las dos celdas está visitada.
            if (visitado[y1][x1] ^ visitado[y2][x2]) {
                // Eliminar la pared entre las celdas.
                Celda celda1 = laberinto.getCelda(x1, y1);
                Celda celda2 = laberinto.getCelda(x2, y2);
                eliminarPared(celda1, celda2);
                // Marcar la celda no visitada como visitada y agregar sus paredes.
                if (!visitado[y1][x1]) {
                    visitado[y1][x1] = true;
                    agregarParedes(laberinto, x1, y1, visitado, paredes);
                } else {
                    visitado[y2][x2] = true;
                    agregarParedes(laberinto, x2, y2, visitado, paredes);
                }
            }
        }
    }

    /**
     * Agrega las paredes de la celda (x, y) a la lista de paredes.
     */
    private static void agregarParedes(Laberinto laberinto, int x, int y, boolean[][] visitado, List<int[]> paredes) {
        int ancho = laberinto.getAncho();
        int alto = laberinto.getAlto();
        // Arriba
        if (y - 1 >= 0 && !visitado[y - 1][x])
            paredes.add(new int[]{x, y, x, y - 1});
        // Abajo
        if (y + 1 < alto && !visitado[y + 1][x])
            paredes.add(new int[]{x, y, x, y + 1});
        // Izquierda
        if (x - 1 >= 0 && !visitado[y][x - 1])
            paredes.add(new int[]{x, y, x - 1, y});
        // Derecha
        if (x + 1 < ancho && !visitado[y][x + 1])
            paredes.add(new int[]{x, y, x + 1, y});
    }
}
