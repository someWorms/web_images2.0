package com.worm.web_images20.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
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
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "path_and_name")
    private String pathAndName;

    @Transient
    private MultipartFile multipartFile;

    @ElementCollection
    private List<String> comments = new ArrayList<>();


}
