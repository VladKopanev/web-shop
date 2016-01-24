package com.epam.preprod.kopaniev.servlet;

import com.epam.preprod.kopaniev.Path;
import com.epam.preprod.kopaniev.service.ServiceFactories;
import com.epam.preprod.kopaniev.service.ServiceFactoriesImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ���� on 06.11.2015.
 */
public class SignUpServletTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    RequestDispatcher disp;

    @Mock
    HttpSession session;

    @Mock
    ServletContext ctx;


    ServiceFactories factories = new ServiceFactoriesImpl();
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(SignUpServletTest.this);
        when(req.getRequestDispatcher(Path.SIGN_UP_PAGE_SERVLET)).thenReturn(disp);
        when(req.getSession()).thenReturn(session);
        when(req.getServletContext()).thenReturn(ctx);
        when(ctx.getAttribute("factories")).thenReturn(factories);
    }

    @Test
    public void testFailValidation() throws Exception {

        HttpServlet servlet = new SignUpServlet();
        when(req.getMethod()).thenReturn("POST");
        servlet.service(req, resp);
        verify(disp).forward(req, resp);
    }

    @Test
    public void testPostWithSessionCaptchaSavingMode() throws Exception {
        String code = "captcha";
        when(session.getAttribute("captcha_code")).thenReturn(code);
        testPostWithSpecificCaptchaSavingMode("session", code);
    }

    @Test
    public void testPostWithContextCaptchaSavingMode() throws Exception {
        String captchaId = "testId";
        String code = "captcha";
        Cookie[] cookies = new Cookie[]{new Cookie("captchaId", captchaId)};
        when(req.getCookies()).thenReturn(cookies);

        when(ctx.getAttribute(captchaId)).thenReturn(code);
        testPostWithSpecificCaptchaSavingMode("context", code);
    }


    public void testPostWithSpecificCaptchaSavingMode(String mode, String code) throws Exception{

        //setting request parameters
        when(req.getParameter("name")).thenReturn("validName");
        when(req.getParameter("surname")).thenReturn("validSurname");
        when(req.getParameter("pass")).thenReturn("validpass12");
        when(req.getParameter("email")).thenReturn("valid@email.test");
        when(req.getParameter("user_code")).thenReturn(code);

        when(session.getAttribute("startRgsrtTime")).thenReturn(System.currentTimeMillis());
        when(req.getMethod()).thenReturn("POST");

        when(ctx.getInitParameter("savingMode")).thenReturn(mode);

        HttpServlet servlet = new SignUpServlet();
        servlet.service(req, resp);

        verify(resp).sendRedirect(Path.HOME);
    }
}