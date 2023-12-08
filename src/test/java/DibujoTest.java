import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.File;
public class DibujoTest {
    @Test
    public void testDeshacer() {
        VentanaPrincipal nucleo = new VentanaPrincipal();
        Dibujo dibujo = new Dibujo(nucleo);
        dibujo.getFiguras().add(new Figura(new Rectangle2D.Double(10, 10, 100, 100), Color.RED, false));
        int numFiguras = dibujo.getFiguras().size();
        dibujo.deshacer();
        assertEquals(numFiguras - 1, dibujo.getFiguras().size());
    }


    @Test
    public void testReiniciar() {
        VentanaPrincipal nucleo = new VentanaPrincipal();
        Dibujo dibujo = new Dibujo(nucleo);
        dibujo.getFiguras().add(new Figura(new Rectangle2D.Double(10, 10, 100, 100), Color.RED, false));
        dibujo.getFiguras().add(new Figura(new Rectangle2D.Double(20, 20, 200, 200), Color.BLUE, false));
        dibujo.reiniciar();
        assertTrue(dibujo.getFiguras().isEmpty());
    }
    @Test
    public void testGuardar() {
        VentanaPrincipal nucleo = new VentanaPrincipal();
        Dibujo dibujo = new Dibujo(nucleo);
        Figura figura = new Figura(new Rectangle2D.Double(10, 10, 100, 100), Color.RED, false);
        dibujo.getFiguras().add(figura);
        dibujo.guardar();
        File file = new File("Figuras.ser");
        assertTrue(file.exists());
    }
}
