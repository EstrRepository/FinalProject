package by.itclass.model.dao.impl;

import by.itclass.constants.AppConstant;
import by.itclass.model.dao.interfaces.NewsDAO;
import by.itclass.model.db.HQLRequest;
import by.itclass.model.db.HibernateManager;
import by.itclass.model.entities.Image;
import by.itclass.model.entities.News;
import by.itclass.model.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class NewsDAOHibernateImpl implements NewsDAO {
    @Override
    public News get(int id) {
        News news = null;
        try (Session session = HibernateManager.getSession()){
            news = session.get(News.class, id);
        }
        return news;
    }

    @Override
    public List<News> getList(User user) {
        List<News> newsList = null;
        try (Session session = HibernateManager.getSession()){
            Query<News> query = session.createQuery(HQLRequest.SELECT_NEWS_BY_USER_ID);
            query.setParameter(1, user.getId());
            newsList = query.list();
        }
        return newsList;
    }

    @Override
    public List<News> getList() {
        List<News> newsList = null;
        try (Session session = HibernateManager.getSession()){
            Query<News> query = session.createQuery(HQLRequest.SELECT_NEWS);
            newsList = query.list();
        }
        return newsList;
    }

    @Override
    public void delete(News news) {
        try (Session session = HibernateManager.getSession()){
            Transaction transaction = session.beginTransaction();
            news = session.get(News.class, news.getId());
            session.delete(news);
            transaction.commit();
        }
    }

    @Override
    public void update(News news) {
        try (Session session = HibernateManager.getSession()){
            Transaction transaction = session.beginTransaction();
            News updateNews = session.get(News.class, news.getId());
            updateNews.setTitle(news.getTitle());
            updateNews.setText(news.getText());

            Image updateImage = updateNews.getImage();
            if (news.getImage().getContent().length > 0) {
                updateImage.setName(news.getImage().getName());
                updateImage.setContent(news.getImage().getContent());
            }
            session.saveOrUpdate(updateNews);
            transaction.commit();
        }
    }

    @Override
    public void likes(int idNews, int idUser, String action) {
        News news = null;

        try (Session session = HibernateManager.getSession()){
            Transaction transaction = session.beginTransaction();
            news = session.get(News.class, idNews);
            System.out.println("news=" + news);
            if (action.equals(AppConstant.LIKE_ACTION)) {
                int likes = news.getLikes()+1;
                news.setLikes(likes);
            } else {
                int dislikes = news.getDislikes()+1;
                news.setDislikes(dislikes);
            }
            session.update(news);
            transaction.commit();
        }
    }

    @Override
    public void save(News news, int idUser) {
        news.setDate(new Date());
        news.setIdUser(idUser);
        try (Session session = HibernateManager.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(news.getImage());
            session.save(news);
            transaction.commit();
        }
    }
}
