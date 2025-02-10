package modelo;

import java.util.List;
import controlador.ControladorIA;

/**
 * Clase que representa al jugador controlado por la IA.
 */
public class IAJugador {
    private Posicion posicion;
    // La ruta ya no se guarda completa, la IA la calcula en tiempo real.

    public IAJugador(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Calcula la ruta desde la posición actual hasta la meta utilizando A* y
     * avanza un solo paso (si es posible). De esta forma la IA "descubre" la ruta en tiempo real.
     * @param controladorIA Controlador que permite calcular la ruta.
     * @param meta Posición meta del laberinto.
     */
    public void avanzar(ControladorIA controladorIA, Posicion meta) {
        List<Posicion> ruta = controladorIA.calcularRuta(posicion, meta);
        if(ruta != null && ruta.size() > 1) {
            // El primer elemento es la posición actual; avanzar al siguiente.
            this.posicion = ruta.get(1);
        }
    }
}
