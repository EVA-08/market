package dao;

/**
 * Created by EVA-08 on 2017/7/12.
 */
public class CreateTypeTable {
    Integer marketID;

    public CreateTypeTable(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean create() {
        String sql = "CREATE TABLE type_" + marketID + " (type VARCHAR(45)," +
                "PRIMARY KEY(type))";
        return new DBUtil(DBConnection.getConnection()).execute(sql);
    }
}
