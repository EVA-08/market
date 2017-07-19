package dao;

import entiry.Commodity;

/**
 * Created by EVA-08 on 2017/7/10.
 */
public class RemoveCommodity {
    private Integer marketID;
    private Integer ID;
    public RemoveCommodity(Integer marketID, Integer ID) {
        this.marketID = marketID;
        this.ID = ID;
    }

    public boolean remove() {
        String sql = "DELETE FROM commodity_" + marketID + " WHERE ID = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, ID);
    }
}
