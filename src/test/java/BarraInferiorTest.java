import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
public class BarraInferiorTest {

    @Test
    public void testSeleccionarColor() {
        BarraInferior barraInferior = new BarraInferior();
        barraInferior.getBotonColor().setBackground(Color.BLUE);
        Color color = barraInferior.getBotonColor().getBackground();
        assertNotNull(color);
        assertEquals(Color.BLUE, color);
    }
    @Test
    public void testGetNombreColor() {
        BarraInferior barraInferior = new BarraInferior();
        assertEquals("Negro", barraInferior.getNombreColor(Color.BLACK));
        assertEquals("Azul", barraInferior.getNombreColor(Color.BLUE));
        assertEquals("Verde", barraInferior.getNombreColor(Color.GREEN));
        assertEquals("Rosa", barraInferior.getNombreColor(Color.PINK));
        assertEquals("Cian", barraInferior.getNombreColor(Color.CYAN));
        assertEquals("Rojo", barraInferior.getNombreColor(Color.RED));
        assertEquals("Amarillo", barraInferior.getNombreColor(Color.YELLOW));
        assertEquals("Naranja", barraInferior.getNombreColor(Color.ORANGE));
        assertEquals("Magenta", barraInferior.getNombreColor(Color.MAGENTA));

        assertEquals("Desconocido", barraInferior.getNombreColor(Color.WHITE));
    }
}
