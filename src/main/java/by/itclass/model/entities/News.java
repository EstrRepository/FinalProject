package by.itclass.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    private Date date;
    private int likes;
    private int dislikes;
    @Column(name = "id_user")
    private int idUser;
    //private String author;//!Временно коментируем, чтобы работала Hibernate реализация
    @OneToOne
    private Image image;

    public News() {
    }

    public News(int id) {
        this.id = id;
    }

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public News(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public News(int id, String title, String text, Image image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public News(String title, String text, Image image) {
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public News(int id, String title, String text, Date date, int likes, int dislikes, String author) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;

    }

    public News(int id, String title, String text, long date, int likes, Image image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = new Date(date);
        this.likes = likes;
        this.image = image;

    }

    public News(int id, String title, String text, long date, int likes, int dislikes, String author, Image image) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = new Date(date);
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getAuthor() {
        return "";
    }

    public void setAuthor(String author) {
        //this.author = author;
    }
}
