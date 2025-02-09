package vista;

import modelo.Laberinto;

public class VistaConsola {
    private Laberinto laberinto;

    public VistaConsola(Laberinto laberinto) {
        this.laberinto = laberinto;
    }

    public void mostrar() {
        laberinto.imprimir();
    }
}
