package util_test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import util.Utilidades;

public class UtilidadesTest {

    @Test
    public void testImprimirMensaje() {
        // Se verifica que el método no lance excepciones.
        assertDoesNotThrow(() -> Utilidades.imprimirMensaje("Mensaje de prueba"));
    }
}
