package by.itclass.controllers.abstracs;

import by.itclass.model.services.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AbstractNewsController")
public abstract class AbstractNewsController extends AbstractController {
    protected NewsService newsService;

    public AbstractNewsController() {
        newsService = new NewsService();
    }
}
