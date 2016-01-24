package com.epam.preprod.kopaniev.servlet;

import com.epam.preprod.kopaniev.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/avatar.do/*")
public class AvatarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if (u != null) {
            String dir = req.getServletContext().getInitParameter("avaDir");
            File avaFile = new File(dir + File.separator + u.getEmail());
            if (!avaFile.exists()) {
                avaFile = new File(dir + File.separator + "unknown.jpg");
            }
            System.out.println(avaFile.getAbsolutePath());
            Files.copy(avaFile.toPath(), resp.getOutputStream());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
