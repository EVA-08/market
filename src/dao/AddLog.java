package dao;

import entiry.Commodity;

import java.sql.Date;

/**
 * Created by EVA-08 on 2017/7/17.
 */
public class AddLog {
    private Integer marketID;

    public AddLog(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean add(Commodity commodity, String action, Integer count, Date date) {
        String sql = "INSERT log_" + marketID + " (id, action, count," +
                "name, type, production_date, guarantee_period, date, " +
                "supplier, price) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return new DBUtil(DBConnection.getConnection()).execute(sql, commodity.getID(), action,
                count, commodity.getName(), commodity.getType(), commodity.getProductionDate(),
                commodity.getGuaranteePeriod(), date, commodity.getSupplier(), commodity.getPrice());
    }
}
