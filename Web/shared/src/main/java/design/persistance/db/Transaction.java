package design.persistance.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {
    private Connection connection;

    public Transaction(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement CreatePreparedStatement(String queryText) throws SQLException {
        return connection.prepareStatement(queryText);
    }

    public void End() throws SQLException {
        this.connection.close();
    }

    @Override
    public void close() throws SQLException {
        End();
    }
}
