package by.itclass.model.db;

public class HQLRequest {
    public static final String SELECT_USER_BY_LOGIN_OR_EMAIL_AND_PASSWORD =
    "from User as u where (u.login=?1 or u.email=?2) and u.password=?3";

    public static final String SELECT_NEWS_BY_USER_ID =
            "from News as n where n.idUser=?1";

    public static final String SELECT_NEWS =
            "from News";
}
