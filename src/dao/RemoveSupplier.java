package dao;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class RemoveSupplier {
    private Integer marketID;
    private String supplier;
    public RemoveSupplier(Integer marketID, String supplier) {
        this.marketID = marketID;
        this.supplier = supplier;
    }

    public boolean remove() {
        String sql = "DELETE FROM supplier_" + marketID + " WHERE supplier = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, supplier);
    }
}
