package service;

import entiry.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by EVA-08 on 2017/7/15.
 */
//查询待审核列表
public class QueryPending {
    private Integer marketID;
    private HttpSession session;
    public QueryPending(HttpSession session) {
        marketID = (((UserInfo)session.getAttribute("userInfo"))).getMarketID();
        this.session = session;
    }

    public void query() {
        List<UserInfo> userInfos = new dao.QueryPending().query(marketID);
        session.setAttribute("userInfos", userInfos);
    }
}
