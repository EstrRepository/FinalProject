package by.itclass.filters;

import by.itclass.constants.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Данный фильтр не даёт доступ на страницы auth.jsp и reg.jsp авторизованному пол-лю
@WebFilter(filterName = "CheckUserFilter", urlPatterns = {AppConstant.AUTH_JSP, AppConstant.REGIST_JSP})
public class CheckUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute(AppConstant.USER_ATTR)==null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + AppConstant.CABINET_JSP);
        }

    }
}
