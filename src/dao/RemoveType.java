package dao;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class RemoveType {
    private Integer marketID;
    private String type;
    public RemoveType(Integer marketID, String type) {
        this.marketID = marketID;
        this.type = type;
    }
    public boolean remove() {
        String sql = "DELETE FROM type_" + marketID + " WHERE type = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, type);
    }


}
