package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class QueryUserInfo {
    private String username;
    private HttpSession session;

    public QueryUserInfo(String username, HttpSession session) {
        this.username = username;
        this.session = session;
    }

    public void query() {
        session.setAttribute("userInfo", new dao.QueryUserInfo().query(username));
    }
}
