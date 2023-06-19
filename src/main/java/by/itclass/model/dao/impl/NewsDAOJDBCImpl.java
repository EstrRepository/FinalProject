package by.itclass.model.dao.impl;

import by.itclass.constants.AppConstant;
import by.itclass.model.dao.interfaces.NewsDAO;
import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.Image;
import by.itclass.model.entities.News;
import by.itclass.model.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static by.itclass.model.db.SQLRequest.*;

public class NewsDAOJDBCImpl implements NewsDAO {
    @Override
    public News get(int id) {
        News news = null;
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SELECT_NEWS_BY_ID)){
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String title = rs.getString(TITLE_COLUMN);
                String text = rs.getString(TEXT_COLUMN);
                Timestamp timestamp = rs.getTimestamp(DATE_COLUMN);
                int likes = rs.getInt(LIKES_COLUMN);
                int dislikes = rs.getInt(DISLIKES_COLUMN);
                String author = rs.getString(AUTHOR_COLUMN);
                int idImage = rs.getInt(ID_IMAGE_COLUMN);
                String name = rs.getString(NAME_COLUMN);
                byte[] content = rs.getBytes(CONTENT_COLUMN);
                news = new News(id, title, text, timestamp.getTime(), likes, dislikes, author, new Image(idImage, name, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public List<News> getList(User user) {
        List<News> newsList = new ArrayList<>();

        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pst = cn.prepareStatement(SELECT_NEWS_BY_USER_ID)){
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idNews = rs.getInt(ID_COLUMN);
                String title = rs.getString(TITLE_COLUMN);
                String text = rs.getString(TEXT_COLUMN);
                //Объект Timestamp хранит полученную из БД дату в виде кол-ва милисекунт прошедших с 01.01.1970
                Timestamp timestamp = rs.getTimestamp(DATE_COLUMN);
                int likes = rs.getInt(LIKES_COLUMN);
                int dislikes = rs.getInt(DISLIKES_COLUMN);
                int idImage = rs.getInt(ID_IMAGE_COLUMN);
                String name = rs.getString(NAME_COLUMN);
                byte[] content = rs.getBytes(CONTENT_COLUMN);

                //Метод getTime() возвращает кол-во м/сек прошедших с 01.01.1970
                newsList.add(new News(idNews, title, text, timestamp.getTime(), likes, dislikes, user.getLogin(), new Image(idImage, name, content)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public List<News> getList() {
        List<News> newsList = new ArrayList<>();
        News news = null;
        try (Connection cn = ConnectionManager.getConnection();
             Statement st = cn.createStatement()){
            ResultSet rs = st.executeQuery(SELECT_NEWS_ALL);

            while (rs.next()) {
                System.out.println(rs.toString());
                int id = rs.getInt(ID_COLUMN);
                String title = rs.getString(TITLE_COLUMN);
                String text = rs.getString(TEXT_COLUMN);
                Timestamp timestamp = rs.getTimestamp(DATE_COLUMN);
                int likes = rs.getInt(LIKES_COLUMN);
                int idImage = rs.getInt(ID_IMAGE_COLUMN);
                String name = rs.getString(NAME_COLUMN);
                byte[] content = rs.getBytes(CONTENT_COLUMN);

                news = new News(id, title, text, timestamp.getTime(), likes, new Image(idImage, name, content));
                newsList.add(news);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public void delete(News news) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement psr = cn.prepareStatement(DELETE_NEWS_BY_ID)){
            psr.setInt(1, news.getId());
            psr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(News news) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement pstNews = cn.prepareStatement(UPDATE_NEWS_BY_ID);
             PreparedStatement pstImage = cn.prepareStatement(UPDATE_IMAGE_BY_ID_NEWS)){
            pstNews.setString(1, news.getTitle());
            pstNews.setString(2, news.getText());
            pstNews.setInt(3, news.getId());
            pstNews.executeUpdate();

            pstImage.setString(1, news.getImage().getName());
            pstImage.setBytes(2, news.getImage().getContent());
            pstImage.setInt(3, news.getId());
            pstImage.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void likes(int idNews, int idUser, String action) {
        System.out.println("action=" + action);
        try (Connection cn = ConnectionManager.getConnection()){
            PreparedStatement pst = null;
            if (action.equals(AppConstant.LIKE_ACTION)) {
                pst = cn.prepareStatement(SET_LIKE_FOR_NEWS);
            } else {
                pst = cn.prepareStatement(SET_DISLIKE_FOR_NEWS);
            }
            pst.setInt(1, idNews);
            pst.setInt(2, idNews);
            pst.setInt(3, idUser);
            pst.executeUpdate();
            System.out.println("pst=" + pst);
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void disLikes(int idNews, int idUser) {
//        try (Connection cn = ConnectionManager.getConnection();
//             PreparedStatement pst = cn.prepareStatement(SET_DISLIKE_FOR_NEWS)){
//            pst.setInt(1, idNews);
//            pst.setInt(2, idNews);
//            pst.setInt(3, idUser);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void save(News news, int idUser) {
        try (Connection cn = ConnectionManager.getConnection();
             //Параметр RETURN_GENERATED_KEYS после вставки новой записи в таблицу,
             //получить сгенерированный id этой записи
             PreparedStatement pstNews = cn.prepareStatement(INSERT_NEWS, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstImage = cn.prepareStatement(INSERT_IMAGE, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstRelations = cn.prepareStatement(INSERT_NEWS_IMAGE_RELATIONS)){
            pstNews.setInt(1, idUser);
            pstNews.setString(2, news.getTitle());
            pstNews.setString(3, news.getText());

            pstImage.setString(1, news.getImage().getName());
            pstImage.setBytes(2, news.getImage().getContent());

            if (pstNews.executeUpdate() > 0) {
                ResultSet rs = pstNews.getGeneratedKeys();
                if (rs.next()) {
                    int idNews = rs.getInt(1);
                    news.setId(idNews);
                }
            }

            if (pstImage.executeUpdate() > 0) {
                ResultSet rs = pstImage.getGeneratedKeys();
                if (rs.next()) {
                    int idImage = rs.getInt(1);
                    news.getImage().setId(idImage);
                }
            }

            pstRelations.setInt(1, news.getId());
            pstRelations.setInt(2, news.getImage().getId());
            pstRelations.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
