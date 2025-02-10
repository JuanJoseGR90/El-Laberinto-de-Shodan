package vista;

import modelo.Laberinto;

/**
 * Vista en consola para mostrar el laberinto.
 */
public class VistaConsola {
    private Laberinto laberinto;

    public VistaConsola(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void mostrar() {
        laberinto.imprimir();
    }
}
