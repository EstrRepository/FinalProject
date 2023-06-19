package by.itclass.model.utils;

import by.itclass.model.entities.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

//Метод создаёт физически файл на сервере, если такого файла нет
public class ImageUtil {
    public static void createImageFile(String path, Image image) {
        //могут и каталоги содержаться в объекте File
        File file = new File(path + image.getName());

        //Метод exists() проверяет физическое существование файла по заданому пути
        if (!file.exists()) {
            try {
                //Метод createNewFile() создаёт файл физически
                if (file.createNewFile()) {
                    try (OutputStream out = new FileOutputStream(file)){
                        out.write(image.getContent());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Image getImage(String path) {
        Image image = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(path + "user.png"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            byte[] content = byteArrayOutputStream.toByteArray();
            image = new Image("user.png", content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("image.getName()=" + image.getName());
        //System.out.println("image.getContent()=" + Arrays.toString(image.getContent()));
        return image;
    }
}
