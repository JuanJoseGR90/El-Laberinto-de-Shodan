package controlador;

import modelo.Laberinto;
import modelo.Posicion;
import util.AStar;
import java.util.List;

/**
 * Controlador de la IA que calcula la ruta óptima usando el algoritmo A*.
 */
public class ControladorIA {
    private Laberinto laberinto;

    public ControladorIA(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    /**
     * Calcula la ruta óptima desde la posición inicial hasta la meta.
     * @param inicio Posición inicial.
     * @param fin Posición meta.
     * @return Lista de posiciones representando la ruta.
     */
    public List<Posicion> calcularRuta(Posicion inicio, Posicion fin) {
        AStar aStar = new AStar(laberinto);
        return aStar.buscar(inicio, fin);
    }
}
