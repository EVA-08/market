package dao;

import entiry.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class QuerySuppliers {
    private Integer marketID;
    public QuerySuppliers(Integer marketID) {
        this.marketID = marketID;
    }
    public List<Supplier> query() {
        String sql = "SELECT * FROM supplier_" + marketID;
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql);
        return toSuppliers(result);
    }

    public List<Supplier> query(String supplier) {
        String sql = "SELECT * FROM supplier_" + marketID + " WHERE supplier LIKE ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, "%" + supplier + "%");
        return toSuppliers(result);
    }

    private List<Supplier> toSuppliers(QueryResult result) {
        List<Supplier> suppliers = new ArrayList<>();
        for (Map<String, Object> row: result.getTable()) {
            Supplier supplier = new Supplier();
            supplier.setSupplier((String)row.get("supplier"));
            supplier.setAddress((String)row.get("address"));
            supplier.setTel((String)row.get("tel"));
            suppliers.add(supplier);
        }
        return suppliers;
    }

}
