package dvano.mysql.viewer;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author dvano
 */
public final class SimpleProgram {

    private SimpleProgram() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            MySQLConnector.registerDriver();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SimpleProgram.class.getName()).log(Level.SEVERE, null, ex);

            System.exit(1);
        }

        EventQueue.invokeLater(() -> {
            SimpleFrame frame = new SimpleFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
