package by.itclass.model.db;

public class SQLRequest {
    public static final String INSERT_USER =
            "insert into users(login, password, email) values(?,?,?)";
    public static final String INSERT_NEWS =
            "insert into news(id_user, title, text, date) values(?,?,?, NOW())";
    public static final String INSERT_IMAGE =
            "insert into images(name, content) values(?,?)";
    public static final String INSERT_NEWS_IMAGE_RELATIONS =
            "insert into news_images(id_news, id_image) values(?,?)";
    public static final String INSERT_USER_IMAGE_RELATIONS =
            "insert into users_images(id_user, id_image) values(?,?)";
    public static final String SELECT_USER_BY_LOGIN_OR_EMAIL_AND_PASSWORD =
            "select " +
                    "users.id as id, " +
                    "login, " +
                    "email, " +
                    "images.id, " +
                    "name, " +
                    "content " +
                    "from users " +
                    "join users_images on users_images.id_user = users.id " +
                    "join images on images.id = users_images.id_image "  +
                    "where (login=? or email=?) and password=?";
    public static final String SET = "set @log=?, @pass=?, @eml=?";
    public static final String UPDATE_USER = "update users set login = (if(@log!='', ?, login)), password=(if(@pass!='', ?, password)), email=(if(@eml!='', ?, email)) where id=?";
    public static final String UPDATE_IMAGE_USER =
            "update images set name=?, content=? where id=(select id_image from users_images where id_user=?)";
    public static final String SELECT_NEWS_ALL =
            "select " +
                    "news.id as id," +
                    "title," +
                    "text," +
                    "date," +
                    "likes," +
                    "images.id," +
                    "name," +
                    "content " +
                    "from news " +
                    "left join news_images on news_images.id_news=news.id " +
                    "left join images on images.id=news_images.id_image " +
                    "where date > (now() - interval 1 month) group by date order by date desc";
    public static final String SELECT_NEWS_BY_ID =
            "select " +
                    "title," +
                    "text," +
                    "date," +
                    "likes," +
                    "dislikes," +
                    "users.login as author," +
                    "images.id," +
                    "name," +
                    "content " +
                    "from news " +
                    "join users on users.id=news.id_user " +
                    "join news_images on news_images.id_news=news.id " +
                    "join images on images.id=news_images.id_image " +
                    "where news.id=?";

    public static final String SELECT_NEWS_BY_USER_ID =
            "select " +
                    "news.id," +
                    "title," +
                    "text," +
                    "date," +
                    "likes," +
                    "dislikes," +
                    "images.id," +
                    "name," +
                    "content " +
                    "from news " +
                    "join news_images on news_images.id_news=news.id " +
                    "join images on images.id=news_images.id_image " +
                    "where id_user=? " +
                    "order by date";
    public static final String DELETE_NEWS_BY_ID = "delete from news where id=?";
    public static final String UPDATE_NEWS_BY_ID =
            "update news set title=?, text=? where id=?";
    public static final String UPDATE_IMAGE_BY_ID_NEWS =
            "update images set name=?, content=? where id=(select id_image from news_images where id_news=?)";
    public static final String SET_LIKE_FOR_NEWS =
            "update news set likes=likes+1 " +
                    "where id=? " +
                    "and id_user not in " +
                    "(select id_user from news_likes where id_news=? and id_user=?)";

    public static final String SET_DISLIKE_FOR_NEWS =
            "update news set dislikes=dislikes+1 " +
                    "where id=? " +
                    "and id_user not in " +
                    "(select id_user from news_likes where id_news=? and id_user=?)";

    public static final String ID_COLUMN = "id";
    public static final String ID_USER_COLUMN = "users.id";
    public static final String LOGIN_COLUMN = "login";
    public static final String EMAIL_COLUMN = "email";

    public static final String ID_IMAGE_COLUMN = "images.id";
    public static final String NAME_COLUMN = "name";
    public static final String CONTENT_COLUMN = "content";
    public static final String TITLE_COLUMN = "title";
    public static final String TEXT_COLUMN = "text";
    public static final String DATE_COLUMN = "date";
    public static final String LIKES_COLUMN = "likes";
    public static final String DISLIKES_COLUMN = "dislikes";
    public static final String AUTHOR_COLUMN = "author";
}
