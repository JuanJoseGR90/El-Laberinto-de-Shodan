package util;

import modelo.Laberinto;
import modelo.Celda;
import modelo.Nodo;
import modelo.Posicion;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;

/**
 * Implementación del algoritmo de búsqueda A*.
 */
public class AStar {
    private Laberinto laberinto;

    public AStar(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    /**
     * Busca la ruta óptima desde la posición de inicio hasta la posición fin.
     * @param inicio Posición de inicio.
     * @param fin Posición meta.
     * @return Lista de posiciones que conforman la ruta óptima, o null si no existe.
     */
    public List<Posicion> buscar(Posicion inicio, Posicion fin) {
        Celda celdaInicio = laberinto.getCelda(inicio.getX(), inicio.getY());
        Celda celdaFin = laberinto.getCelda(fin.getX(), fin.getY());
        if (celdaInicio == null || celdaFin == null) {
            return null;
        }

        // Cola de prioridad para la lista abierta.
        PriorityQueue<Nodo> abierta = new PriorityQueue<>(Comparator.comparingDouble(Nodo::getF));
        // HashSet para la lista cerrada para mejorar eficiencia.
        Set<String> cerrada = new HashSet<>();

        Nodo nodoInicio = new Nodo(celdaInicio);
        nodoInicio.setG(0);
        nodoInicio.setH(Heuristica.manhattan(inicio, fin));
        nodoInicio.setF(nodoInicio.getG() + nodoInicio.getH());
        abierta.add(nodoInicio);

        while (!abierta.isEmpty()) {
            Nodo actual = abierta.poll();
            // Clave para identificar la celda en el conjunto cerrado.
            String claveActual = actual.getCelda().getX() + "," + actual.getCelda().getY();
            if (cerrada.contains(claveActual)) {
                continue;
            }
            cerrada.add(claveActual);

            // Verificar si se ha alcanzado el objetivo.
            if (actual.getCelda().getX() == fin.getX() && actual.getCelda().getY() == fin.getY()) {
                return reconstruirRuta(actual);
            }

            // Obtener vecinos válidos.
            for (Celda vecino : obtenerVecinos(actual.getCelda())) {
                String claveVecino = vecino.getX() + "," + vecino.getY();
                if (cerrada.contains(claveVecino))
                    continue;
                double costoTentativo = actual.getG() + 1; // Costo uniforme para cada movimiento.
                // Buscar si el vecino ya está en la cola abierta.
                Nodo nodoVecino = buscarNodo(abierta, vecino);
                if (nodoVecino == null) {
                    nodoVecino = new Nodo(vecino);
                    nodoVecino.setG(costoTentativo);
                    nodoVecino.setH(Heuristica.manhattan(new Posicion(vecino.getX(), vecino.getY()), fin));
                    nodoVecino.setF(nodoVecino.getG() + nodoVecino.getH());
                    nodoVecino.setPadre(actual);
                    abierta.add(nodoVecino);
                } else if (costoTentativo < nodoVecino.getG()) {
                    // Mejorar la ruta encontrada.
                    nodoVecino.setG(costoTentativo);
                    nodoVecino.setF(nodoVecino.getG() + nodoVecino.getH());
                    nodoVecino.setPadre(actual);
                }
            }
        }
        return null; // No se encontró ruta.
    }

    /**
     * Reconstruye la ruta desde el nodo final hasta el inicio.
     * @param nodoFinal Nodo que representa la meta.
     * @return Lista de posiciones en la ruta.
     */
    private List<Posicion> reconstruirRuta(Nodo nodoFinal) {
        List<Posicion> ruta = new ArrayList<>();
        Nodo actual = nodoFinal;
        while (actual != null) {
            ruta.add(0, new Posicion(actual.getCelda().getX(), actual.getCelda().getY()));
            actual = actual.getPadre();
        }
        return ruta;
    }

    /**
     * Obtiene los vecinos de una celda, considerando las paredes.
     * @param celda Celda actual.
     * @return Lista de celdas vecinas accesibles.
     */
    private List<Celda> obtenerVecinos(Celda celda) {
        List<Celda> vecinos = new ArrayList<>();
        int x = celda.getX();
        int y = celda.getY();
        // Verificar vecino arriba.
        Celda arriba = laberinto.getCelda(x, y - 1);
        if (arriba != null && !celda.isParedArriba())
            vecinos.add(arriba);
        // Verificar vecino abajo.
        Celda abajo = laberinto.getCelda(x, y + 1);
        if (abajo != null && !celda.isParedAbajo())
            vecinos.add(abajo);
        // Verificar vecino izquierda.
        Celda izquierda = laberinto.getCelda(x - 1, y);
        if (izquierda != null && !celda.isParedIzquierda())
            vecinos.add(izquierda);
        // Verificar vecino derecha.
        Celda derecha = laberinto.getCelda(x + 1, y);
        if (derecha != null && !celda.isParedDerecha())
            vecinos.add(derecha);
        return vecinos;
    }

    /**
     * Busca un nodo en la cola de prioridad que corresponda a la celda dada.
     * @param cola Cola de nodos.
     * @param celda Celda a buscar.
     * @return Nodo encontrado o null si no existe.
     */
    private Nodo buscarNodo(PriorityQueue<Nodo> cola, Celda celda) {
        for (Nodo nodo : cola) {
            if (nodo.getCelda().getX() == celda.getX() && nodo.getCelda().getY() == celda.getY())
                return nodo;
        }
        return null;
    }
}
