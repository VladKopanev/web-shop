package ua.nure.kopaniev.controller;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.Path;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.builder.RequestBuilder;
import ua.nure.kopaniev.bean.SortField;
import ua.nure.kopaniev.service.ServiceFactories;
import ua.nure.kopaniev.service.items.ItemService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/shop.do/*")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        ServiceFactories factories = (ServiceFactories) context.getAttribute("factories");
        ItemService itemService = factories.getItemServiceFactory().getItemService(context);


        QueryBean query = getQueryBean(req);
        setRequestString(query, req);
        int page = 1;
        if (req.getParameter("page") != null && !"".equals(req.getParameter("page"))) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfRecords = 0;
        try {
            req.setAttribute("shopItems",
                    itemService.getItems(query));
            noOfRecords = itemService.getCountOfItems(query);
        } catch (AppException e) {
            System.out.println(e.getMessage());
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / query.getCount());

        System.out.println(noOfRecords);
        System.out.println(noOfPages);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher(Path.SHOP_PAGE).forward(req, resp);
    }

    private QueryBean getQueryBean(HttpServletRequest req) {
        float priceFrom = 0;
        float priceTo = 0;

        if (req.getParameter("priceRange") != null && !"".equals(req.getAttribute("priceRange"))) {
            String range = req.getParameter("priceRange");
            String[] bounds = range.split(",");
            if (bounds.length >= 2) {

                try {
                    priceFrom = Float.parseFloat(bounds[0].replace("[", ""));
                    priceTo = Float.parseFloat(bounds[1].replace("]", ""));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }


        String[] brands = req.getParameterValues("brand");
        String[] categories = req.getParameterValues("category");
        String name = req.getParameter("itemName");

        int page = 1;
        int recordsPerPage = 3;
        if (req.getParameter("page") != null && !"".equals(req.getParameter("page"))) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        if (req.getParameter("recordsPerPage") != null && !"".equals(req.getParameter("recordsPerPage"))) {
            recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        }
        int offset = (page - 1) * recordsPerPage;

        String[] sort = req.getParameterValues("sort");

        Map<SortField, String> sorts = null;
        if (sort != null) {
            sorts = new HashMap<>();
            SortField sortField;
            for (String s : sort) {
                try {
                    sortField = SortField.value(s.substring(0, s.indexOf("_")));
                    String order = s.substring(s.indexOf("_") + 1);
                    sorts.put(sortField, order);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return new QueryBean(priceFrom, priceTo, brands, categories, offset, recordsPerPage, name, sorts);
    }

    private void setRequestString(QueryBean queryBean, HttpServletRequest req) {
        RequestBuilder rb = new RequestBuilder();

        if (queryBean.getBrands() != null) {
            for (String s : queryBean.getBrands()) {
                rb.add("brand", s);
            }
        }

        if (queryBean.getCategories() != null) {
            for (String s : queryBean.getCategories()) {
                rb.add("category", s);
            }
        }

        if (queryBean.getSort() != null) {
            queryBean.getSort().forEach((k, v) -> rb.add("sort", String.format("%s_%s", k, v)));
        }

        rb.add("recordsPerPage", String.valueOf(queryBean.getCount()));
        rb.add("priceRange", String.format("[%f, %f]", queryBean.getPriceFrom(), queryBean.getPriceTo()));
        rb.add("itemName", queryBean.getName());
        req.setAttribute("shopRequest", rb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
