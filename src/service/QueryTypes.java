package service;

import entiry.Type;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class QueryTypes {
    private HttpSession session;
    private Integer marketID;
    public QueryTypes(Integer marketID, HttpSession session) {
        this.marketID = marketID;
        this.session = session;
    }

    public void query() {
        List<Type> types = new dao.QueryType(marketID).query();
        session.setAttribute("types", types);
    }

    public void query(String type) {
        List<Type> types = new dao.QueryType(marketID).query(type);
        session.setAttribute("types", types);
    }
}
