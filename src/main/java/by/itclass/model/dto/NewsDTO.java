package by.itclass.model.dto;
import by.itclass.model.entities.Image;

import java.util.Date;

public class NewsDTO {
    private int id;
    private String title;
    private String text;
    private Date date;
    private int likes;
    private int dislikes;
    private String author;
    private Image image;

    public NewsDTO() {
    }

    public NewsDTO(int id, String title, String text, Date date, int likes, int dislikes, Image image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
