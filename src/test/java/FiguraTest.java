import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;


public class FiguraTest {
    @Test
    public void testFigura() {
        Rectangle2D shape = new Rectangle2D.Double(10, 10, 100, 100);
        Color color = Color.RED;
        boolean dash = false;
        Figura figura = new Figura(shape, color, dash);
        assertNotNull(figura);
        assertEquals(shape, figura.getShape());
        assertEquals(color, figura.getColor());
        assertEquals(dash, figura.getDash());
    }


    @Test
    public void testSetTexto() {
        Rectangle2D shape = new Rectangle2D.Double(10, 10, 100, 100);
        Color color = Color.RED;
        boolean dash = false;
        Figura figura = new Figura(shape, color, dash);
        figura.setTexto("Test");
        assertEquals("Test", figura.getTexto());
    }


    @Test
    public void testCrearFlecha() {
        Path2D shape = new Path2D.Float();
        Color color = Color.RED;
        boolean dash = false;
        Figura figura = new Figura(shape, color, dash);
        assertNotNull(figura);
        assertEquals(shape, figura.getShape());
        assertEquals(color, figura.getColor());
        assertEquals(dash, figura.getDash());
    }
    @Test
    public void testSetShape() {
        Rectangle2D shape = new Rectangle2D.Double(10, 10, 100, 100);
        Color color = Color.RED;
        boolean dash = false;
        Figura figura = new Figura(shape, color, dash);
        Rectangle2D newShape = new Rectangle2D.Double(20, 20, 200, 200);
        figura.setShape(newShape);
        assertEquals(newShape, figura.getShape());
    }
}
