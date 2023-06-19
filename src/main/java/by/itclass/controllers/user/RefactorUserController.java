package by.itclass.controllers.user;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractUserController;
import by.itclass.model.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RefactorUserController", urlPatterns = AppConstant.URL_REFACTOR_USER_CONT)
public class RefactorUserController extends AbstractUserController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(AppConstant.LOGIN_LABEL);
        String email = request.getParameter(AppConstant.EMAIL_LABEL);
        String password = request.getParameter(AppConstant.PASSWORD_LABEL);

        HttpSession session = request.getSession();
        UserDTO userData = (UserDTO) session.getAttribute(AppConstant.USER_ATTR);
        UserDTO userDTO = new UserDTO(userData.getId(), login, email);

        if (userServices.refactor(userDTO, password)) {
            if (login != "") userData.setLogin(login);
            if (email != "") userData.setEmail(email);
            redirect(response, AppConstant.CABINET_JSP);
        }
    }
}
