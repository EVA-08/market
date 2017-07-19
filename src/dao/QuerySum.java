package dao;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by EVA-08 on 2017/7/16.
 */
public class QuerySum {
    private Integer marketID;
    public QuerySum(Integer marketID) {
        this.marketID = marketID;
    }
    public BigDecimal query(Date startDate, Date endDate, String action) {
        String sql = "SELECT sum(price) as sum FROM log_" + marketID + " WHERE " +
                "`action` = \"" + action + "\" AND `date` BETWEEN ? AND ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, startDate, endDate);

        return result.getTable().get(0).get("sum") == null ? BigDecimal.ZERO :
                (BigDecimal)result.getTable().get(0).get("sum");
    }
}
