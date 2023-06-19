package by.itclass.controllers.news;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractNewsController;
import by.itclass.model.entities.Image;
import by.itclass.model.entities.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RefactorNewsController", urlPatterns = AppConstant.URL_REFACTOR_NEWS_CONT)
public class RefactorNewsController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNews = (String) request.getAttribute(AppConstant.ID_LABEL);
        String title = (String) request.getAttribute(AppConstant.TITLE_LABEL);
        String text = (String) request.getAttribute(AppConstant.TEXT_LABEL);
        String fileName = (String) request.getAttribute(AppConstant.FILE_NAME_LABEL);
        byte[] fileContent = (byte[]) request.getAttribute(AppConstant.FILE_CONTENT_LABEL);
        int id = Integer.parseInt(idNews);

        News news = new News(id, title, text, new Image(fileName, fileContent));
        newsService.refactor(news);

        redirect(response, AppConstant.URL_MY_NEWS_CONT);
    }
}
