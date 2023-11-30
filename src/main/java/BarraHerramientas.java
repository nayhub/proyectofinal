import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la barra de herramientas de la aplicación.
 */
public class BarraHerramientas extends JToolBar implements ActionListener {

    private VentanaPrincipal nucleo;

    private String opcion;
    private List<ActionListener> actionListeners; // Lista de observadores
    private JToggleButton botonLinea;
    private JToggleButton botonAgregacion;
    private JToggleButton botonComposicion;
    private JToggleButton botonDependencia;
    private JToggleButton botonGeneralizacion;
    private JToggleButton botonRealizacion;
    private JToggleButton botonRectangulo;
    private JToggleButton botonTexto; // Botón para la opción de cuadro de texto

    private Dibujo dibujo; // Referencia al objeto Dibujo

    /**
     * Constructor de la clase BarraHerramientas.
     *
     * @param dibujo Referencia al objeto Dibujo.
     */
    public BarraHerramientas(Dibujo dibujo) {
        this.dibujo = dibujo;
        opcion = "LINEA";
        setFloatable(false);
        actionListeners = new ArrayList<>(); // Inicializar la lista de observadores
        inicializarComponentes();
        actualizarEstadoBotones();
    }

    /**
     * Inicializa los componentes de la barra de herramientas.
     */
    private void inicializarComponentes() {

        botonLinea = new JToggleButton();
        botonLinea.setBackground(Color.white);
        botonLinea.setFocusable(false);

        botonAgregacion = new JToggleButton();
        botonAgregacion.setBackground(Color.white);
        botonAgregacion.setFocusable(false);

        botonComposicion = new JToggleButton();
        botonComposicion.setBackground(Color.white);
        botonComposicion.setFocusable(false);

        botonDependencia = new JToggleButton();
        botonDependencia.setBackground(Color.white);
        botonDependencia.setFocusable(false);

        botonGeneralizacion = new JToggleButton();
        botonGeneralizacion.setBackground(Color.white);
        botonGeneralizacion.setFocusable(false);

        botonRealizacion = new JToggleButton();
        botonRealizacion.setBackground(Color.white);
        botonRealizacion.setFocusable(false);

        botonRectangulo = new JToggleButton();
        botonRectangulo.setBackground(Color.white);
        botonRectangulo.setFocusable(false);

        botonTexto = new JToggleButton(); // Nuevo botón para la opción de cuadro de texto
        botonTexto.setBackground(Color.white);
        botonTexto.setFocusable(false);

        ImageIcon icono = crearImageIcon("asociacion.png");
        Image imagen = icono.getImage();
        Image nuevaImagen = imagen.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono = new ImageIcon(nuevaImagen);
        botonLinea.setIcon(nuevoIcono);

        ImageIcon icono2 = crearImageIcon("agregacion.png");
        Image imagen2 = icono2.getImage();
        Image nuevaImagen2 = imagen2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono2 = new ImageIcon(nuevaImagen2);
        botonAgregacion.setIcon(nuevoIcono2);

        ImageIcon icono3 = crearImageIcon("composicion.png");
        Image imagen3 = icono3.getImage();
        Image nuevaImagen3 = imagen3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono3 = new ImageIcon(nuevaImagen3);
        botonComposicion.setIcon(nuevoIcono3);

        ImageIcon icono4 = crearImageIcon("dependencia.png");
        Image imagen4 = icono4.getImage();
        Image nuevaImagen4 = imagen4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono4 = new ImageIcon(nuevaImagen4);
        botonDependencia.setIcon(nuevoIcono4);

        ImageIcon icono5 = crearImageIcon("generalizacion.jpg");
        Image imagen5 = icono5.getImage();
        Image nuevaImagen5 = imagen5.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono5 = new ImageIcon(nuevaImagen5);
        botonGeneralizacion.setIcon(nuevoIcono5);

        ImageIcon icono6 = crearImageIcon("realizacion.png");
        Image imagen6 = icono6.getImage();
        Image nuevaImagen6 = imagen6.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono6 = new ImageIcon(nuevaImagen6);
        botonRealizacion.setIcon(nuevoIcono6);

        ImageIcon icono7 = crearImageIcon("rectangulo.png");
        Image imagen7 = icono7.getImage();
        Image nuevaImagen7 = imagen7.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono7 = new ImageIcon(nuevaImagen7);
        botonRectangulo.setIcon(nuevoIcono7);

        ImageIcon icono8 = crearImageIcon("texto.png");
        Image imagen8 = icono8.getImage();
        Image nuevaImagen8 = imagen8.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon nuevoIcono8 = new ImageIcon(nuevaImagen8);
        botonTexto.setIcon(nuevoIcono8);

        //botonLinea.setIcon(crearImageIcon("asociacion.png"));
        //botonAgregacion.setIcon(crearImageIcon("agregacion.png"));
        //botonComposicion.setIcon(crearImageIcon("composicion.png"));
        //botonDependencia.setIcon(crearImageIcon("dependencia.png"));
        //botonGeneralizacion.setIcon(crearImageIcon("generalizacion.jpg"));
        //botonRealizacion.setIcon(crearImageIcon("realizacion.png"));
        //botonRectangulo.setIcon(crearImageIcon("rectangulo.png"));
        //botonTexto.setIcon(crearImageIcon("texto.png"));

        botonLinea.addActionListener(this);
        botonAgregacion.addActionListener(this);
        botonComposicion.addActionListener(this);
        botonDependencia.addActionListener(this);
        botonGeneralizacion.addActionListener(this);
        botonRealizacion.addActionListener(this);
        botonRectangulo.addActionListener(this);
        botonTexto.addActionListener(this);


        add(botonLinea);
        add(botonLinea);
        add(botonAgregacion);
        add(botonComposicion);
        add(botonDependencia);
        add(botonGeneralizacion);
        add(botonRealizacion);
        add(botonRectangulo);
        add(botonTexto);

    }
    private void notificarObservadores() {
        ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, opcion);
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(event);
        }
    }

    /**
     * Acción que se ejecuta cuando se presiona un botón de la barra de herramientas.
     *
     * @param e Evento de acción.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonLinea) {
            opcion = "LINEA";
        } else if (e.getSource() == botonRectangulo) {
            opcion = "RECTANGULO";
        } else if (e.getSource() == botonAgregacion) {
            opcion = "AGREGACION";
        } else if (e.getSource() == botonComposicion) {
            opcion = "COMPOSICION";
        } else if (e.getSource() == botonDependencia) {
            opcion = "DEPENDENCIA";
        } else if (e.getSource() == botonGeneralizacion) {
            opcion = "GENERALIZACION";
        } else if (e.getSource() == botonRealizacion) {
            opcion = "REALIZACION";
        } else if (e.getSource() == botonTexto) { // Opción para activar el cuadro de texto
            opcion = "TEXTO";
            dibujo.activarCuadroTexto();
        }
        notificarObservadores(); // Notificar a los observadores que se ha producido un cambio
        actualizarEstadoBotones();
    }

    /**
     * Devuelve la opción seleccionada en la barra de herramientas.
     *
     * @return La opción seleccionada.
     */
    public String getOpcion() {
        return opcion;
    }

    /**
     * Actualiza el estado de los botones de la barra de herramientas.
     */
    private void actualizarEstadoBotones() {
        botonLinea.setSelected(opcion.equals("LINEA"));
        botonRectangulo.setSelected(opcion.equals("RECTANGULO"));
        botonAgregacion.setSelected(opcion.equals("AGREGACION"));
        botonComposicion.setSelected(opcion.equals("COMPOSICION"));
        botonDependencia.setSelected(opcion.equals("DEPENDENCIA"));
        botonGeneralizacion.setSelected(opcion.equals("GENERALIZACION"));
        botonRealizacion.setSelected(opcion.equals("REALIZACION"));
        botonTexto.setSelected(opcion.equals("TEXTO")); // Actualizar estado del botón de cuadro de texto
    }

    /**
     * Crea un objeto ImageIcon a partir de una imagen almacenada en el archivo especificado.
     *
     * @param path La ruta del archivo que contiene la imagen.
     * @return El objeto ImageIcon creado o null si no se pudo crear.
     */
    protected static ImageIcon crearImageIcon(String path) {
        java.net.URL imgURL = BarraHerramientas.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("No se pudo encontrar el archivo: " + path);
            return null;
        }
    }
}
