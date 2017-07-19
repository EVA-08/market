package dao;

import entiry.UserInfo;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateUserInfo {
    private UserInfo userInfo;
    private String oldUsername;
    public UpdateUserInfo(UserInfo userInfo, String oldUsername) {
        this.userInfo = userInfo;
        this.oldUsername = oldUsername;
    }

    public boolean update() {
        String sql = "UPDATE user SET username = ?, name = ?, age = ?, gender = ?," +
                "tel = ?, authority = ?, market_id = ?, password = ? WHERE username = ?";
        return new DBUtil(DBConnection.getConnection()).execute(sql, userInfo.getUsername(),
                userInfo.getName(), userInfo.getAge(), userInfo.getGender(), userInfo.getTel(),
                userInfo.getAuthority(), userInfo.getMarketID(), userInfo.getPassword(), oldUsername);
    }
}
