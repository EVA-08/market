package service;

import entiry.Market;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class QueryMarkets {
    private HttpSession session;
    public QueryMarkets(HttpSession session) {
        this.session = session;
    }
    public void query() {
        List<Market> markets = new dao.QueryMarkets().query();
        session.setAttribute("markets", markets);
    }
    public void query(String name) {
        List<Market> markets = new dao.QueryMarkets().query(name);
        session.setAttribute("markets", markets);
    }
}
