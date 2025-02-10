package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import util.ConfiguracionJuego;

/**
 * Diálogo para configurar las opciones del juego.
 */
public class OpcionesPanel extends JDialog {
    private ConfiguracionJuego configuracion;

    public OpcionesPanel(JFrame padre, ConfiguracionJuego configuracion) {
        super(padre, "Opciones", true);
        this.configuracion = configuracion;
        setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));

        // Ancho del laberinto
        panelCampos.add(new JLabel("Ancho del Laberinto:"));
        JTextField txtAncho = new JTextField(String.valueOf(configuracion.getAnchoLaberinto()));
        panelCampos.add(txtAncho);

        // Alto del laberinto
        panelCampos.add(new JLabel("Alto del Laberinto:"));
        JTextField txtAlto = new JTextField(String.valueOf(configuracion.getAltoLaberinto()));
        panelCampos.add(txtAlto);

        // Retraso de la IA
        panelCampos.add(new JLabel("Retraso de la IA (ms):"));
        JTextField txtIaDelay = new JTextField(String.valueOf(configuracion.getIaDelay()));
        panelCampos.add(txtIaDelay);

        // Tipo de laberinto (Prim o DFS)
        panelCampos.add(new JLabel("Tipo de Laberinto (Prim/DFS):"));
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Prim", "DFS"});
        cbTipo.setSelectedItem(configuracion.getTipoLaberinto());
        panelCampos.add(cbTipo);

        add(panelCampos, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener((ActionEvent e) -> {
            try {
                int ancho = Integer.parseInt(txtAncho.getText());
                int alto = Integer.parseInt(txtAlto.getText());
                int iaDelay = Integer.parseInt(txtIaDelay.getText());
                String tipo = (String) cbTipo.getSelectedItem();

                configuracion.setAnchoLaberinto(ancho);
                configuracion.setAltoLaberinto(alto);
                configuracion.setIaDelay(iaDelay);
                configuracion.setTipoLaberinto(tipo);

                JOptionPane.showMessageDialog(this, "Configuración guardada", "Opciones", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(padre);
    }
}
