package by.itclass.controllers.news;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractNewsController;
import by.itclass.model.comparator.SortedNewsByLikesComparator;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.News;
import by.itclass.model.utils.ImageUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "NewsListController", urlPatterns = AppConstant.URL_NEWS_CONT)
public class NewsListController extends AbstractNewsController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> newsList = newsService.get();

        List<News> topNewsList = new ArrayList<>(newsList);
        //topNewsList.sort(new SortedNewsByLikesComparator());
        //topNewsList.sort((news1, news2)->news1.getLikes() - news2.getLikes());
        //topNewsList.sort(Comparator.comparingInt(News::getLikes));
        for (News news : newsList) {
            //--> Подготовка картинки для отбражения пользователю
            ServletContext context = this.getServletContext();
            String path = context.getRealPath(AppConstant.IMAGE_WEB_REPOSITORY);
            if (news.getImage().getContent() != null) {
                ImageUtil.createImageFile(path, news.getImage());
            }
            //<--
        }
        request.setAttribute(AppConstant.NEWS_LIST_ATTR, newsList);
        request.setAttribute(AppConstant.TOP_NEWS_LIST_ATTR, topNewsList);
        forward(request, response, AppConstant.INDEX_JSP);

    }

}