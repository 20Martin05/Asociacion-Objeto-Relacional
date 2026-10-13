package U1;

import U1.gui.VentanaProductos;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaProductos().setVisible(true));
    }
}