package design.persistance.db.query;

import design.persistance.db.TableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Query<T extends TableModel> {

    protected String queryText;

    public Query(String text) {
        this.queryText = text;
    }

    public String getText() {
        return queryText;
    }

    public abstract void InsertParameters(PreparedStatement statement, T model) throws SQLException;

    public abstract ResultSet execute(PreparedStatement statement) throws SQLException;

    public ResultSet apply(PreparedStatement statement, T model) {
        try {
            InsertParameters(statement, model);
            return execute(statement);
        } catch (SQLException e) {
            throw new QueryError(e);
        }
    }
}
