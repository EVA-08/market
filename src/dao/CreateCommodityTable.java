package dao;

/**
 * Created by EVA-08 on 2017/7/12.
 */
public class CreateCommodityTable {
    private Integer marketID;

    public CreateCommodityTable(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean create() {
        String sql = "CREATE TABLE commodity_" + marketID + "(id INT, name VARCHAR(45) NOT NULL, " +
                "type VARCHAR(45) NOT NULL, price DECIMAL(10, 2) NOT NULL, count INT NOT NULL, " +
                "production_date DATE NOT NULL, guarantee_period VARCHAR(45) NOT NULL, " +
                "supplier VARCHAR(45) NOT NULL, market_id int NOT NULL, FOREIGN KEY(market_id) REFERENCES market(id) ON DELETE " +
                "RESTRICT ON UPDATE CASCADE, FOREIGN KEY(type) REFERENCES type_" + marketID + "(type) ON DELETE " +
                "RESTRICT ON UPDATE CASCADE, PRIMARY KEY(id))";
        return new DBUtil(DBConnection.getConnection()).execute(sql);
    }
}
