package service;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class UpdateType {
    private Integer marketID;
    private String type;
    private String oldType;
    private HttpSession session;
    public UpdateType(Integer marketID, String type, String oldType, HttpSession session) {
        this.marketID = marketID;
        this.type = type;
        this.oldType = oldType;
        this.session = session;
    }

    public void update() {
        boolean success = new dao.UpdateType(marketID, type, oldType).update();
        if (success) {
            session.setAttribute("updateSucceeded", true);
        } else {
            session.setAttribute("updateSucceeded", false);
        }
    }
}
