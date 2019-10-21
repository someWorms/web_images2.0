package com.worm.web_images20.valid;

import com.worm.web_images20.model.TheImage;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import java.io.IOException;

/**
 * Validate image to be an true image
 * */
public class ImageValidator {
    public boolean isImage(TheImage theImage) throws Exception {
        MagicMatch match;
        match = Magic.getMagicMatch(theImage.getMyFile().getBytes());
        String mimeType = match.getMimeType();
        if(!mimeType.startsWith("image")){
            System.out.println("not an image" + mimeType);
            return false;
        }else {
            return true;
        }

    }
}
