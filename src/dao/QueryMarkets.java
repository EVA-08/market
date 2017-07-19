package dao;

import entiry.Market;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class QueryMarkets {
    public QueryMarkets(){}

    public List<Market> query() {
        String sql = "SELECT * FROM market";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql);
        return toMarkets(result);
    }

    public List<Market> query(String name) {
        String sql = "SELECT * FROM market WHERE name LIKE ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, "%" + name + "%");
        return toMarkets(result);
    }

    private List<Market> toMarkets(QueryResult result) {
        List<Market> markets = new ArrayList<>();
        for (Map<String, Object> row: result.getTable()) {
            Market market = new Market();
            market.setAddress((String)row.get("address"));
            market.setName((String)row.get("name"));
            market.setTel((String)row.get("tel"));
            market.setMarketID((Integer)row.get("id"));
            markets.add(market);
        }
        return markets;
    }
}
