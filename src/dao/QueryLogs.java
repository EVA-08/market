package dao;

import entiry.Action;
import entiry.Commodity;
import entiry.Log;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/16.
 */
public class QueryLogs {
    private Integer marketID;

    public QueryLogs(Integer marketID) {
        this.marketID = marketID;
    }

    public List<Log> query(Date startDate, Date endDate) {
        String sql = "SELECT * FROM log_" + marketID + " WHERE date BETWEEN ? AND ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, startDate, endDate);
        List<Log> logs = new ArrayList<>();
        for (Map<String, Object> row: result.getTable()) {
            Log log = new Log();
            Commodity commodity = new Commodity();
            commodity.setCount((Integer)row.get("count"));
            commodity.setID((Integer)row.get("id"));
            commodity.setGuaranteePeriod((String)row.get("guarantee_period"));
            commodity.setName((String)row.get("name"));
            commodity.setPrice((BigDecimal) row.get("price"));
            commodity.setProductionDate((Date) row.get("production_date"));
            commodity.setSupplier((String)row.get("supplier"));
            commodity.setType((String)row.get("type"));
            log.setCommodity(commodity);
            Action action = new Action();
            action.setDate((Date)row.get("date"));
            action.setAction((String)row.get("action"));
            log.setAction(action);
            logs.add(log);
        }
        return logs;
    }
}
