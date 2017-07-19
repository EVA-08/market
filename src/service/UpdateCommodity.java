package service;

import entiry.Commodity;
import entiry.UserInfo;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by EVA-08 on 2017/7/10.
 */
public class UpdateCommodity {
    private HttpSession session;
    private Commodity commodity;
    public UpdateCommodity(Integer ID, String name, String type,
                           Integer count, BigDecimal price, String supplier,
                           Date productionDate, String guaranteePeriod, HttpSession session) {
        commodity = new Commodity();
        commodity.setID(ID);
        commodity.setName(name);
        commodity.setType(type);
        commodity.setCount(count);
        commodity.setSupplier(supplier);
        commodity.setPrice(price);
        commodity.setProductionDate(productionDate);
        commodity.setGuaranteePeriod(guaranteePeriod);
        this.session = session;
    }

    public void update() {
        Integer marketID = ((UserInfo)session.getAttribute("userInfo")).getMarketID();
        Commodity commodity = new dao.QueryCommondities(marketID).query(this.commodity.getID());
        boolean success0 = new dao.UpdateCommodity(this.commodity, marketID).update();
        if (!success0) {
            session.setAttribute("updateSucceeded", false);
            return;
        }
        String action;
        Integer count = this.commodity.getCount() - commodity.getCount();
        if (count > 0) {
            action = "进库";
        } else {
            action = "出库";
        }
        count = Math.abs(count);
        boolean success1 = new dao.AddLog(marketID).add(commodity, action,
                count, Date.valueOf(LocalDate.now()));
        if (success1) {
            session.setAttribute("updateSucceeded", true);
        } else {
            session.setAttribute("updateSucceeded", false);
        }
    }
}
