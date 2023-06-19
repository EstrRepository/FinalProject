package by.itclass.controllers.user;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractUserController;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Временный контроллер для авторизации без БД
@WebServlet(name = "TempAuthorizationUserController", urlPatterns = "/temp")
public class TempAuthorizationUserController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //Создаём временного пользователя для разработки
        UserDTO user = new UserDTO("admin", "admin@gmail.com");

        //То что должно в основном контроллере авторизации
        HttpSession session = request.getSession();
        session.setAttribute(AppConstant.USER_ATTR, user);
        forward(request, response, AppConstant.CABINET_JSP);
    }

}
