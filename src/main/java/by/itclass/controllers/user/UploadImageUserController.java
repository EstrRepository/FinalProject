package by.itclass.controllers.user;

import by.itclass.constants.AppConstant;
import by.itclass.controllers.abstracs.AbstractUserController;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.Image;
import by.itclass.model.utils.ImageUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "UploadImageUserController", urlPatterns = AppConstant.UPLOAD_IMAGE_USER_CONT)
public class UploadImageUserController extends AbstractUserController {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Получаем объект пользователя из сессии для которого будет загружаться картинка
        UserDTO userDTO = (UserDTO) session.getAttribute(AppConstant.USER_ATTR);

        //устанавливаем кодировку текстового содержимого запроса
        request.setCharacterEncoding("UTF-8");//для названия файлов на кирилице
        String fileName = (String) request.getAttribute(AppConstant.FILE_NAME_LABEL);
        byte[] fileContent = (byte[]) request.getAttribute(AppConstant.FILE_CONTENT_LABEL);
        Image updateImage = new Image(fileName, fileContent);
        userServices.uploadImage(userDTO, updateImage);
        //--> Подготовка картинки для отбражения пользователю
        ServletContext context = this.getServletContext();
        String path = context.getRealPath(AppConstant.IMAGE_WEB_REPOSITORY);
        ImageUtil.createImageFile(path, userDTO.getImage());
        //<--
        request.setAttribute(AppConstant.USER_ATTR, userDTO);
        forward(request, response, AppConstant.CABINET_JSP);
    }

}
