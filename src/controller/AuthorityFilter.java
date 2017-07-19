package controller;

import entiry.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EVA-08 on 2017/7/18.
 */

@WebFilter(filterName = "AuthorityFilter", urlPatterns = "/*")
public class AuthorityFilter implements Filter {
    private FilterConfig config;
    //权限表
    private Map<String, String[]> authorityMap = new HashMap<>();
    private String[] employeeAuthorities = {"/", "/main.jsp", "/index.jsp", "/top.jsp", "/left.jsp", "queryCommodities",
            "queryTypes", "querySuppliers", "queryUserInfo", "/login.jsp", "login", "register",
    "/register.jsp", "updateCommodity", "addCommodity", "removeCommodity", "addType",
            "updateType", "removeType", "addSupplier", "updateSupplier", "updateUserInfo", "updatePassword"};
    private String[] adminAuthorities = {"/", "/main.jsp", "/index.jsp", "/top.jsp", "/left.jsp", "queryMarkets",
            "/createManager.jsp", "queryUserInfo", "/login.jsp", "/register.jsp", "login", "register", "updateUserInfo",
            "updatePassword", "addMarket", "updateMarket", "removeMarket", "createManager"};
    private String[] managerAuthorities = {"/", "/main.jsp", "/index.jsp", "/top.jsp", "/left.jsp", "queryCommodities",
            "queryTypes", "querySuppliers", "queryPending", "queryLogs", "queryUserInfo", "/login.jsp", "/register.jsp",
    "login", "register", "updatePassword", "updateUserInfo", "passPending", "refusePending"};

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("AuthorityFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("utf-8");
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println(request.getRequestURI());
        System.out.println();
        String path = request.getRequestURI();
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        String service = request.getParameter("service");
        //如果未登录或者请求登录，则不过滤
        if (userInfo == null || "login".equals(service)) {
            chain.doFilter(req, resp);
            return;
        }
        //若登陆后请求main.jsp则不过滤
        if ("/main.jsp".equals(path)) {
            chain.doFilter(req, resp);
            return;
        }
        String authority = userInfo.getAuthority();
        //根据权限判断是否能访问相应的service服务
        if (path.startsWith("/server")) {
            boolean hasService = false;
            for (String str: authorityMap.get(authority)) {
                if (str.equals(service)) {
                    hasService = true;
                }
            }
            if (!hasService) {
                session.setAttribute("excessOfAuthority", true);
                response.sendRedirect("main.jsp");
                return;
            }
            //根据权限判断是否能访问相应的jsp页面
        } else if (path.endsWith(".jsp")) {
            boolean hasJsp = false;
            for (String str: authorityMap.get(authority)) {
                if (str.equals(path)) {
                    hasJsp = true;
                }
            }
            if (!hasJsp) {
                session.setAttribute("excessOfAuthority", true);
                response.sendRedirect("main.jsp");
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        authorityMap.put("employee", employeeAuthorities);
        authorityMap.put("manager", managerAuthorities);
        authorityMap.put("admin", adminAuthorities);
    }

}