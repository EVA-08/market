package controller;

import entiry.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by EVA-08 on 2017/7/6.
 */

@WebFilter(filterName = "controller.LoginFilter", urlPatterns = "*.jsp", initParams = {
        @WebInitParam(name = "loginPage", value = "/login.jsp"),
        @WebInitParam(name = "registerPage", value = "/register.jsp")
})

public class LoginFilter implements Filter {
    private FilterConfig config;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        request.setCharacterEncoding("utf-8");
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        if (config.getInitParameter("registerPage").equals(request.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }

        //判断是否访问登录界面
        if (config.getInitParameter("loginPage").equals(request.getServletPath())) {
            String login = (String)request.getParameter("login");
            if (login == null || "false".equals(login) || "fail".equals(login)) {
                chain.doFilter(request, response);
            }
            return;
        }



        //false代表未登录
        if (userInfo == null) {
            response.sendRedirect("/login.jsp?login=false");
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

}
