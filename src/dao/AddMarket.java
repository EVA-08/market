package dao;

/**
 * Created by EVA-08 on 2017/7/12.
 */
public class AddMarket {
    private String name;
    private String address;
    private String tel;
    private Integer marketID;


    public AddMarket(String name, String address, String tel, Integer marketID) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.marketID = marketID;
    }

    public boolean create() {
        String sql = "INSERT market (id, name, address, tel) VALUES(?, ?, ?, ?)";
        return new DBUtil(DBConnection.getConnection()).execute(
                sql, marketID, name, address, tel);
    }
}
