package by.itclass.model.dao.impl;

import by.itclass.model.dao.interfaces.UserDAO;
import by.itclass.model.db.ConnectionManager;
import static by.itclass.model.db.SQLRequest.*;

import by.itclass.model.entities.Image;
import by.itclass.model.entities.User;


import java.sql.*;

//Реализация DAO, которая использует JDBC API
public class UserDAOJDBCImpl implements UserDAO {
    @Override
    public boolean save(User user) {
        boolean isRegist = false;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pstUser = cn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstImage = cn.prepareStatement(INSERT_IMAGE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstRelations = cn.prepareStatement(INSERT_USER_IMAGE_RELATIONS)) {
            pstUser.setString(1, user.getLogin());
            pstUser.setString(2, user.getPassword());
            pstUser.setString(3, user.getEmail());

            pstImage.setString(1, user.getImage().getName());
            pstImage.setBytes(2, user.getImage().getContent());

            //Метод executeUpdate() возвращает количество добавленных строк после исполнения запроса
            if (pstUser.executeUpdate() > 0) {
                ResultSet rs = pstUser.getGeneratedKeys();
                if (rs.next()) {
                    int idUser = rs.getInt(1);
                    user.setId(idUser);
                }
            }

            if (pstImage.executeUpdate() > 0) {
                ResultSet rs = pstImage.getGeneratedKeys();
                if (rs.next()) {
                    int idImage = rs.getInt(1);
                    user.getImage().setId(idImage);
                }
            }

            pstRelations.setInt(1, user.getId());
            pstRelations.setInt(2, user.getImage().getId());
            isRegist = pstRelations.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegist;
    }

    @Override
    public User get(String loginOrEmail, String password) {
        User user = null;
        try (Connection cn = ConnectionManager.getConnection();
        PreparedStatement pst = cn.prepareStatement(SELECT_USER_BY_LOGIN_OR_EMAIL_AND_PASSWORD)){
            pst.setString(1, loginOrEmail);
            pst.setString(2, loginOrEmail);
            pst.setString(3, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int idUser = rs.getInt(ID_COLUMN);
                String login = rs.getString(LOGIN_COLUMN);
                String email = rs.getString(EMAIL_COLUMN);

                //Получаем данные картинки
                int idImage = rs.getInt(ID_IMAGE_COLUMN);
                String name = rs.getString(NAME_COLUMN);
                byte[] content = rs.getBytes(CONTENT_COLUMN);

                user = new User(idUser, login, email, new Image(idImage, name, content));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(User user, String password) {
        boolean isUpdate = false;
        System.out.println(UPDATE_USER);
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst1 = cn.prepareStatement(SET);
             PreparedStatement pst = cn.prepareStatement(UPDATE_USER)){
            pst1.setString(1, user.getLogin());
            pst1.setString(2, user.getPassword());
            pst1.setString(3, user.getEmail());
            pst1.executeUpdate();
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, Integer.toString(user.getId()));
            isUpdate = pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }

    @Override
    public boolean saveImage(User user) {
        boolean isSave = false;
        try (Connection cn = ConnectionManager.getConnection();
            PreparedStatement pst = cn.prepareStatement(UPDATE_IMAGE_USER)){
            pst.setString(1, user.getImage().getName());
            pst.setBytes(2, user.getImage().getContent());
            pst.setInt(3, user.getId());

            isSave = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSave;
    }
}
