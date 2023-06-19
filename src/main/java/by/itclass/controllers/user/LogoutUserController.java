package by.itclass.controllers.user;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractUserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutUserController", urlPatterns = AppConstant.URL_LOGOUT_CONT)
public class LogoutUserController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Для выхода из системы нужно удалить существующую сессию пользователя,
        //а вместе с ней будут удалены и все атрибуты этой сессии
        HttpSession session = request.getSession();

        //Метод invalidate() удаляет текущую сессию пользователя
        session.invalidate();

        //Отправляем пользователя на страницу авторизации или на главную страницу
        redirect(response, AppConstant.URL_NEWS_CONT);
    }

}
