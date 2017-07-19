package dao;

import entiry.Supplier;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class AddSupplier {
    Supplier supplier;
    Integer marketID;
    public AddSupplier(Supplier supplier, Integer marketID) {
        this.supplier = supplier;
        this.marketID = marketID;
    }

    public boolean add() {
        String sql = "INSERT supplier_" + marketID + " (supplier, address, tel) values (?, ?, ?)";
        return new DBUtil(DBConnection.getConnection()).execute(sql, supplier.getSupplier(),
                supplier.getAddress(), supplier.getTel());
    }
}
