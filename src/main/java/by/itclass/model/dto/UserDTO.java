package by.itclass.model.dto;

import by.itclass.model.entities.Image;
import by.itclass.model.entities.News;

import java.util.List;

//Объекты этого класса будут использоваться для отображения информации о пользователе на странице
//основной объект для обмена данными
public class UserDTO {
    private int id;
    private String login;
    private String email;
    private Image image;

    public UserDTO() {
    }

    public UserDTO(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public UserDTO(String login, String email, Image image) {
        this.login = login;
        this.email = email;
        this.image = image;
    }

    public UserDTO(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public UserDTO(int id, String login, String email, Image image) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
