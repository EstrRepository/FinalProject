package by.itclass.controllers.news;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractNewsController;
import by.itclass.model.entities.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteNewsController", urlPatterns = AppConstant.URL_REMOVE_NEWS_CONT)
public class RemoveNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = request.getParameter(AppConstant.ID_LABEL);
        int id = Integer.parseInt(idNews);
        News news = new News(id);
        newsService.remove(news);
        redirect(response, AppConstant.URL_MY_NEWS_CONT);
    }

    
}
