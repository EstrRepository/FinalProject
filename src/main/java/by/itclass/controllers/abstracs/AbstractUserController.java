package by.itclass.controllers.abstracs;

import by.itclass.model.services.UserService;

import javax.servlet.annotation.WebServlet;

//Уровень абстракции над контроллерами, обрабатывающими запросы
//для User сущностей
@WebServlet(name = "AbstractUserController")
public abstract class AbstractUserController extends AbstractController {
    //Вводим общее описание, которое будет наследоваться другими контроллерами
    protected UserService userServices;

    public AbstractUserController() {
        userServices = new UserService();
    }
}
