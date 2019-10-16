package com.worm.web_images20.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
/*Getters, Setters to all fields + all-args constructor*/
@Data
public class TheImage {

    private static final Path ABSOLUTE_PATH = Paths.get(".").toAbsolutePath();
    private static final String FILE_FOLDER = "/images/";

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "image_name")
    private String name;


    @Transient
    private MultipartFile myFile;


    @ElementCollection
    private List<String> comments = new ArrayList<>();

    /*Lombok setter is not valid*/
    public void setComments(String comments) {
        this.comments.add(comments);
    }

    /*Method is called from thymeleaf, to show random comment from list*/
    public String getRandComment(){
        return comments.get((int) (Math.random() * comments.size()));
    }

    public void saveTheImageToFolder() throws IOException{

        Path path = Paths.get(ABSOLUTE_PATH + FILE_FOLDER + _nameGenerator(myFile.getOriginalFilename()));
        Files.write(path, myFile.getBytes());

    }

    public TheImage() {
    }

    /*slug, prevent bugs if we have 2 uploaded images with same name (now it has unique name),
      also used to send data in get request instead of ID */
    private String _nameGenerator(String name) {
        long _slugNumber = new Date().getTime();
        name = (_slugNumber+name);
        this.name = name;
        return name;
    }
}
