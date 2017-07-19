package dao;

import entiry.UserInfo;

/**
 * Created by EVA-08 on 2017/7/14.
 */
public class AddPending {
    private UserInfo userInfo;
    public AddPending(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean add() {
        String sql = "INSERT pending (username, password, name, tel, age, gender," +
                "market_id, authority) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return new DBUtil(DBConnection.getConnection()).execute(sql, userInfo.getUsername(),
                userInfo.getPassword(), userInfo.getName(), userInfo.getTel(), userInfo.getAge(),
                userInfo.getGender(), userInfo.getMarketID(), userInfo.getAuthority());
    }
}
