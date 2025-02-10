package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pantalla de pausa que permite continuar, reiniciar o volver al menú principal.
 */
public class PantallaPausa extends JDialog {
    public PantallaPausa(JFrame padre) {
        super(padre, "Pausa", true);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Juego en pausa", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnContinuar = new JButton("Continuar");
        JButton btnReiniciar = new JButton("Reiniciar");
        JButton btnMenu = new JButton("Menú Principal");
        panelBotones.add(btnContinuar);
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnMenu);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnContinuar.addActionListener((ActionEvent e) -> dispose());
        btnReiniciar.addActionListener((ActionEvent e) -> {
            // Aquí se debe implementar la lógica para reiniciar el juego
            dispose();
        });
        btnMenu.addActionListener((ActionEvent e) -> System.exit(0));

        setSize(300, 200);
        setLocationRelativeTo(padre);
    }
}
