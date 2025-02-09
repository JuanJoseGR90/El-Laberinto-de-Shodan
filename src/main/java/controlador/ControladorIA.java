package controlador;

import modelo.Laberinto;
import modelo.Posicion;
import util.AStar;
import java.util.List;

public class ControladorIA {

    private Laberinto laberinto;

    public ControladorIA(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    // Calcula la ruta óptima desde el inicio hasta la meta usando A*
    public List<Posicion> calcularRuta(Posicion inicio, Posicion fin) {
        AStar aStar = new AStar(laberinto);
        return aStar.buscar(inicio, fin);
    }
}