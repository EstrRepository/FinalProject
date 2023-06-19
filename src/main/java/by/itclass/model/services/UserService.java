package by.itclass.model.services;

import by.itclass.model.dao.impl.UserDAOHibernateImpl;
import by.itclass.model.dao.interfaces.UserDAO;
import by.itclass.model.dao.impl.UserDAOJDBCImpl;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.Image;
import by.itclass.model.entities.User;
import by.itclass.model.mapper.UserMapper;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
       //userDAO = new UserDAOJDBCImpl();
       userDAO = new UserDAOHibernateImpl();
    }

    public boolean registration(UserDTO userDTO, String password) {
        User user = UserMapper.map(userDTO);
        user.setPassword(password);
        user.setImage(userDTO.getImage());
        return userDAO.save(user);
    }

    public UserDTO authorization(String loginOrEmail, String password) {
        User user = userDAO.get(loginOrEmail, password);
        return UserMapper.map(user);
    }

    public boolean refactor(UserDTO userDTO, String password) {
        User user = UserMapper.map(userDTO);
        user.setPassword(password);
        return userDAO.update(user, password);
    }

    public void uploadImage(UserDTO userDTO, Image image) {
        User user = UserMapper.map(userDTO);
        user.setImage(image);
        if (userDAO.saveImage(user)) {
            userDTO.getImage().setName(image.getName());
            userDTO.getImage().setContent(image.getContent());
        }
    }
}
