package dvano.mysql.viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author dvano
 */
public final class SimpleFrame extends JFrame {

    SimpleFrame() {
        this.setTitle("MySQL-Viewer-0.1");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    MySQLConnector.disconnect();
                } catch (SQLException ex) {
                    Logger.getLogger(SimpleFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JPanel panel = new JPanel();

        JTable table = new JTable();
        table.setEnabled(false);
        JScrollPane pane = new JScrollPane(table);

        this.add(pane, BorderLayout.CENTER);

        JTextField url = new JTextField("jdbc:mysql://localhost/NAME", 18);
        JTextField user = new JTextField("USER", 4);
        JPasswordField password = new JPasswordField(7);
        JTextField sql = new JTextField("select * from NAME", 12);

        url.setToolTipText("URL");
        user.setToolTipText("USER");
        password.setToolTipText("PASSWORD");
        sql.setToolTipText("SQL");

        JButton connect = new JButton("connect");
        connect.addActionListener((ActionEvent e) -> {
            try {
                MySQLConnector.disconnect();
                MySQLConnector.connect(url.getText(), user.getText(), String.valueOf(password.getPassword(), 0, password.getPassword().length));
                try (ResultSet rs = MySQLConnector.getResultSet(sql.getText())) {
                    table.setModel(MySQLTable.buildMySQLTable(rs));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SimpleFrame.class.getName()).log(Level.SEVERE, null, ex);

                StringBuilder builder = new StringBuilder();
                builder.append(ex.getMessage()).append("\n");

                for (StackTraceElement s : ex.getStackTrace()) {
                    builder.append(s.toString()).append("\n");
                }

                ErrorFrame frame = new ErrorFrame(builder.toString());
                frame.setVisible(true);
            }
        });

        panel.add(url);
        panel.add(user);
        panel.add(password);
        panel.add(sql);
        panel.add(connect);

        this.add(panel, BorderLayout.SOUTH);

        JPanel panel2 = new JPanel();

        JTextField sF = new JTextField(14);
        JButton search = new JButton("search");
        search.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < table.getColumnCount(); i++) {
                for (int j = 0; j < table.getRowCount(); j++) {
                    String value = String.valueOf(table.getValueAt(j, i));

                    if (sF.getText().equals(value)) {
                        table.getSelectionModel().setSelectionInterval(j, j);
                    }
                }
            }
        });

        sF.setToolTipText("search field");

        panel2.add(sF);
        panel2.add(search);

        this.add(panel2, BorderLayout.NORTH);
    }
}
