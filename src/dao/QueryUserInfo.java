package dao;

import entiry.UserInfo;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/7.
 */
//查询用户名和密码，返回是否存在包含用户名，名字，权限的用户信息
public class QueryUserInfo {
    private Connection connection = DBConnection.getConnection();
    public UserInfo query(String queryedUsername) {
        String sql = "SELECT * FROM user " +
                "WHERE username = ?";
        QueryResult result = new DBUtil(connection).query(sql, queryedUsername);
        if (result.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> table = result.getTable();
        Map<String, Object> row = table.get(0);
        UserInfo userInfo = new UserInfo();
        String username = (String)row.get("username");
        userInfo.setUsername(username);
        String name = (String)row.get("name");
        userInfo.setName(name);
        String password = (String)row.get("password");
        userInfo.setPassword(password);
        String authority = (String)row.get("authority");
        userInfo.setAuthority(authority);
        Integer marketID = (Integer)row.get("market_id");
        userInfo.setMarketID(marketID);
        String gender = (String)row.get("gender");
        userInfo.setGender(gender);
        Integer age = (Integer)row.get("age");
        userInfo.setAge(age);
        String tel = (String)row.get("tel");
        userInfo.setTel(tel);
        return userInfo;
    }
}
