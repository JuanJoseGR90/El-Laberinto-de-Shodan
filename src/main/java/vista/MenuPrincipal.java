package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import util.ConfiguracionJuego;

/**
 * Pantalla de inicio que permite configurar opciones, iniciar la partida, ver el tutorial y salir.
 */
public class MenuPrincipal extends JFrame {
    private ConfiguracionJuego configuracion;

    public MenuPrincipal() {
        configuracion = new ConfiguracionJuego();
        setTitle("Juego del Laberinto - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Juego del Laberinto", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        JButton btnIniciar = new JButton("Iniciar Juego");
        JButton btnOpciones = new JButton("Opciones");
        JButton btnTutorial = new JButton("Tutorial");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnIniciar);
        panelBotones.add(btnOpciones);
        panelBotones.add(btnTutorial);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.CENTER);

        btnIniciar.addActionListener((ActionEvent e) -> {
            iniciarJuego();
        });
        btnOpciones.addActionListener((ActionEvent e) -> {
            OpcionesPanel opciones = new OpcionesPanel(this, configuracion);
            opciones.setVisible(true);
        });
        btnTutorial.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this,
                    "Tutorial:\n- Usa las flechas para mover al jugador (una vez iniciada la partida).\n- La IA calculará su ruta en tiempo real.\n- Usa el botón Pausa para detener la partida temporalmente.\n",
                    "Tutorial", JOptionPane.INFORMATION_MESSAGE);
        });
        btnSalir.addActionListener((ActionEvent e) -> System.exit(0));

        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void iniciarJuego() {
        // Cerrar el menú principal y lanzar el juego
        dispose();
        int ancho = configuracion.getAnchoLaberinto();
        int alto = configuracion.getAltoLaberinto();

        // Crear el laberinto según la configuración
        modelo.Laberinto laberinto = new modelo.Laberinto(ancho, alto);
        if (configuracion.getTipoLaberinto().equalsIgnoreCase("Prim")) {
            util.GeneradorLaberinto.generarConPrim(laberinto);
        } else {
            util.GeneradorLaberinto.generar(laberinto, 0, 0);
        }

        // Inicializar jugadores
        modelo.Jugador jugador = new modelo.Jugador(new modelo.Posicion(0, 0));
        modelo.IAJugador iaJugador = new modelo.IAJugador(new modelo.Posicion(0, 0));

        // La IA no pre-calcula la ruta completa; se calculará en tiempo real
        // Crear la vista y el controlador del juego
        VistaLaberinto vistaLaberinto = new VistaLaberinto(laberinto, jugador, iaJugador);
        controlador.ControlJuego controlJuego = new controlador.ControlJuego(laberinto, jugador, iaJugador, vistaLaberinto);
        // Ajustar la velocidad de la IA según la configuración
        controlJuego.setIaDelay(configuracion.getIaDelay());
        PanelControles panelControles = new PanelControles(controlJuego);
        new VentanaPrincipal(jugador, iaJugador, vistaLaberinto, panelControles, controlJuego);
    }
}
