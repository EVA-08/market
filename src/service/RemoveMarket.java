package service;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class RemoveMarket {
    private Integer marketID;
    private HttpSession session;
    public RemoveMarket(Integer marketID, HttpSession session) {
        this.marketID = marketID;
        this.session = session;
    }

    public void remove() {
        if (new dao.RemoveMarket(marketID).remove() &&
                new dao.DropRelevantTables(marketID).drop()) {
            session.setAttribute("removeSucceeded", true);
        } else{
            session.setAttribute("removeSucceeded", false);
        }
    }
}
