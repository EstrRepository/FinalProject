package by.itclass.model.comparator;

import by.itclass.model.entities.News;

import java.util.Comparator;

public class SortedNewsByLikesComparator implements Comparator<News> {
    @Override
    public int compare(News news1, News news2) {
        return news2.getLikes() - news1.getLikes();
    }
}
