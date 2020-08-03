package com.worm.web_images20.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageFileDTO {
    private MultipartFile file;
    private String commentary;
    private MultipartFile multipartFile;
}
