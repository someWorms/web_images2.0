package com.worm.web_images20.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Core model. stores metadata of the image.
 * Used Lombok to avoid getters and setters in this model
 * Override setter for comment to give it proper functionality
 * */
@Entity
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

    public void setComments(String comments) {
        this.comments.add(comments);
    }


    public String getRandComment(){
        return comments.get((int) (Math.random() * comments.size()));
    }

    public void saveTheImageToFolder() throws IOException{

        if(new File(ABSOLUTE_PATH + FILE_FOLDER).exists()) {
            Path path = Paths.get(ABSOLUTE_PATH + FILE_FOLDER + _nameGenerator(myFile.getOriginalFilename()));
            Files.write(path, myFile.getBytes());
        }else{
            new File(ABSOLUTE_PATH + FILE_FOLDER).mkdir();
            saveTheImageToFolder();
        }

    }

    public TheImage() {
    }

    /** slug, prevent bugs if we have 2 uploaded images with same name (now it has unique name),
     *  also used to send data as get request instead of ID
     * @param  name  name of uploafef image.
     */
    private String _nameGenerator(String name) {
        long _slugNumber = new Date().getTime();
        name = (_slugNumber+name);
        this.name = name;
        return name;
    }

}
