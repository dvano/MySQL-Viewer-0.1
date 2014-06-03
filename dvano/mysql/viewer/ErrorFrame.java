package dvano.mysql.viewer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author dvano
 */
public final class ErrorFrame extends JFrame {

    ErrorFrame(String message) {
        this.setTitle("Error");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);

        JTextArea area = new JTextArea(message);
        JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scroll);
    }
}
