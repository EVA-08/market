package dao;

import entiry.UserInfo;

/**
 * Created by EVA-08 on 2017/7/14.
 */
public class AddUser {
    private UserInfo userInfo;
    public AddUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean add() {
        String sql = "INSERT user(username, password, tel, " +
                "authority, name, age, gender, market_id) values (" +
                "?, ?, ?, ?, ?, ?, ?, ?)";
        return new DBUtil(DBConnection.getConnection()).execute(sql, userInfo.getUsername(),
                userInfo.getPassword(), userInfo.getTel(), userInfo.getAuthority(),
                userInfo.getName(), userInfo.getAge(), userInfo.getGender(),
                userInfo.getMarketID());
    }
}
