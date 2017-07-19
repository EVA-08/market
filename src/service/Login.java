package service;

import dao.QueryUserInfo;
import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/6.
 */
//登录
public class Login {
    private String username;
    private String password;
    private HttpSession session;
    public Login(String username, String password, HttpSession session) {
        this.username = username;
        this.password = password;
        this.session = session;
    }
    public boolean login() {
        UserInfo userInfo = new dao.QueryUserInfo().query(username);
        if (userInfo == null || !userInfo.getPassword().equals(password)) {
            return false;
        }
        session.setAttribute("userInfo", userInfo);
        return true;
    }
}
