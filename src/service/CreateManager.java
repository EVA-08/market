package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/14.
 */
//创建经理
public class CreateManager {
    private String username;
    private String password;
    private Integer marketID;
    private HttpSession session;

    public CreateManager(String username, String password, Integer marketID, HttpSession session) {
        this.username = username;
        this.password = password;
        this.marketID = marketID;
        this.session = session;
    }

    public void create() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setAuthority("manager");
        userInfo.setMarketID(marketID);
        if (new dao.AddUser(userInfo).add()) {
            session.setAttribute("addSucceeded", true);
        } else {
            session.setAttribute("addSucceeded", false);
        }
    }

}
