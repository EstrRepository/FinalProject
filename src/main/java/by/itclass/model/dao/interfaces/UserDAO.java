package by.itclass.model.dao.interfaces;

import by.itclass.model.entities.User;

//Интерфейс объявляет набор методов, которые можно вызывать для
//управления информацией о сущности в БД
public interface UserDAO {
    boolean save(User user);
    User get(String loginOrEmail, String password);
    boolean update(User user, String password);
    boolean saveImage(User user);
}
