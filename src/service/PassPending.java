package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/15.
 */
//通过审核
public class PassPending {
    private HttpSession session;
    private Integer marketID;
    public PassPending(HttpSession session) {
        this.session = session;
        this.marketID = ((UserInfo)session.getAttribute("userInfo")).getMarketID();
    }

    public void pend(String username) {
        UserInfo userInfo = new dao.QueryPending().query(username);
        boolean success1 = new dao.RemovePending(marketID).remove(username);
        boolean success2 = new dao.AddUser(userInfo).add();
        if (success1 && success2) {
            session.setAttribute("passSucceeded", true);
        } else {
            session.setAttribute("passSucceeded", false);
        }
    }
}
