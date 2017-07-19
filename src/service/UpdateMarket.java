package service;

import entiry.Market;

import javax.servlet.http.HttpSession;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class UpdateMarket {
    private String name;
    private String address;
    private String tel;
    private Integer marketID;
    private HttpSession session;

    public UpdateMarket(String name, String address, String tel, Integer marketID, HttpSession session) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.marketID = marketID;
        this.session = session;
    }

    public void update() {
        Market market = new Market();
        market.setName(name);
        market.setMarketID(marketID);
        market.setTel(tel);
        market.setAddress(address);
        if (new dao.UpdateMarket(market).update()) {
            session.setAttribute("updateSucceeded", true);
        } else {
            session.setAttribute("updateSucceeded", false);
        }
    }

}
