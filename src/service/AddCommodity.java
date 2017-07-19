package service;

import entiry.Commodity;
import entiry.UserInfo;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by EVA-08 on 2017/7/10.
 */
//增加商品
public class AddCommodity {
    private HttpSession session;
    private Commodity commodity;
    public AddCommodity(Integer ID, String name, String type,
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

    public void add() {
        Integer marketID = ((UserInfo)session.getAttribute("userInfo")).getMarketID();
        boolean success0 = new dao.AddCommodity(commodity, marketID).add();
        if (!success0) {
            session.setAttribute("addSucceeded", false);
            return;
        }
        boolean success1 = new dao.AddLog(marketID).add(commodity, "进库",
                commodity.getCount(), Date.valueOf(LocalDate.now()));
        if (success1) {
            session.setAttribute("addSucceeded", true);
        } else {
            session.setAttribute("addSucceeded", false);
        }
    }
}
