package modelo;

public class Laberinto {
    private Celda[][] celdas;
    private int ancho, alto;

    public Laberinto(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        celdas = new Celda[alto][ancho];
        // Inicialización de cada celda
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j] = new Celda(j, i);
            }
        }
    }

    public Celda getCelda(int x, int y) {
        if (x >= 0 && x < ancho && y >= 0 && y < alto) {
            return celdas[y][x];
        }
        return null;
    }

    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }

    // Método de depuración para imprimir el laberinto en consola
    public void imprimir() {
        for (int i = 0; i < alto; i++) {
            // Imprime las paredes superiores de cada celda
            for (int j = 0; j < ancho; j++) {
                System.out.print(celdas[i][j].isParedArriba() ? "+---" : "+   ");
            }
            System.out.println("+");
            // Imprime las paredes laterales
            for (int j = 0; j < ancho; j++) {
                System.out.print(celdas[i][j].isParedIzquierda() ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // Línea final inferior
        for (int j = 0; j < ancho; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
