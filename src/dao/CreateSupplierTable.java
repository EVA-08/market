package dao;

/**
 * Created by EVA-08 on 2017/7/12.
 */
public class CreateSupplierTable {
    Integer marketID;

    public CreateSupplierTable(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean create() {
        String sql = "CREATE TABLE supplier_" + marketID + " (supplier VARCHAR(45)," +
                "address VARCHAR(45), tel VARCHAR(45), PRIMARY KEY(supplier))";
        return new DBUtil(DBConnection.getConnection()).execute(sql);
    }
}
