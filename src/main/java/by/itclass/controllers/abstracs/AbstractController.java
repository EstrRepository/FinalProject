package by.itclass.controllers.abstracs;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Абстрактный контроллер будет задавать начальную реализацию и начальное поведение
//всем другим создаваемым контроллерам через наследования
@WebServlet(name = "AbstractController")
public abstract class AbstractController extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //Метод forward() выполняет перенаправления запроса
    protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        request.getRequestDispatcher(url)
                .forward(request, response);
    }

    //Метод redirect() выполняет переадресацию запроса
    protected void redirect(HttpServletResponse response, String url) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        response.sendRedirect(context.getContextPath() + url);///newssite.ru +
    }
}
