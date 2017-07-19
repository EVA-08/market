package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/15.
 */
//拒绝通过审核
public class RefusePending {
    private HttpSession session;
    private Integer marketID;
    public RefusePending(HttpSession session) {
        this.session = session;
        this.marketID = ((UserInfo)session.getAttribute("userInfo")).getMarketID();
    }

    public void refuse(String username) {
        boolean success = new dao.RemovePending(marketID).remove(username);
        if (success) {
            session.setAttribute("refuseSucceeded", true);
        } else {
            session.setAttribute("refuseSucceeded", false);
        }
    }
}
