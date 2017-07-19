package service;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class RemoveType {
    private Integer marketID;
    private String type;
    private HttpSession session;
    public RemoveType(Integer marketID, String type, HttpSession session) {
        this.marketID = marketID;
        this.type = type;
        this.session = session;
    }

    public void remove() {
        boolean success = new dao.RemoveType(marketID, type).remove();
        if (success) {
            session.setAttribute("removeSucceeded", true);
        } else {
            session.setAttribute("removeSucceeded", false);
        }
    }
}
