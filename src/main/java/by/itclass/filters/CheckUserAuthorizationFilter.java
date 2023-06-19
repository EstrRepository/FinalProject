package by.itclass.filters;

import by.itclass.constants.AppConstant;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Данный фильтр предоставляет доступ к страницам mynews.jsp и cabinet.jsp только
//авторизованным пользователям
@WebFilter(filterName = "CheckUserAuthorizationFilter", urlPatterns = {AppConstant.MY_NEWS_JSP, AppConstant.CABINET_JSP, AppConstant.ADD_NEWS_JSP, AppConstant.EDIT_NEWS_JSP})
public class CheckUserAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute(AppConstant.USER_ATTR) != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + AppConstant.AUTH_JSP);
        }

    }
}