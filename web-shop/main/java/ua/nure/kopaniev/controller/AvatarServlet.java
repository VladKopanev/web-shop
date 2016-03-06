package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.kopaniev.bean.User;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class AvatarServlet {

    @Value("${avatar.dir}")
    private String avatarDir;

    @Value("${avatar.default.path}")
    private String defaultAvatarPath;

    @RequestMapping(value = "/avatar", produces = "image/jpg")
    protected byte[] getAvatar(@ModelAttribute User user) throws IOException {

        log.info("::getAvatar({})", user);
        val sb = new StringBuilder(avatarDir)
                            .append(File.separator)
                            .append(user.getEmail());

        File avaFile = new File(sb.toString());

        if (!avaFile.exists()) {
            avaFile = new File(defaultAvatarPath);
        }

        return FileUtils.readFileToByteArray(avaFile);
    }
}
