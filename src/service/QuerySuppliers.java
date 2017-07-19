package service;

import entiry.Supplier;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/11.
 */
public class QuerySuppliers {
    private Integer marketID;
    private HttpSession session;
    public QuerySuppliers(Integer marketID, HttpSession session) {
        this.marketID = marketID;
        this.session = session;
    }
    public void query() {
        List<Supplier> commodities = new dao.QuerySuppliers(marketID).query();
        session.setAttribute("suppliers", commodities);
    }
    public void query(String supplier) {
        List<Supplier> commodities = new dao.QuerySuppliers(marketID).query(supplier);
        session.setAttribute("suppliers", commodities);
    }
}
