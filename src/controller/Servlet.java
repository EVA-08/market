package controller;

import service.QueryMarkets;
import entiry.UserInfo;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by EVA-08 on 2017/7/6.
 */
@WebServlet(name = "controller.Servlet", urlPatterns = "/server")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String service = request.getParameter("service");
        HttpSession session = request.getSession();
        if ("login".equals(service)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Login login = new Login(username, password, session);
            if (login.login()) {
                response.sendRedirect("main.jsp");
            } else {
                //fail代表账户或密码错误
                response.sendRedirect("login.jsp?login=fail");
            }
            return;
        }

        //
        if ("queryCommodities".equals(service)) {
            String name = request.getParameter("name");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            if (name == null) {
                new QueryCommodities(marketID, session).query();
            } else {
                new QueryCommodities(marketID, session).query(name);
            }
            request.getRequestDispatcher("adminCommodities.jsp").forward(request, response);
        }

        if ("updateCommodity".equals(service)) {
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            Integer ID;
            try {
                ID = Integer.parseInt(request.getParameter("ID"));
            } catch (NumberFormatException e) {
                ID = null;
            }
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            Integer count;
            try {
                count = Integer.parseInt(request.getParameter("count"));
            } catch (NumberFormatException e) {
                count = null;
            }

            BigDecimal price;
            try {
                price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            } catch (NumberFormatException e) {
                price = null;
            }
            String supplier = request.getParameter("supplier");
            Date productionDate;
            try {
                productionDate = Date.valueOf(request.getParameter("productionDate"));
            } catch (IllegalArgumentException e) {
                productionDate = null;
            }
            String guaranteePeriod = request.getParameter("guaranteePeriod");
            new UpdateCommodity(ID, name, type, count, price, supplier, productionDate, guaranteePeriod, session).update();
            new QueryCommodities(marketID, session).query();
            request.getRequestDispatcher("adminCommodities.jsp").forward(request, response);
        }

        if ("addCommodity".equals(service)) {
            Integer ID;
            try {
                ID = Integer.parseInt(request.getParameter("ID"));
            } catch (NumberFormatException e) {
                ID = null;
            }
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            Integer count;
            BigDecimal price;
            try {
                count = Integer.parseInt(request.getParameter("count"));
            } catch (NumberFormatException e) {
                count = null;
            }
            try {
                price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
            } catch (NumberFormatException e) {
                price = null;
            }
            String supplier = request.getParameter("supplier");
            Date productionDate;
            try {
                productionDate = Date.valueOf(request.getParameter("productionDate"));
            } catch (IllegalArgumentException e) {
                productionDate = null;
            }
            String guaranteePeriod = request.getParameter("guaranteePeriod");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new AddCommodity(ID, name, type, count, price, supplier, productionDate, guaranteePeriod, session).add();
            new QueryCommodities(marketID, session).query();
            request.getRequestDispatcher("adminCommodities.jsp").forward(request, response);
        }

        if ("removeCommodity".equals(service)) {
            Integer ID;
            try {
                ID = Integer.parseInt(request.getParameter("ID"));
            } catch (NumberFormatException e) {
                ID = null;
            }
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new RemoveCommodity(marketID, ID, session).remove();
            new QueryCommodities(marketID, session).query();
            request.getRequestDispatcher("adminCommodities.jsp").forward(request, response);
        }

        if ("queryTypes".equals(service)) {
            String type = request.getParameter("type");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            if (type == null) {
                new QueryTypes(marketID, session).query();
            } else {
                new QueryTypes(marketID, session).query(type);
            }
            request.getRequestDispatcher("adminTypes.jsp").forward(request, response);
        }

        if ("addType".equals(service)) {
            String type = request.getParameter("type");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new AddType(type, marketID, session).add();
            new QueryTypes(marketID, session).query();
            request.getRequestDispatcher("adminTypes.jsp").forward(request, response);
        }

        if ("updateType".equals(service)) {
            String type = request.getParameter("type");
            String oldType = request.getParameter("oldType");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new UpdateType(marketID, type, oldType, session).update();
            new QueryTypes(marketID, session).query();
            request.getRequestDispatcher("adminTypes.jsp").forward(request, response);
        }

        if ("removeType".equals(service)) {
            String type = request.getParameter("type");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new RemoveType(marketID, type, session).remove();
            new QueryTypes(marketID, session).query();
            request.getRequestDispatcher("adminTypes.jsp").forward(request, response);
        }

        if ("addSupplier".equals(service)) {
            String supplier = request.getParameter("supplier");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new AddSupplier(supplier, address, tel, marketID, session).add();
            new QuerySuppliers(marketID, session).query();
            request.getRequestDispatcher("adminSuppliers.jsp").forward(request, response);
        }

        if ("querySuppliers".equals(service)) {
            String supplier = request.getParameter("supplier");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            if (supplier == null) {
                new QuerySuppliers(marketID, session).query();
            } else {
                new QuerySuppliers(marketID, session).query(supplier);
            }
            request.getRequestDispatcher("adminSuppliers.jsp").forward(request, response);

        }

        if ("updateSupplier".equals(service)) {
            String supplier = request.getParameter("supplier");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            String oldSupplier = request.getParameter("oldSupplier");
            Integer marketID = ((UserInfo) session.getAttribute("userInfo")).getMarketID();
            new UpdateSupplier(supplier, address, tel, oldSupplier, marketID, session).update();
            new QuerySuppliers(marketID, session).query();
            request.getRequestDispatcher("adminSuppliers.jsp").forward(request, response);
        }

        if ("queryUserInfo".equals(service)) {
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            new QueryUserInfo(userInfo.getUsername(), session).query();
            request.getRequestDispatcher("adminUserInfo.jsp").forward(request, response);
        }

        if ("updateUserInfo".equals(service)) {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            Integer age;
            try {
                age = Integer.parseInt(request.getParameter("age"));
            } catch (NumberFormatException e) {
                age = null;
            }
            String tel = request.getParameter("tel");
            new UpdateUserInfo(name, age, gender, tel, session).update();
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            new QueryUserInfo(userInfo.getUsername(), session).query();
            request.getRequestDispatcher("adminUserInfo.jsp").forward(request, response);
        }

        if ("updatePassword".equals(service)) {
            String oldPassword = request.getParameter("oldPassword");
            String rePassword = request.getParameter("rePassword");
            String newPassword = request.getParameter("newPassword");
            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            new UpdatePassword(oldPassword, newPassword, rePassword, session).update();
            new QueryUserInfo(userInfo.getUsername(), session).query();
            request.getRequestDispatcher("adminUserInfo.jsp").forward(request, response);
        }

        if ("queryMarkets".equals(service)) {
            String name = request.getParameter("name");
            if (name != null) {
                new QueryMarkets(session).query(name);
            } else {
                new QueryMarkets(session).query();
            }
            request.getRequestDispatcher("adminMarkets.jsp").forward(request, response);
        }

        if ("addMarket".equals(service)) {
            Integer marketID;
            try {
                marketID = Integer.parseInt(request.getParameter("marketID"));
            } catch (NumberFormatException e) {
                session.setAttribute("addSucceeded", false);
                return;
            }
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            new AddMarket(marketID, tel, address, name, session).add();
            new QueryMarkets(session).query();
            request.getRequestDispatcher("adminMarkets.jsp").forward(request, response);
        }

        if ("updateMarket".equals(service)) {
            Integer marketID;
            try {
                marketID = Integer.parseInt(request.getParameter("marketID"));
            } catch (NumberFormatException e) {
                marketID = null;
            }
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String tel = request.getParameter("tel");
            new UpdateMarket(name, address, tel, marketID, session).update();
            new QueryMarkets(session).query();
            request.getRequestDispatcher("adminMarkets.jsp").forward(request, response);
        }

        if ("removeMarket".equals(service)) {
            Integer marketID;
            try {
                marketID = Integer.parseInt(request.getParameter("marketID"));
            } catch (NumberFormatException e) {
                marketID = null;
            }
            new RemoveMarket(marketID, session).remove();
            new QueryMarkets(session).query();
            request.getRequestDispatcher("adminMarkets.jsp").forward(request, response);
        }

        if ("createManager".equals(service)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Integer marketID;
            try {
                marketID = Integer.parseInt(request.getParameter("marketID"));
            } catch (NumberFormatException e) {
                marketID = null;
            }
            new CreateManager(username, password, marketID, session).create();
            request.getRequestDispatcher("createManager.jsp").forward(request, response);
        }

        if ("register".equals(service)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            String gender = request.getParameter("gender");
            Integer age;
            if ("".equals(request.getParameter("age"))) {
                age = null;
            } else {
                age = Integer.parseInt(request.getParameter("age"));
            }
            Integer marketID;
            if ("".equals(request.getParameter("marketID"))) {
                marketID = null;
            } else {
                marketID = Integer.parseInt(request.getParameter("marketID"));
            }
            new Register(username, password, tel, name, marketID, age,
                    gender, session).register();
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

        if ("queryPending".equals(service)) {
            new QueryPending(session).query();
            request.getRequestDispatcher("approval.jsp").forward(request, response);
        }

        if ("passPending".equals(service)) {
            String username = request.getParameter("username");
            new PassPending(session).pend(username);
            new QueryPending(session).query();
            request.getRequestDispatcher("approval.jsp").forward(request, response);
        }

        if ("refusePending".equals(service)) {
            String username = request.getParameter("username");
            new RefusePending(session).refuse(username);
            new QueryPending(session).query();
            request.getRequestDispatcher("approval.jsp").forward(request, response);
        }

        //startDate 默认为当月1号，endDate默认为当月最后一天
        if ("queryLogs".equals(service)) {
            LocalDate now = LocalDate.now();
            Date startDate;
            if (request.getParameter("startDate") == null) {
                startDate = Date.valueOf(now.minusDays(now.getDayOfMonth()).plusDays(1));
            } else {
                try {
                    startDate = Date.valueOf(request.getParameter("startDate"));
                } catch (IllegalArgumentException e) {
                    startDate = null;
                }

            }

            Date endDate;
            if (request.getParameter("endDate") == null) {
                endDate = Date.valueOf(now.minusDays(now.getDayOfMonth()).plusMonths(1));
            } else {
                try {
                    endDate = Date.valueOf(request.getParameter("endDate"));
                } catch (IllegalArgumentException e) {
                    endDate = null;
                }
            }
            new QueryLogs(session).query(startDate, endDate);
            request.getRequestDispatcher("logs.jsp").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }
}
