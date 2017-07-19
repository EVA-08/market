package service;

import dao.QueryCommondities;
import entiry.Commodity;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/8.
 */

public class QueryCommodities {
    private int marketID;
    private HttpSession session;
    public QueryCommodities(int marketID, HttpSession session) {
        this.marketID = marketID;
        this.session = session;
    }
    public void query() {
        List<Commodity> commodities = new dao.QueryCommondities(marketID).query();
        session.setAttribute("commodities", commodities);
    }
    public void query(String name) {
        List<Commodity> commodities = new dao.QueryCommondities(marketID).query(name);
        session.setAttribute("commodities", commodities);
    }
}
