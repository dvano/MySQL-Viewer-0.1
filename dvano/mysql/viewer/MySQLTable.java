package dvano.mysql.viewer;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dvano
 */
public final class MySQLTable {

    private MySQLTable() {
    }

    public static DefaultTableModel buildMySQLTable(ResultSet rs) throws SQLException {
        ResultSetMetaData data = rs.getMetaData();
        Vector<Object> columns = new Vector<>();
        Vector<Vector<Object>> values = new Vector<>();
        int maxColumns = data.getColumnCount();
        for (int i = 1; i <= maxColumns; i++) {
            columns.add(data.getColumnName(i));
        }
        while (rs.next()) {
            Vector<Object> value = new Vector<>();
            for (int i = 1; i <= maxColumns; i++) {
                value.add(rs.getObject(i));
            }
            values.add(value);
        }

        return new DefaultTableModel(values, columns);
    }
}
