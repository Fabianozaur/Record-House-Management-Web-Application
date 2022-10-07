package design.persistance.db.query;

import design.persistance.db.TableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectQuery<T extends TableModel> extends Query<T> {
    protected SelectQuery(String text) {
        super(text);
    }

    public abstract void InsertParameters(PreparedStatement statement, T model) throws SQLException;

    @Override
    public ResultSet execute(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }
}
