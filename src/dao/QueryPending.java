package dao;

import entiry.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/14.
 */
public class QueryPending {
    public List<UserInfo> query(Integer marketID) {
        String sql = "SELECT * FROM pending WHERE market_id = ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, marketID);
        List<UserInfo> userInfos = new ArrayList<>();
        for (Map<String, Object> row: result.getTable()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername((String)row.get("username"));
            userInfo.setPassword((String)row.get("password"));
            userInfo.setAge((Integer)row.get("age"));
            userInfo.setAuthority((String)row.get("authority"));
            userInfo.setMarketID((Integer)row.get("market_id"));
            userInfo.setName((String)row.get("name"));
            userInfo.setTel((String)row.get("tel"));
            userInfo.setGender((String)row.get("gender"));
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    public UserInfo query(String queryedUsername) {
        String sql = "SELECT * FROM pending " +
                "WHERE username = ?";
        QueryResult result = new DBUtil(DBConnection.getConnection()).query(sql, queryedUsername);
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
