package dao;

/**
 * Created by EVA-08 on 2017/7/17.
 */
public class CreateLogTable {
    private Integer marketID;

    public CreateLogTable(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean create() {
        String sql = "CREATE TABLE log_" + marketID + "(id INTEGER NOT NULL, " +
                "action ENUM('进库', '出库') NOT NULL, count INT NOT NULL, " +
                "name VARCHAR(45) NOT NULL, type VARCHAR(45) NOT NULL," +
                "production_date DATE NOT NULL, guarantee_period VARCHAR(45) NOT NULL," +
                "date DATE NOT NULL, supplier VARCHAR(45) NOT NULL," +
                "price DECIMAL(10, 2) NOT NULL)";
        return new DBUtil(DBConnection.getConnection()).execute(sql);
    }
}
