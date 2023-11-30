import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Clase que representa el panel de dibujo donde se visualizan las figuras y se manejan los eventos del ratón.
 */
public class Dibujo extends JPanel implements MouseListener, MouseMotionListener {

    private VentanaPrincipal nucleo;
    private ArrayList<Figura> figuras = new ArrayList<>();
    private int xInicial, yInicial;
    private int xActual, yActual;
    private JTextField cuadroTexto;
    private Figura figuraTextoActual;

    /**
     * Constructor de la clase Dibujo.
     *
     * @param nucleo Instancia de la ventana principal.
     */
    public Dibujo(VentanaPrincipal nucleo) {
        this.nucleo = nucleo;
        this.setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);

        cuadroTexto = new JTextField();
        cuadroTexto.setBounds(10, 10, 100, 20);
        cuadroTexto.setVisible(false);
        cuadroTexto.addActionListener(e -> {
            String texto = cuadroTexto.getText();
            figuraTextoActual.setTexto(texto);
            figuras.add(figuraTextoActual);
            cuadroTexto.setVisible(false);
            repaint();
        });

        add(cuadroTexto);
    }

    /**
     * Activa el cuadro de texto para agregar una figura de texto en el punto actual del ratón.
     */
    public void activarCuadroTexto() {
        cuadroTexto.setLocation(xActual, yActual);
        cuadroTexto.setText("");
        cuadroTexto.setVisible(true);
        cuadroTexto.requestFocus();
        figuraTextoActual = crearTexto(xActual, yActual, "", nucleo.getColor(), 1);
        figuras.add(figuraTextoActual);
        repaint();
    }

    /**
     * Sobrescribe el método paintComponent para dibujar las figuras en el panel.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dibujar todas las figuras en la lista
        for (Figura figura : figuras) {
            g2.setColor(figura.getColor());

        // Esto es para las rayitas
            if (figura.getDash() == true) {
                float[] dashPattern = {10.0f, 5.0f};
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10.0f, dashPattern, 0.0f));
            } else {
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            }

            // Si la figura es de texto, se dibuja el texto en el centro del rectángulo
            if (figura.getTexto() != null && figura.getShape() instanceof Rectangle2D.Float) {
                g2.setFont(figura.getFont());
                g2.setColor(figura.getColor());
                Rectangle2D rectangulo = (Rectangle2D) figura.getShape();
                FontMetrics metrics = g2.getFontMetrics();
                int xTexto = (int) (rectangulo.getX() + (rectangulo.getWidth() - metrics.stringWidth(figura.getTexto())) / 2);
                int yTexto = (int) (rectangulo.getY() + (rectangulo.getHeight() + metrics.getAscent()) / 2);
                g2.drawString(figura.getTexto(), xTexto, yTexto);
            } else {
                g2.draw(figura.getShape());
            }
        }
    }

    // Implementación de los métodos de la interfaz MouseListener

    public void mousePressed(MouseEvent e) {
        xInicial = e.getX();
        yInicial = e.getY();
        xActual = xInicial;
        yActual = yInicial;

        String opcion = nucleo.getOpcion();
        Figura figura = null;

        // Crear la figura correspondiente según la opción seleccionada
        if (opcion.equals("LINEA")) {
            figura = crearLinea(xInicial, yInicial, xActual, yActual, nucleo.getColor(), true);
        } else if (opcion.equals("RECTANGULO")) {
            figura = crearRectangulo(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("AGREGACION")) {
            figura = crearFlecha(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("COMPOSICION")) {
            figura = crearFlecha2(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("DEPENDENCIA")) {
            figura = crearFlecha3(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("GENERALIZACION")) {
            figura = crearFlecha4(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("REALIZACION")) {
            figura = crearFlecha5(xInicial, yInicial, xActual, yActual, nucleo.getColor());
        } else if (opcion.equals("TEXTO")) {
            activarCuadroTexto();
            return;
        }

        if (figura != null) {
            figuras.add(figura);
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        // No se realiza ninguna acción al soltar el ratón
    }

    public void mouseClicked(MouseEvent e) {
        // No se realiza ninguna acción al hacer clic
    }

    public void mouseEntered(MouseEvent e) {
        // No se realiza ninguna acción al entrar en el área del dibujo
    }

    public void mouseExited(MouseEvent e) {
        // No se realiza ninguna acción al salir del área del dibujo
    }

    // Implementación de los métodos de la interfaz MouseMotionListener

    public void mouseMoved(MouseEvent e) {
        // No se realiza ninguna acción al mover el ratón
    }

    public void mouseDragged(MouseEvent e) {
        xActual = e.getX();
        yActual = e.getY();
        Figura figuraActual = figuras.get(figuras.size() - 1);
        figuraActual.setShape(actualizarShape(figuraActual.getShape(), xInicial, yInicial, xActual, yActual));
        repaint();
    }

    /**
     * Actualiza la forma de una figura según las coordenadas iniciales y finales del arrastre del ratón.
     *
     * @param shape Forma original de la figura.
     * @param x1    Coordenada x inicial.
     * @param y1    Coordenada y inicial.
     * @param x2    Coordenada x final.
     * @param y2    Coordenada y final.
     * @return La forma actualizada de la figura.
     */
    private Shape actualizarShape(Shape shape, int x1, int y1, int x2, int y2) {
        if (shape instanceof Line2D.Float) {
            Line2D linea = (Line2D) shape;
            linea.setLine(x1, y1, x2, y2);
        } if (shape instanceof Rectangle2D.Float) {
            Rectangle2D rectangulo = (Rectangle2D) shape;
            int width = Math.abs(x2 - x1);
            int height = Math.abs(y2 - y1);
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            rectangulo.setRect(x, y, width, height);

        } else if (shape instanceof Path2D.Float) {
            Path2D flecha = (Path2D) shape;
            flecha.reset();
            switch (nucleo.getOpcion()) {
                case "AGREGACION":
                    agregarFlecha(flecha, x1, y1, x2, y2);
                    break;
                case "COMPOSICION":
                    agregarFlecha2(flecha, x1, y1, x2, y2);
                    break;
                case "DEPENDENCIA":
                    agregarFlecha3(flecha, x1, y1, x2, y2);
                    break;
                case "GENERALIZACION":
                    agregarFlecha4(flecha, x1, y1, x2, y2);
                    break;
                case "REALIZACION":
                    agregarFlecha5(flecha, x1, y1, x2, y2);
                    break;
                default:
                    // Opción no válida
                    break;
            }
        }
        return shape;
    }

    /**
     * Crea una figura de tipo línea.
     *
     * @param x1    Coordenada x inicial.
     * @param y1    Coordenada y inicial.
     * @param x2    Coordenada x final.
     * @param y2    Coordenada y final.
     * @param color Color de la línea.
     * @return La figura de línea creada.
     */
    private Figura crearLinea(int x1, int y1, int x2, int y2, Color color, boolean dash) {
        Line2D linea = new Line2D.Float(x1, y1, x2, y2);
        return new Figura(linea, color, false);
    }

    /**
     * Crea una figura de tipo rectángulo.
     *
     * @param x1    Coordenada x inicial.
     * @param y1    Coordenada y inicial.
     * @param x2    Coordenada x final.
     * @param y2    Coordenada y final.
     * @param color Color del rectángulo.
     * @return La figura de rectángulo creada.
     */
    private Figura crearRectangulo(int x1, int y1, int x2, int y2, Color color) {
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        Rectangle2D rectangulo = new Rectangle2D.Float(x, y, width, height);
        return new Figura(rectangulo, color,false);
    }


    /**
     * Crea una figura de tipo flecha.
     *
     * @param x1    Coordenada x inicial.
     * @param y1    Coordenada y inicial.
     * @param x2    Coordenada x final.
     * @param y2    Coordenada y final.
     * @param color Color de la flecha.
     * @return La figura de flecha creada.
     */
    private Figura crearFlecha(int x1, int y1, int x2, int y2, Color color) {
        Path2D flecha = new Path2D.Float();
        agregarFlecha(flecha, x1, y1, x2, y2);
        return new Figura(flecha, color,false);
    }
    /**
     * Agrega una flecha a un objeto de tipo Path2D.
     *
     * @param flecha Objeto Path2D al que se agregará la flecha.
     * @param x1     Coordenada x inicial.
     * @param y1     Coordenada y inicial.
     * @param x2     Coordenada x final.
     * @param y2     Coordenada y final.
     */
    private void agregarFlecha(Path2D flecha, int x1, int y1, int x2, int y2) {
        // Calcula la posición de la punta de flecha
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowX = x2 - 15 * Math.cos(angle);
        double arrowY = y2 - 15 * Math.sin(angle);

        // Agrega la línea recta
        flecha.moveTo(x1, y1);
        flecha.lineTo(x2 - 15 * Math.cos(angle), y2 - 15 * Math.sin(angle));

        // Crea un rombo para la punta de flecha
        Path2D rombo = new Path2D.Float();
        rombo.moveTo(arrowX, arrowY);
        rombo.lineTo(arrowX - 7.5 * Math.cos(angle - Math.PI / 4), arrowY - 7.5 * Math.sin(angle - Math.PI / 4));
        rombo.lineTo(arrowX - 15 * Math.cos(angle), arrowY - 15 * Math.sin(angle));
        rombo.lineTo(arrowX - 7.5 * Math.cos(angle + Math.PI / 4), arrowY - 7.5 * Math.sin(angle + Math.PI / 4));
        rombo.closePath();

        // Agrega la punta de flecha romboidal al rombo
        flecha.append(rombo, false);
    }

    private Figura crearFlecha2(int x1, int y1, int x2, int y2, Color color) {
        Path2D flecha2 = new Path2D.Float();
        agregarFlecha(flecha2, x1, y1, x2, y2);
        return new Figura(flecha2, color,false);
    }
    private void agregarFlecha2(Path2D flecha2, int x1, int y1, int x2, int y2) {
        // Para cancular angulo de la punta
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowX = x2 - 10 * Math.cos(angle);
        double arrowY = y2 - 10 * Math.sin(angle);

        // Esto es para la linea recta
        flecha2.moveTo(x1, y1);
        flecha2.lineTo(x2 - 10 * Math.cos(angle), y2 - 10 * Math.sin(angle));

        // Tratando de hacer que se rellene el rombo D:
        Path2D rombo2 = new Path2D.Float();
        rombo2.moveTo(arrowX, arrowY);
        rombo2.lineTo(
                arrowX - (7 * Math.cos(angle - Math.PI / 4)),
                arrowY - (7 * Math.sin(angle - Math.PI / 4)));
        rombo2.lineTo(
                arrowX - 10 * Math.cos(angle),
                arrowY - 10 * Math.sin(angle));
        rombo2.lineTo(
                arrowX - 7 * Math.cos(angle + Math.PI / 4),
                arrowY - 7 * Math.sin(angle + Math.PI / 4));
        rombo2.closePath();

        // La punta de la flecha se agrega al rombo
        flecha2.append(rombo2, false);
        flecha2.append(new Line2D.Double(
                        arrowX - 7 * Math.cos(angle - Math.PI / 4),
                        arrowY - 7 * Math.sin(angle - Math.PI / 4),
                        arrowX - 7 * Math.cos(angle + Math.PI / 4),
                        arrowY - 7 * Math.sin(angle + Math.PI / 4)),
                        false);
    }
    private Figura crearFlecha3(int x1, int y1, int x2, int y2, Color color) {
        Path2D flecha3 = new Path2D.Float();
        agregarFlecha(flecha3, x1, y1, x2, y2);
        return new Figura(flecha3, color,true);
    }
    private void agregarFlecha3(Path2D flecha3, int x1, int y1, int x2, int y2) {
        flecha3.moveTo(x1, y1);
        flecha3.lineTo(x2, y2);

        double angulo = Math.atan2(y2 - y1, x2 - x1);
        double angulo1 = angulo - Math.PI / 8;
        double angulo2 = angulo + Math.PI / 8;
        int longitud = 15;

        int x3 = (int) (x2 - longitud * Math.cos(angulo1));
        int y3 = (int) (y2 - longitud * Math.sin(angulo1));
        int x4 = (int) (x2 - longitud * Math.cos(angulo2));
        int y4 = (int) (y2 - longitud * Math.sin(angulo2));

        flecha3.lineTo(x3, y3);
        flecha3.moveTo(x2, y2);
        flecha3.lineTo(x4, y4);
    }

    private Figura crearFlecha4(int x1, int y1, int x2, int y2, Color color) {
        Path2D flecha4 = new Path2D.Float();
        agregarFlecha(flecha4, x1, y1, x2, y2);
        return new Figura(flecha4, color,false);
    }

    private void agregarFlecha4(Path2D flecha4, int x1, int y1, int x2, int y2) {
        // Calcula la posición de la punta de flecha
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowX = x2 - 10 * Math.cos(angle);
        double arrowY = y2 - 10 * Math.sin(angle);

        // Agrega la línea recta
        flecha4.moveTo(x1, y1);
        flecha4.lineTo(x2 - 10 * Math.cos(angle), y2 - 10 * Math.sin(angle));

        // Crea un triángulo para la punta de la flecha
        Path2D triangulo = new Path2D.Float();
        triangulo.moveTo(arrowX, arrowY);
        triangulo.lineTo(arrowX - 10 * Math.cos(angle - Math.PI / 6), arrowY - 10 * Math.sin(angle - Math.PI / 6));
        triangulo.lineTo(arrowX - 10 * Math.cos(angle + Math.PI / 6), arrowY - 10 * Math.sin(angle + Math.PI / 6));
        triangulo.closePath();

        // Agrega la punta de flecha triangular al triángulo
        flecha4.append(triangulo, false);
    }

    private Figura crearFlecha5(int x1, int y1, int x2, int y2, Color color) {
        Path2D flecha5 = new Path2D.Float();
        agregarFlecha(flecha5, x1, y1, x2, y2);
        return new Figura(flecha5, color,true);
    }

    private void agregarFlecha5(Path2D flecha5, int x1, int y1, int x2, int y2) {
        // Calcula la posición de la punta de flecha
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowX = x2 - 10 * Math.cos(angle);
        double arrowY = y2 - 10 * Math.sin(angle);

        // Agrega la línea recta
        flecha5.moveTo(x1, y1);
        flecha5.lineTo(x2 - 10 * Math.cos(angle), y2 - 10 * Math.sin(angle));

        // Crea un triángulo para la punta de la flecha
        Path2D triangulo = new Path2D.Float();
        triangulo.moveTo(arrowX, arrowY);
        triangulo.lineTo(arrowX - 10 * Math.cos(angle - Math.PI / 6), arrowY - 10 * Math.sin(angle - Math.PI / 6));
        triangulo.lineTo(arrowX - 10 * Math.cos(angle + Math.PI / 6), arrowY - 10 * Math.sin(angle + Math.PI / 6));
        triangulo.closePath();

        // Agrega la punta de flecha triangular al triángulo
        flecha5.append(triangulo, false);
    }

    /**
     * Crea una figura de tipo texto.
     *
     * @param x      Coordenada x del texto.
     * @param y      Coordenada y del texto.
     * @param texto  Texto a mostrar.
     * @param color  Color del texto.
     * @param grosor Grosor del texto.
     * @return La figura de texto creada.
     */
    private Figura crearTexto(int x, int y, String texto, Color color, int grosor) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        Rectangle2D rectangulo = new Rectangle2D.Float(x, y, 0, 0);
        return new Figura(rectangulo, color, texto, font);
    }

    /**
     * Deshace la última figura agregada.
     */
    public void deshacer() {
        if (!figuras.isEmpty()) {
            figuras.remove(figuras.size() - 1);
            repaint();
        }
    }

    /**
     * Reinicia el lienzo, eliminando todas las figuras.
     */
    public void reiniciar() {
        figuras.clear();
        repaint();
    }
}