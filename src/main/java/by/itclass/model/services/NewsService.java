package by.itclass.model.services;

import by.itclass.constants.AppConstant;
import by.itclass.model.dao.impl.NewsDAOHibernateImpl;
import by.itclass.model.dao.impl.NewsDAOJDBCImpl;
import by.itclass.model.dao.interfaces.NewsDAO;
import by.itclass.model.dto.UserDTO;
import by.itclass.model.entities.News;
import by.itclass.model.entities.User;
import by.itclass.model.mapper.UserMapper;

import java.util.List;

public class NewsService {
    private NewsDAO newsDAO;

    public NewsService() {
        //newsDAO = new NewsDAOJDBCImpl();
        newsDAO = new NewsDAOHibernateImpl();
    }

    public News get(int id) {
        return newsDAO.get(id);
    }

    public List<News> get(UserDTO userDTO) {
        User user = UserMapper.map(userDTO);
        return newsDAO.getList(user);
    }

    public List<News> get() {
        return newsDAO.getList();
    }

    public void remove(News news) {
        newsDAO.delete(news);
    }

    public void refactor(News news) {
        newsDAO.update(news);
    }

    public void like(int idNews, int idUser, String action) {
        newsDAO.likes(idNews, idUser, action);
//        switch (action) {
//            case AppConstant.LIKE_ACTION :
//                newsDAO.likes(idNews, idUser);
//                break;
//            case AppConstant.DISLIKE_ACTION :
//                newsDAO.disLikes(idNews, idUser);
//                break;
//        }
    }

    public void add(News news, int idUser) {
        newsDAO.save(news, idUser);
    }
}
