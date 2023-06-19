package by.itclass.model.mapper;

import by.itclass.model.dto.NewsDTO;
import by.itclass.model.entities.News;

public class NewsMapper {
    public static News map(NewsDTO newsDTO) {
        return null;
    }

    public static NewsDTO map(News news) {
        return new NewsDTO(news.getId(), news.getTitle(), news.getText(),
                news.getDate(), news.getLikes(), news.getDislikes(), news.getImage());
    }
}
