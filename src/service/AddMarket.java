package service;

import dao.*;

import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Created by EVA-08 on 2017/7/12.
 */

//增加一个超市
public class AddMarket {
    private Integer marketID;
    private HttpSession session;
    private String tel;
    private String address;
    private String name;

    public AddMarket(Integer marketID, String tel, String address,
                     String name, HttpSession session) {
        this.marketID = marketID;
        this.session = session;
        this.tel = tel;
        this.address = address;
        this.name = name;
    }

    public void add() {
        boolean success0 = new dao.AddMarket(name, address, tel, marketID).create();
        if (!success0) {
            session.setAttribute("createSucceeded", false);
            return;
        }
        boolean success1 = new CreateSupplierTable(marketID).create();
        boolean success2 = new CreateTypeTable(marketID).create();
        boolean success3 = new CreateCommodityTable(marketID).create();
        boolean success4 = new CreateLogTable(marketID).create();
        if (success1 && success2 && success3 && success4) {
            session.setAttribute("createSucceeded", true);
        } else {
            session.setAttribute("createSucceeded", false);
        }
    }
}
