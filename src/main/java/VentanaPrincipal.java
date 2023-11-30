import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la ventana principal de la aplicación.
 */
public class VentanaPrincipal extends JFrame {

    private Menu menu;
    private BarraHerramientas barraHerramientas;
    private Dibujo dibujo;
    private BarraInferior barraInferior;

    /**
     * Constructor de la clase VentanaPrincipal.
     */
    public VentanaPrincipal() {

        super("PIZARRA");
        configurarVentana();
        inicializarComponentes();

    }

    /**
     * Configura la ventana principal.
     */
    private void configurarVentana() {
        setSize(750, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Inicializa los componentes de la ventana principal.
     */
    private void inicializarComponentes() {
        menu = new Menu(this);
        dibujo = new Dibujo(this); // Pasar 'this' como argumento
        barraInferior = new BarraInferior();
        barraHerramientas = new BarraHerramientas(dibujo); // Pasar 'dibujo' como argumento
        setJMenuBar(menu);
        add(barraHerramientas, BorderLayout.NORTH);
        add(dibujo, BorderLayout.CENTER);
        add(barraInferior, BorderLayout.SOUTH);
    }

    /**
     * Reinicia el dibujo.
     */
    public void reiniciar() {
        dibujo.reiniciar();
    }

    /**
     * Obtiene el color seleccionado en la barra inferior.
     *
     * @return Color seleccionado.
     */
    public Color getColor() {
        return barraInferior.getBotonColor().getBackground();
    }

    /**
     * Obtiene la opción seleccionada en la barra de herramientas.
     *
     * @return Opción seleccionada.
     */
    public String getOpcion() {
        return barraHerramientas.getOpcion();
    }

    /**
     * Deshace la última acción realizada en el dibujo.
     */
    public void deshacer() {
        dibujo.deshacer();
        dibujo.repaint();
    }
}