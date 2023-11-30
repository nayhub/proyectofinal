import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que representa una barra inferior.
 */
public class BarraInferior extends JPanel implements ActionListener {

    private JLabel etiquetaColor;
    private JButton botonColor;

    private Color[] coloresDisponibles = {Color.black, Color.blue, Color.green, Color.pink, Color.cyan, Color.red, Color.YELLOW,
            Color.orange, Color.magenta};

    /**
     * Constructor por defecto.
     */
    public BarraInferior() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        etiquetaColor = new JLabel("Color");
        botonColor = new JButton();
        botonColor.setBackground(coloresDisponibles[0]);
        botonColor.addActionListener(this);
        add(etiquetaColor);
        add(botonColor);
    }

    public void actionPerformed(ActionEvent e) {
        String[] nombresColores = {"Negro", "Azul", "Verde", "Rosa", "Cian", "Rojo", "Amarillo", "Naranja", "Magenta"};
        Color[] coloresDisponibles = {Color.BLACK, Color.BLUE, Color.GREEN, Color.PINK, Color.CYAN, Color.RED, Color.YELLOW,
                Color.ORANGE, Color.MAGENTA};

        JComboBox<String> comboBox = new JComboBox<>(nombresColores);
        comboBox.setSelectedIndex(0);

        int opcion = JOptionPane.showConfirmDialog(null, comboBox, "Seleccione un color", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            Color colorSeleccionado = coloresDisponibles[comboBox.getSelectedIndex()];
            botonColor.setBackground(colorSeleccionado);
            botonColor.setText(getNombreColor(colorSeleccionado));
            botonColor.setFocusable(false);
        }
    }
    private String getNombreColor(Color color) {
        String[] nombresColores = {"Negro", "Azul", "Verde", "Rosa", "Cian", "Rojo", "Amarillo", "Naranja", "Magenta"};
        int[] codigosRGB = {Color.BLACK.getRGB(), Color.BLUE.getRGB(), Color.GREEN.getRGB(), Color.PINK.getRGB(),
                Color.CYAN.getRGB(), Color.RED.getRGB(), Color.YELLOW.getRGB(), Color.ORANGE.getRGB(), Color.MAGENTA.getRGB()};

        for (int i = 0; i < codigosRGB.length; i++) {
            if (color.getRGB() == codigosRGB[i]) {
                return nombresColores[i];
            }
        }

        return "Desconocido";
    }

    /**
     * Devuelve el botón de color.
     *
     * @return El botón de color.
     */
    public JButton getBotonColor() {
        return botonColor;
    }
}
