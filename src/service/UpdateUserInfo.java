package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateUserInfo {
    private String name;
    private Integer age;

    public UpdateUserInfo(String name, Integer age, String gender, String tel, HttpSession session) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.session = session;
    }

    private String gender;
    private String tel;
    private HttpSession session;

    public void update() {
        UserInfo info = (UserInfo) session.getAttribute("userInfo");
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(age);
        userInfo.setTel(tel);
        userInfo.setGender(gender);
        userInfo.setUsername(info.getUsername());
        userInfo.setPassword(info.getPassword());
        userInfo.setMarketID(info.getMarketID());
        userInfo.setAuthority(info.getAuthority());
        boolean success = new dao.UpdateUserInfo(userInfo, info.getUsername()).update();
        if (success) {
            session.setAttribute("updateSucceeded", true);
        } else {
            session.setAttribute("updateSucceeded", false);
        }
    }
}
