package modelo;

import java.util.List;
import util.AStar;

/**
 * Clase que representa al jugador controlado por la IA.
 */
public class IAJugador {
    private Posicion posicion;
    private List<Posicion> ruta; // Ruta calculada por A*.

    public IAJugador(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setRuta(List<Posicion> ruta) {
        this.ruta = ruta;
    }

    /**
     * Avanza al siguiente paso de la ruta calculada.
     */
    public void avanzar() {
        if (ruta != null && !ruta.isEmpty()) {
            this.posicion = ruta.remove(0);
        }
    }
}
