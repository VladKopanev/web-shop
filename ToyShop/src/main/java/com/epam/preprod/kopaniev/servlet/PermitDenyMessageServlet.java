package com.epam.preprod.kopaniev.servlet;

import com.epam.preprod.kopaniev.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Влад on 28.11.2015.
 */
@WebServlet("/denyPermission.do")
public class PermitDenyMessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", "Permission denied!");
        req.getRequestDispatcher(Path.PERMIT_DENY_PAGE).forward(req, resp);
    }
}
