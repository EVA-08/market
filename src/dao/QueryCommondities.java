package dao;

import entiry.Commodity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/8.
 */
public class QueryCommondities {
    private int marketID;
    private String tableName;
    public QueryCommondities(int marketID) {
        this.marketID = marketID;
        tableName = "commodity_" + String.valueOf(marketID);
    }
    public List<Commodity> query() {
        String sql = "SELECT * FROM " + tableName;
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql);
        return toCommodities(result);
    }

    public List<Commodity> query(String name) {
        String sql = "SELECT * FROM " + tableName + " WHERE type like ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, "%" + name + "%");
        return toCommodities(result);
    }

    public Commodity query(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, id);
        List<Commodity> commodities = toCommodities(result);
        return commodities.get(0);
    }

    private List<Commodity> toCommodities(QueryResult result) {
        List<Commodity> commodities = new ArrayList<>();
        List<Map<String, Object>> table = result.getTable();
        for (int i = 0; i < result.getRows(); i++) {
            Commodity commodity = new Commodity();
            Map<String, Object> row = table.get(i);
            commodity.setCount((Integer)row.get("count"));
            commodity.setID((Integer)row.get("id"));
            commodity.setGuaranteePeriod((String)row.get("guarantee_period"));
            commodity.setName((String)row.get("name"));
            commodity.setPrice((BigDecimal) row.get("price"));
            commodity.setProductionDate((Date) row.get("production_date"));
            commodity.setSupplier((String)row.get("supplier"));
            commodity.setType((String)row.get("type"));
            commodities.add(commodity);
        }
        return commodities;
    }
}
