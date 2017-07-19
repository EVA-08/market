package service;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
//增加类别
public class AddType {
    private String type;
    private Integer marketID;
    private HttpSession session;
    public AddType(String type, Integer marketID, HttpSession session) {
        this.type = type;
        this.marketID = marketID;
        this.session = session;
    }

    public void add() {
        boolean success = new dao.AddType(type, marketID).add();
        if (success) {
            session.setAttribute("addSucceeded", true);
        } else {
            session.setAttribute("addSucceeded", false);
        }
    }
}
