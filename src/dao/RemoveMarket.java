package dao;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class RemoveMarket {
    private Integer marketID;

    public RemoveMarket(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean remove() {
        String sql = "DELETE FROM market WHERE id = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, marketID);
    }
}
