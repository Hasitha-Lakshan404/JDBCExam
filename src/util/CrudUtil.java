package util;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author : Hasitha Lakshan
 * Project :JDBCExam
 * Date :7/12/2022
 * Time :9:45 AM
 */

public class CrudUtil {
    public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            stm.setObject(i + 1, params[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) stm.executeQuery();
        } else {
            return (T) ((Boolean) (stm.executeUpdate() > 0));
        }

    }
}
