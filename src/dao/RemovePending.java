package dao;

/**
 * Created by EVA-08 on 2017/7/15.
 */
public class RemovePending {
    private Integer marketID;

    public RemovePending(Integer marketID) {
        this.marketID = marketID;
    }

    public boolean remove(String username) {
        String sql = "DELETE FROM pending WHERE market_id = ? and username = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, marketID, username);
    }
}
