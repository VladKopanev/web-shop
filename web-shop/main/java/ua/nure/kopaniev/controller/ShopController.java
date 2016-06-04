package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.service.ItemService;

@Slf4j
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ItemService itemService;

    //TODO implement model overwriting with query params
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewShop(QueryBean query) {
        log.info("::viewShop({})", query);
        return new ModelAndView("shop").addObject("shopItems", itemService.getItems(query));
    }
}
