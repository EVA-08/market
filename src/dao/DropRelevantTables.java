package dao;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class DropRelevantTables {
    private Integer marketID;
    public DropRelevantTables(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean drop() {
        String sql = "DROP TABLE commodity_" + marketID;
        if (!new DBUtil(DBConnection.getConnection()).execute(sql)) {
            return false;
        }
        sql = "DROP TABLE supplier_" + marketID;
        if (!new DBUtil(DBConnection.getConnection()).execute(sql)) {
            return false;
        }

        sql = "DROP TABLE type_" + marketID;
        if (!new DBUtil(DBConnection.getConnection()).execute(sql)) {
            return false;
        }

        sql = "DROP TABLE log_" + marketID;
        if (!new DBUtil(DBConnection.getConnection()).execute(sql)) {
            return false;
        }
        return true;
    }
}
