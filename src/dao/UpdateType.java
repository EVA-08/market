package dao;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateType {
    private Integer marketID;
    private String type;
    private String oldType;
    public UpdateType(Integer marketID, String type, String oldType) {
        this.marketID = marketID;
        this.type = type;
        this.oldType = oldType;
    }

    public boolean update() {
        String sql = "UPDATE type_" + marketID + " SET type = ? WHERE type = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, type, oldType);
    }
}
