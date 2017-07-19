package service;

import dao.AddPending;
import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/14.
 */
public class Register {
    private String username;
    private String password;
    private String tel;
    private String name;
    private Integer marketID;
    private Integer age;
    private String gender;
    private HttpSession session;

    public Register(String username, String password, String tel,
                    String name, Integer marketID, Integer age,
                    String gender, HttpSession session) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.marketID = marketID;
        this.age = age;
        this.gender = gender;
        this.session = session;
    }

    public void register() {
        UserInfo userInfo1 = new dao.QueryUserInfo().query(username);
        UserInfo userInfo2 = new dao.QueryPending().query(username);
        if (userInfo1 != null && userInfo2 != null) {
            session.setAttribute("usernameUsed", true);
            return;
        }
        UserInfo userInfo = new UserInfo(username, password,
                "employee", name, gender, tel, age, marketID);
        if (new AddPending(userInfo).add()) {
            session.setAttribute("registerSucceeded", true);
        } else {
            session.setAttribute("registerSucceeded", false);
        }
    }
}
