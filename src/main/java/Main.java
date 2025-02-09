import modelo.Laberinto;
import modelo.Jugador;
import modelo.IAJugador;
import modelo.Posicion;
import util.GeneradorLaberinto;
import controlador.ControlJuego;
import controlador.ControladorIA;
import vista.VistaLaberinto;
import vista.PanelControles;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        int ancho = 10;
        int alto = 10;
        Laberinto laberinto = new Laberinto(ancho, alto);
        // Generamos el laberinto (por ejemplo, partiendo desde la celda (0,0))
        GeneradorLaberinto.generar(laberinto, 0, 0);

        // Posición inicial para el jugador y la IA (usualmente la celda de inicio)
        Jugador jugador = new Jugador(new Posicion(0, 0));
        IAJugador iaJugador = new IAJugador(new Posicion(0, 0));

        // Controladores
        ControlJuego controlJuego = new ControlJuego(laberinto, jugador, iaJugador, null); // La vista se asignará luego
        ControladorIA controladorIA = new ControladorIA(laberinto);
        // Calculamos la ruta para la IA y se la asignamos
        iaJugador.setRuta(controladorIA.calcularRuta(new Posicion(0, 0), new Posicion(ancho - 1, alto - 1)));

        // Creamos la vista
        VistaLaberinto vistaLaberinto = new VistaLaberinto(laberinto, jugador, iaJugador);
        PanelControles panelControles = new PanelControles(controlJuego);

        // Asignamos la vista al controlador (si es necesario para llamar a repaint, etc.)
        // controlJuego.setVista(vistaLaberinto); // O mediante un setter, según la implementación

        // Creamos la ventana principal
        new VentanaPrincipal(jugador, iaJugador, vistaLaberinto, panelControles);
    }
}
