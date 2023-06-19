package by.itclass.controllers.user;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractUserController;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.Image;
import by.itclass.model.utils.ImageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationUserController", urlPatterns = AppConstant.URL_REGIST_CONT)
public class RegistrationUserController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Получение данных из request
        String login = request.getParameter(AppConstant.LOGIN_LABEL);
        String password = request.getParameter(AppConstant.PASSWORD_LABEL);
        String email = request.getParameter(AppConstant.EMAIL_LABEL);
        String path = "D:/Documents/FinalProject_09.05.2023/FinalProject/src/main/webapp/image/";

        Image image = ImageUtil.getImage(path);

        //2. Создание объектов сущностей
        //объект нужен для того, чтобы объединить все данные
        //и в последующем передать в другие методы не пачку параметров, а всего одни параметр - объект
        UserDTO user = new UserDTO(login, email, image);

        //Сценарий обработки запроса на стороне model
        if (userServices.registration(user, password)) {
            //основной сценарий
            //После того, когда пользователь был сохранён в БД
            //перенаправляем его на страницу авторизации
            forward(request, response, AppConstant.AUTH_JSP);
        } else {
            //альтернативный сценарий
            //если были проблемы прои сохранении в БД
            //то отправляем пользователя сделать ещё одну попытку
            forward(request, response, AppConstant.REGIST_JSP);
        }


    }
}
