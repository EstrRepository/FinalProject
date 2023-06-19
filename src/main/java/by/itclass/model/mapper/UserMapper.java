package by.itclass.model.mapper;

import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.User;

//Класс используется для перехода между представлениями User и UserDTO
public class UserMapper {
    public static User map(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getLogin(), userDTO.getEmail());
    }

    public static UserDTO map(User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getImage());
    }
}
