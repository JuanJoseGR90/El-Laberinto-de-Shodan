package util;

import modelo.Laberinto;
import modelo.Celda;
import modelo.Nodo;
import modelo.Posicion;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AStar {
    private Laberinto laberinto;

    public AStar(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public List<Posicion> buscar(Posicion inicio, Posicion fin) {
        Celda celdaInicio = laberinto.getCelda(inicio.getX(), inicio.getY());
        Celda celdaFin = laberinto.getCelda(fin.getX(), fin.getY());
        if (celdaInicio == null || celdaFin == null) {
            return null;
        }

        PriorityQueue<Nodo> abierta = new PriorityQueue<>(Comparator.comparingDouble(Nodo::getF));
        List<Nodo> cerrada = new ArrayList<>();

        Nodo nodoInicio = new Nodo(celdaInicio);
        nodoInicio.setG(0);
        nodoInicio.setH(Heuristica.manhattan(inicio, fin));
        nodoInicio.setF(nodoInicio.getG() + nodoInicio.getH());
        abierta.add(nodoInicio);

        while (!abierta.isEmpty()) {
            Nodo actual = abierta.poll();
            if (actual.getCelda().getX() == fin.getX() && actual.getCelda().getY() == fin.getY()) {
                return reconstruirRuta(actual);
            }
            cerrada.add(actual);

            // Obtener vecinos (considerando las paredes)
            for (Celda vecino : obtenerVecinos(actual.getCelda())) {
                if (existeEnLista(cerrada, vecino))
                    continue;

                double costoTentativo = actual.getG() + 1; // Costo uniforme
                Nodo nodoVecino = buscarNodo(abierta, vecino);

                if (nodoVecino == null) {
                    nodoVecino = new Nodo(vecino);
                    nodoVecino.setG(costoTentativo);
                    nodoVecino.setH(Heuristica.manhattan(new Posicion(vecino.getX(), vecino.getY()), fin));
                    nodoVecino.setF(nodoVecino.getG() + nodoVecino.getH());
                    nodoVecino.setPadre(actual);
                    abierta.add(nodoVecino);
                } else if (costoTentativo < nodoVecino.getG()) {
                    nodoVecino.setG(costoTentativo);
                    nodoVecino.setF(nodoVecino.getG() + nodoVecino.getH());
                    nodoVecino.setPadre(actual);
                }
            }
        }
        return null; // No se encontró ruta
    }

    private List<Posicion> reconstruirRuta(Nodo nodoFinal) {
        List<Posicion> ruta = new ArrayList<>();
        Nodo actual = nodoFinal;
        while (actual != null) {
            ruta.add(0, new Posicion(actual.getCelda().getX(), actual.getCelda().getY()));
            actual = actual.getPadre();
        }
        return ruta;
    }

    private List<Celda> obtenerVecinos(Celda celda) {
        List<Celda> vecinos = new ArrayList<>();
        int x = celda.getX();
        int y = celda.getY();
        // Verificar vecinos según las paredes: arriba, abajo, izquierda y derecha
        Celda arriba = laberinto.getCelda(x, y - 1);
        if (arriba != null && !celda.isParedArriba())
            vecinos.add(arriba);
        Celda abajo = laberinto.getCelda(x, y + 1);
        if (abajo != null && !celda.isParedAbajo())
            vecinos.add(abajo);
        Celda izquierda = laberinto.getCelda(x - 1, y);
        if (izquierda != null && !celda.isParedIzquierda())
            vecinos.add(izquierda);
        Celda derecha = laberinto.getCelda(x + 1, y);
        if (derecha != null && !celda.isParedDerecha())
            vecinos.add(derecha);
        return vecinos;
    }

    private boolean existeEnLista(List<Nodo> lista, Celda celda) {
        for (Nodo nodo : lista) {
            if (nodo.getCelda().getX() == celda.getX() && nodo.getCelda().getY() == celda.getY())
                return true;
        }
        return false;
    }

    private Nodo buscarNodo(PriorityQueue<Nodo> cola, Celda celda) {
        for (Nodo nodo : cola) {
            if (nodo.getCelda().getX() == celda.getX() && nodo.getCelda().getY() == celda.getY())
                return nodo;
        }
        return null;
    }
}
