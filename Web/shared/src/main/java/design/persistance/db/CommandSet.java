package design.persistance.db;

import design.persistance.db.query.DeleteQuery;
import design.persistance.db.query.InsertQuery;
import design.persistance.db.query.SelectQuery;
import design.persistance.db.query.UpdateQuery;

public class CommandSet<T extends TableModel> {
    public SelectQuery<T> selectCommand;
    public InsertQuery<T> insertCommand;
    public UpdateQuery<T> updateCommand;
    public DeleteQuery<T> deleteCommand;

    public CommandSet() {
    }

    public CommandSet<T> WithSelect(SelectQuery<T> selectCommand) {
        this.selectCommand = selectCommand;
        return this;
    }

    public CommandSet<T> WithInsert(InsertQuery<T> insertCommand) {
        this.insertCommand = insertCommand;
        return this;
    }

    public CommandSet<T> WithUpdate(UpdateQuery<T> updateCommand) {
        this.updateCommand = updateCommand;
        return this;
    }

    public CommandSet<T> WithDelete(DeleteQuery<T> deleteCommand) {
        this.deleteCommand = deleteCommand;
        return this;
    }
}
