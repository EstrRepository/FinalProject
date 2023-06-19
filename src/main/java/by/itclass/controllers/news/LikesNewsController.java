package by.itclass.controllers.news;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractNewsController;
import by.itclass.model.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikesNewsController", urlPatterns = AppConstant.URL_LIKE_NEWS_CONT)
public class LikesNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter(AppConstant.ID_LABEL);
        String action = request.getParameter(AppConstant.ACTION_LABEL);
        int id = Integer.parseInt(idNews);
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(AppConstant.USER_ATTR);
        newsService.like(id, userDTO.getId(), action);
        forward(request, response, AppConstant.URL_GET_NEWS_CONT);
    }

}
