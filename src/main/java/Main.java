import controlador.ControlJuego;
import modelo.IAJugador;
import modelo.Jugador;
import modelo.Laberinto;
import modelo.Posicion;
import util.GeneradorLaberinto;
import vista.PanelControles;
import vista.VentanaPrincipal;
import vista.VistaLaberinto;

public class Main {
    public static void main(String[] args) {
        int ancho = 10;
        int alto = 10;
        Laberinto laberinto = new Laberinto(ancho, alto);
        // Genera el laberinto usando el algoritmo de DFS u otro
        GeneradorLaberinto.generar(laberinto, 0, 0);

        // Inicializa las posiciones de jugador e IA
        Jugador jugador = new Jugador(new Posicion(0, 0));
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));

        // Crea la vista del laberinto pasando el laberinto y las referencias de los jugadores
        VistaLaberinto vistaLaberinto = new VistaLaberinto(laberinto, jugador, iaJugador);

        // Crea el controlador pasando la vista
        ControlJuego controlJuego = new ControlJuego(laberinto, jugador, iaJugador, vistaLaberinto);

        // Crea el panel de controles y la ventana principal, integrando la vista y el controlador
        PanelControles panelControles = new PanelControles(controlJuego);
        new VentanaPrincipal(jugador, iaJugador, vistaLaberinto, panelControles);
    }
}


