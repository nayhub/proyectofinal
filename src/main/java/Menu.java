import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Clase que representa el menú de la aplicación.
 */
public class Menu extends JMenuBar {

    private VentanaPrincipal nucleo;
    private JMenu menuArchivo;
    private JMenuItem itemNuevo;
    private JMenuItem abrir;
    private JMenuItem pizarras;
    private JMenuItem otroItem;
    private JMenuItem itemDeshacer;

    /**
     * Constructor de la clase Menu.
     *
     * @param nucleo Ventana principal de la aplicación.
     */
    public Menu(VentanaPrincipal nucleo) {
        this.nucleo = nucleo;
        inicializarComponentes();
    }

    /**
     * Inicializa los componentes del menú.
     */
    private void inicializarComponentes() {
        menuArchivo = new JMenu("Archivo");

        itemNuevo = new JMenuItem("Nuevo");
        otroItem = new JMenuItem("Guardar");
        abrir = new JMenuItem("Abrir");
        pizarras = new JMenuItem("Proyectos");
        itemNuevo.addActionListener(new NuevoCommand(nucleo));

        itemDeshacer = new JMenuItem("Deshacer");
        itemDeshacer.addActionListener(new DeshacerCommand(nucleo));

        // Archivo
        menuArchivo.add(itemNuevo);
        menuArchivo.add(otroItem);
        menuArchivo.add(abrir);
        menuArchivo.add(pizarras);

        // MENU
        add(menuArchivo);
        add(itemDeshacer);
    }
}

/**
 * Clase Command para el comando "Nuevo".
 */
class NuevoCommand implements ActionListener {
    private VentanaPrincipal nucleo;

    public NuevoCommand(VentanaPrincipal nucleo) {
        this.nucleo = nucleo;
    }

    public void actionPerformed(ActionEvent e) {
        nucleo.reiniciar();
    }
}

/**
 * Clase Command para el comando "Deshacer".
 */
class DeshacerCommand implements ActionListener {
    private VentanaPrincipal nucleo;

    public DeshacerCommand(VentanaPrincipal nucleo) {
        this.nucleo = nucleo;
    }

    public void actionPerformed(ActionEvent e) {
        nucleo.deshacer();
    }
}
