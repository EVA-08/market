package dao;

import entiry.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class QueryType {
    private Integer marketID;
    public QueryType(Integer marketID) {
        this.marketID = marketID;
    }
    public List<Type> query() {
        String sql = "SELECT * FROM type_" + marketID;
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql);
        return toTypes(result);
    }

    public List<Type> query(String type) {
        String sql = "SELECT * FROM type_" + marketID + " WHERE type LIKE ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, "%" +type + "%");
        return toTypes(result);
    }

    private List<Type> toTypes(QueryResult result) {
        List<Type> types = new ArrayList<>();
        for (Map<String, Object> row: result.getTable()) {
            Type type = new Type();
            type.setType((String)row.get("type"));
            types.add(type);
        }
        return types;
    }

}
