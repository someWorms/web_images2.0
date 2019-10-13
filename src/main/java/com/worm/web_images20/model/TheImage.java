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

    //private static final Path curr = Paths.get(".");
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

    public void setComments(String comments) {
        this.comments.add(comments);
    }

    public String getRandComment(){
        return comments.get((int) (Math.random() * comments.size()));
    }

    public void saveTheImageToFolder() throws IOException{
        name = getMyFile().getOriginalFilename();

        byte[] bytes = myFile.getBytes();
        Path path = Paths.get(ABSOLUTE_PATH + FILE_FOLDER + _nameGenerator((String) myFile.getOriginalFilename()));
        Files.write(path, bytes);

    }

    public TheImage() {
    }

    /*slug*/
    private String _nameGenerator(String name) {
        long _slugNumber = new Date().getTime();
        name = (String)(_slugNumber+name);
        this.name = name;
        return name;
    }
}
