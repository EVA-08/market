package dao;

import entiry.Commodity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by EVA-08 on 2017/7/10.
 */
public class UpdateCommodity {
    private Commodity commodity;
    Integer marketID;
    public UpdateCommodity(Commodity commodity, Integer marketID) {
        this.commodity = commodity;
        this.marketID = marketID;
    }
    public boolean update() {
        String sql = "UPDATE commodity_" + marketID + " SET type = ?, type = ?, count = ?, " +
                "price = ?, supplier = ?, production_date = ?, " +
                "guarantee_period = ? WHERE id = ?";
        boolean success = new DBUtil(DBConnection.getConnection()).execute(sql, commodity.getName(),
                commodity.getType(), commodity.getCount(), commodity.getPrice(),
                commodity.getSupplier(), commodity.getProductionDate(), commodity.getGuaranteePeriod(),
                commodity.getID());
        return success;
    }
}
