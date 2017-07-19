package dao;

import entiry.Commodity;

/**
 * Created by EVA-08 on 2017/7/10.
 */
public class AddCommodity {
    private Commodity commodity;
    Integer marketID;
    public AddCommodity(Commodity commodity, Integer marketID) {
        this.commodity = commodity;
        this.marketID = marketID;
    }
    public boolean add() {
        String sql = "INSERT commodity_" + marketID + " (id, name, type, count, price, " +
                "supplier, production_date, guarantee_period) values (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean success = new DBUtil(DBConnection.getConnection()).execute(sql, commodity.getID(),
                commodity.getName(), commodity.getType(), commodity.getCount(), commodity.getPrice(),
                commodity.getSupplier(), commodity.getProductionDate(), commodity.getGuaranteePeriod());
        return success;
    }
}
