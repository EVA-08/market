package dao;

import java.sql.*;
import java.util.*;

/**
 * Created by EVA-08 on 2017/7/6.
 */
public class DBUtil {
    private Connection connection;
    public DBUtil(Connection connection) {
        this.connection = connection;
    }

    public boolean execute(String sql, Object... objects) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= objects.length; i++) {
                statement.setObject(i, objects[i - 1]);
            }
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    QueryResult query(String sql) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            return query(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    QueryResult query(String sql, Object... objects) {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= objects.length; i++) {
                statement.setObject(i, objects[i - 1]);
            }
            return query(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //如果没有相关结果则返回null
    private QueryResult query(PreparedStatement statement) {
        try {
            ResultSet resultset = statement.executeQuery();
            ResultSetMetaData metaData = resultset.getMetaData();
            List<Map<String, Object>> table = new ArrayList<>();
            QueryResult result = new QueryResult();
            result.setTable(table);
            result.setColumns(metaData.getColumnCount());
            Set<String> columnNames = new HashSet<>();
            result.setColumnNames(columnNames);
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                columnNames.add(columnName);
            }
            int rows = 0;
            while (resultset.next()) {
                rows++;
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    row.put(metaData.getColumnName(i), resultset.getObject(i));
                }
                table.add(row);
            }
            result.setRows(rows);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
