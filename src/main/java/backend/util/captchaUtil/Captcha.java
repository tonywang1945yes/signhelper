package backend.util.captchaUtil;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class Captcha {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    String text;
    BufferedImage image;

//    public Captcha(){
//        getCode();
//    }

    public String getCode() {
        text = defaultKaptcha.createText();
        image = defaultKaptcha.createImage(text);
        return text;
    }

//    public BufferedImage getCaptchaImage(String text){
//        if(image!=null){
//            return image;
//        }else {
//
//        }
//    }

    public void write(OutputStream os) throws IOException {
        ImageIO.write(image, "png", os);
        os.close();
    }

    public DefaultKaptcha getDefaultKaptcha() {
        return defaultKaptcha;
    }

    public void setDefaultKaptcha(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }
}
