package service;

import entiry.Log;
import entiry.UserInfo;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/16.
 */
public class QueryLogs {
    private HttpSession session;
    private Integer marketID;
    public QueryLogs(HttpSession session) {
        this.session = session;
        marketID = ((UserInfo)session.getAttribute("userInfo")).getMarketID();
    }

    public void query (Date startDate, Date endDate) {
        List<Log> logs = new dao.QueryLogs(marketID).query(startDate, endDate);
        session.setAttribute("logs", logs);
        BigDecimal incomeSum = new dao.QuerySum(marketID).query(startDate, endDate, "出库");
        session.setAttribute("incomeSum", incomeSum);
        BigDecimal expenseSum = new dao.QuerySum(marketID).query(startDate, endDate, "进库");
        session.setAttribute("expenseSum", expenseSum);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
    }
}
