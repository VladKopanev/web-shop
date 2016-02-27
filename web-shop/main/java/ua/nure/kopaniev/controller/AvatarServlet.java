package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.kopaniev.bean.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class AvatarServlet {

    @Value("${avatar.dir}")
    private String avatarDir;

    @RequestMapping(value = "/avatar", produces = "image/jpg")
    protected BufferedImage getAvatar(@ModelAttribute User user) throws IOException {
        log.info("::getAvatar({})", user);
        File avaFile = new File(avatarDir + File.separator + user.getEmail());

        if (!avaFile.exists()) {
            avaFile = new File(avatarDir + File.separator + "unknown.jpg");
        }

        InputStream inputStream = new FileInputStream(avaFile);
        return ImageIO.read(inputStream);
    }
}
