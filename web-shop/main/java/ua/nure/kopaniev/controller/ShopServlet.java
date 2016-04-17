package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.service.items.ItemService;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopServlet {

    @Autowired
    ItemService itemService;

    //TODO implement model overwriting with query params
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewShop(QueryBean query) {
        log.info("::viewShop({})", query);

        val mav = new ModelAndView("shop");

        mav.addObject("shopItems", itemService.getItems(query));
        return mav;
    }
}
