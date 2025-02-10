import controlador.ControlJuego;
import controlador.ControladorIA;
import modelo.IAJugador;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Posicion;
import util.GeneradorLaberinto;
import vista.PanelControles;
import vista.VentanaPrincipal;
import vista.VistaLaberinto;

/**
 * Clase principal para iniciar el juego del laberinto.
 */
public class Main {
    public static void main(String[] args) {
        int ancho = 50;
        int alto = 30;
        Laberinto laberinto = new Laberinto(ancho, alto);
        // Generar el laberinto usando el algoritmo de Prim para mayor variabilidad.
        GeneradorLaberinto.generarConPrim(laberinto);

        // Inicializar las posiciones del jugador y de la IA.
        Jugador jugador = new Jugador(new Posicion(0, 0));
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));

        // Calcular la ruta de la IA usando A*.
        ControladorIA controladorIA = new ControladorIA(laberinto);
        iaJugador.setRuta(controladorIA.calcularRuta(new Posicion(0, 0), new Posicion(ancho - 1, alto - 1)));

        // Crear la vista del laberinto.
        VistaLaberinto vistaLaberinto = new VistaLaberinto(laberinto, jugador, iaJugador);
        // Crear el controlador del juego.
        ControlJuego controlJuego = new ControlJuego(laberinto, jugador, iaJugador, vistaLaberinto);
        // Crear el panel de controles.
        PanelControles panelControles = new PanelControles(controlJuego);
        // Crear la ventana principal.
        new VentanaPrincipal(jugador, iaJugador, vistaLaberinto, panelControles);
    }
}
