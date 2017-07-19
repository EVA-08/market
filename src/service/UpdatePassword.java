package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdatePassword {
    private String newPassword;
    private String rePassword;
    private String oldPassword;
    private HttpSession session;
    public UpdatePassword(String oldPassword, String newPassword, String rePassword, HttpSession session) {
        this.rePassword = rePassword;
        this.newPassword = newPassword;
        this.session = session;
        this.oldPassword = oldPassword;
    }

    public void update() {
        UserInfo info = (UserInfo) session.getAttribute("userInfo");
        if (!(oldPassword.equals(info.getPassword()) && newPassword.equals(rePassword))) {
            session.setAttribute("updateSucceeded", false);
            return;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setName(info.getName());
        userInfo.setAge(info.getAge());
        userInfo.setTel(info.getTel());
        userInfo.setGender(info.getGender());
        userInfo.setUsername(info.getUsername());
        userInfo.setPassword(newPassword);
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
