import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase que representa una figura dibujada en el lienzo.
 */
public class Figura implements Serializable {
    private Shape shape;
    private Color color;
    private String texto;
    private Font font;
    private boolean dash;

    /**
     * Constructor de la clase Figura para figuras sin texto.
     *
     * @param shape   Forma de la figura.
     * @param color   Color de la figura.
     */
    public Figura(Shape shape, Color color, boolean dash) {
        this.shape = shape;
        this.color = color;
        this.texto = null;
        this.font = null;
        this.dash = dash;
    }

    public Figura(Shape shape, Color color, String texto, Font font) {
        this.shape = shape;
        this.color = color;
        this.texto = texto;
        this.font = font;
    }

    /**
     * Obtiene la forma de la figura.
     *
     * @return Forma de la figura.
     */

    public Shape getShape() {
        return shape;
    }

    /**
     * Establece la forma de la figura.
     *
     * @param shape Nueva forma de la figura.
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Obtiene el color de la figura.
     *
     * @return Color de la figura.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Obtiene el texto.
     *
     * @return Texto.
     */

    public String getTexto() {
        return texto;
    }

    /**
     * Establece un nuevo texto.
     *
     * @param texto Nuevo texto.
     */

    public void setTexto(String texto) {
        this.texto = texto;
    }
    /**
     * Obtiene la fuente utilizada para escribir el texto.
     *
     * @return La fuente o null si no hay ninguna fuente definida.
     */

    public Font getFont() {
        return font;
    }

    public boolean getDash(){
        return dash;
    }
    /**
     * Verifica si el punto especificado por las coordenadas (px, py) está dentro de la figura.
     *
     * @param px Coordenada x del punto.
     * @param py Coordenada y del punto.
     * @return true si el punto está dentro de la figura, false de lo contrario.
     */
    public boolean contienePunto(int px, int py) {
        return shape.getBounds().contains(px, py);
    }

}