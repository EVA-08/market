package dao;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class AddType {
    String type;
    Integer marketID;
    public AddType(String type, Integer marketID) {
        this.type = type;
        this.marketID = marketID;
    }

    public boolean add() {
        String sql = "INSERT type_" + marketID + " (type) values (?)";
        return new DBUtil(DBConnection.getConnection()).execute(sql, type);
    }
}
