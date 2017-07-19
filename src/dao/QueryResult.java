package dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by EVA-08 on 2017/7/7.
 */
class QueryResult {
    private List<Map<String, Object>> table;
    private Set<String> columnNames;

    QueryResult(){}

    private int rows;

    List<Map<String, Object>> getTable() {
        return table;
    }

    void setTable(List<Map<String, Object>> table) {
        this.table = table;
    }

    void setColumnNames(Set<String> columnNames) {
        this.columnNames = columnNames;
    }

    public int getRows() {
        return rows;
    }

    void setRows(int rows) {
        this.rows = rows;
    }

    int getColumns() {
        return columns;
    }

    void setColumns(int columns) {
        this.columns = columns;
    }

    private int columns;
    boolean isEmpty() {
        if (rows == 0 || columns == 0) {
            return true;
        } else {
            return false;
        }
    }
    Set<String> getColumnNames() {
        return columnNames;
    }
}
