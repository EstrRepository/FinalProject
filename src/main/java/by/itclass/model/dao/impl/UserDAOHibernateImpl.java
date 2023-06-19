package by.itclass.model.dao.impl;

import by.itclass.model.dao.interfaces.UserDAO;
import by.itclass.model.db.HQLRequest;
import by.itclass.model.db.HibernateManager;
import by.itclass.model.entities.Image;
import by.itclass.model.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDAOHibernateImpl implements UserDAO {
    @Override
    public boolean save(User user) {
        boolean isSave = false;
        try (Session session = HibernateManager.getSession()){
            //Все изменения в БД выполняются в рамках транзакция
            //Транзакция позволяет накопить набор изменений
            //и применить их все сразу как конечный итог
            Transaction transaction = session.beginTransaction();
            session.save(user.getImage());
            //Метод save(user) возвращает номер сохраненной записи(id)
            isSave = session.save(user) != null;
            transaction.commit();
        }
        return isSave;
    }

    @Override
    public User get(String loginOrEmail, String password) {
        User user = null;
        try (Session session = HibernateManager.getSession()){
            Query<User> query = session.createQuery(HQLRequest.SELECT_USER_BY_LOGIN_OR_EMAIL_AND_PASSWORD);
            query.setParameter(1, loginOrEmail);
            query.setParameter(2, loginOrEmail);
            query.setParameter(3, password);
            user = query.getSingleResult();
        }
        return user;
    }

    @Override
    public boolean update(User user, String password) {
        return false;
    }

    @Override
    public boolean saveImage(User user) {
        boolean isSave = false;
        try (Session session = HibernateManager.getSession()){
            Transaction transaction = session.beginTransaction();
            //Получаем старый объект image со статусом persist
            Image image = session.get(Image.class, user.getImage().getId());
            if (image != null) {
                //Теперь hibernate следит за изменениями данного объекта
                //Можно изменять ткущий объект картинки, все изменения
                //будут сохранены в БД после commit() транзакции
                image.setName(user.getImage().getName());
                image.setContent(user.getImage().getContent());
                transaction.commit();
                isSave = true;
            }
        }
        return isSave;
    }
}
