package ua.nure.kopaniev.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.SessionScope;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.cart.UserCart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class OrderControllerTest {


    @Autowired
    private OrderController controller;

    @Autowired
    private UserCart cart;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    private User user;
    private Item item;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        item = new Item();
        item.setId(1);
        beanFactory.registerScope(WebApplicationContext.SCOPE_SESSION, new SessionScope());
    }

    @Test
    public void makeOrder() throws Exception {
        SecurityContextHolder
                .getContext()
                .setAuthentication(new TestingAuthenticationToken(user, null));
        cart.add(item);
        String html = controller.makeOrder("ship ddr", "mail");

    }
}