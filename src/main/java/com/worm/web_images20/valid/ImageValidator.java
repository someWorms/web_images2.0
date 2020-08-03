package com.worm.web_images20.valid;

import com.worm.web_images20.model.ImageEntity;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.springframework.web.multipart.MultipartFile;

/**
 * Validate image to be an true image
 * */
public class ImageValidator {
    public boolean isImage(MultipartFile file) throws Exception {
        MagicMatch match;
        match = Magic.getMagicMatch(file.getBytes());
        String mimeType = match.getMimeType();
        if(!mimeType.startsWith("image")){
            System.out.println("not an image" + mimeType);
            return false;
        }else {
            return true;
        }

    }
}
