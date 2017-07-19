package dao;

import entiry.Supplier;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateSupplier {
    private Supplier supplier;
    private Integer marketID;
    private String oldSupplier;
    public UpdateSupplier(Supplier supplier, String oldSupplier, Integer marketID) {

        this.supplier = supplier;
        this.marketID = marketID;
        this.oldSupplier = oldSupplier;
    }

    public boolean update() {
        String sql = "UPDATE supplier_" + marketID + " SET supplier = ?, " +
                "address = ?, tel = ? WHERE supplier = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, supplier.getSupplier(),
                supplier.getAddress(), supplier.getTel(), oldSupplier);
    }
}
