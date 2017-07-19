package service;

import entiry.Commodity;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by EVA-08 on 2017/7/10.
 */
public class RemoveCommodity {
    private Integer marketID;
    private Integer ID;
    private HttpSession session;
    public RemoveCommodity(Integer marketID, Integer ID, HttpSession session) {
        this.marketID = marketID;
        this.ID = ID;
        this.session = session;
    }

    public void remove() {
        Commodity commodity = new dao.QueryCommondities(marketID).query(ID);
        boolean success0 = new dao.RemoveCommodity(marketID, ID).remove();
        if (!success0) {
            session.setAttribute("removeSucceeded", false);
            return;
        }
        boolean success1 = new dao.AddLog(marketID).add(commodity, "出库",
                commodity.getCount(), Date.valueOf(LocalDate.now()));
        if (success1) {
            session.setAttribute("removeSucceeded", true);
        } else {
            session.setAttribute("removeSucceeded", false);
        }
    }
}
