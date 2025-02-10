package util;

/**
 * Clase para almacenar la configuración del juego.
 */
public class ConfiguracionJuego {
    private int anchoLaberinto;
    private int altoLaberinto;
    private int iaDelay; // milisegundos de retraso para la IA; aumentar este valor reduce la velocidad
    private String tipoLaberinto; // "Prim" o "DFS"
    private int dificultad; // valor numérico que representa el nivel de dificultad

    // Constructor con valores por defecto
    public ConfiguracionJuego() {
        this.anchoLaberinto = 10;
        this.altoLaberinto = 10;
        this.iaDelay = 200; // Por ejemplo, 200ms; aumentar este valor reduce la velocidad de la IA
        this.tipoLaberinto = "Prim";
        this.dificultad = 1;
    }

    // Getters y setters
    public int getAnchoLaberinto() { return anchoLaberinto; }
    public void setAnchoLaberinto(int anchoLaberinto) { this.anchoLaberinto = anchoLaberinto; }
    public int getAltoLaberinto() { return altoLaberinto; }
    public void setAltoLaberinto(int altoLaberinto) { this.altoLaberinto = altoLaberinto; }
    public int getIaDelay() { return iaDelay; }
    public void setIaDelay(int iaDelay) { this.iaDelay = iaDelay; }
    public String getTipoLaberinto() { return tipoLaberinto; }
    public void setTipoLaberinto(String tipoLaberinto) { this.tipoLaberinto = tipoLaberinto; }
    public int getDificultad() { return dificultad; }
    public void setDificultad(int dificultad) { this.dificultad = dificultad; }
}
