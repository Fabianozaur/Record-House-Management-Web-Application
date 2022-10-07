package design.persistance.db;

import design.converter.Converter;

import java.sql.ResultSet;

public interface TableResultConverter<T extends TableModel> extends Converter<ResultSet, T> {
}
