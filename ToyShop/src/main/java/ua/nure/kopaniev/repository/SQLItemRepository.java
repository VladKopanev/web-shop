package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.Option;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.bean.Toy;
import ua.nure.kopaniev.repository.builder.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladyslav_Kopaniev on 11/17/2015.
 */
public class SQLItemRepository implements ItemRepository {

    @Override
    public List<Toy> getItems(Connection c, QueryBean bean) throws AppException {
        System.out.println(bean);
        QueryBuilder prodQuery = new QueryBuilder("products p");

        prodQuery.column("p.ProductID, p.ProductName, p.ProductPrice, p.ProductWeight, p.ProductImage, c.CategoryName, b.BrandName")
                .join("productcategories c on p.ProductCategoryID = c.CategoryID")
                .join("brands b ON p.ProductBrandID = b.BrandID")
                .where("p.ProductPrice between ", bean.getPriceFrom())
                .where("", bean.getPriceTo());

        if (bean.getBrands() != null) {
            for (String brand : bean.getBrands()) {
                prodQuery.in("b.BrandName", brand);
            }
        }

        if (bean.getCategories() != null) {
            for (String s : bean.getCategories()) {
                prodQuery.in("c.CategoryName", s);
            }
        }

        if (bean.getSort() != null && !bean.getSort().isEmpty()) {
            bean.getSort().forEach((k, v) -> prodQuery.orderBy(k + " " + v));
        }

        prodQuery.limit(bean.getOffset(), bean.getCount());

        QueryBuilder optQuery = new QueryBuilder("productoptions po");
        optQuery.column("po.ProductID, o.OptionName, og.OptionGroupName")
                .join("options o ON po.OptionID = o.OptionID")
                .join("optiongroups og ON po.OptionGroupID = og.OptionGroupID");

        List<Toy> toys = new ArrayList<>();
        try {
            String query = prodQuery.toString();
            System.out.println(query);
            Statement stm = c.prepareStatement(query);
            prodQuery.setPreparedStatement((PreparedStatement) stm);

            ResultSet toysResSet = ((PreparedStatement) stm).executeQuery();

            stm = c.createStatement();
            query = optQuery.toString();
            System.out.println(query);
            ResultSet optionsResSet = stm.executeQuery(query);

            Map<Integer, List<Option>> options = mapOptionsToProducts(optionsResSet);

            while (toysResSet.next()) {
                toys.add(toToy(toysResSet, options));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Can't get items", e);
        }
        System.out.println(toys);
        return toys;
    }

    @Override
    public int getCountOfItems(Connection c, QueryBean bean) throws AppException {
        int count = 0;
        QueryBuilder countQuery = new QueryBuilder("products p")
                .column("COUNT(*)")
                .join("productcategories c on p.ProductCategoryID = c.CategoryID")
                .join("brands b ON p.ProductBrandID = b.BrandID")
                .where("p.ProductPrice between ", bean.getPriceFrom())
                .where("p.ProductPrice between ", bean.getPriceFrom())
                .where("", bean.getPriceTo());

        if (bean.getCategories() != null) {
            for (String s : bean.getCategories()) {
                countQuery.in("c.CategoryName", s);
            }
        }

        if (bean.getBrands() != null) {
            for (String brand : bean.getBrands()) {
                countQuery.in("b.BrandName", brand);
            }
        }

        try {
            PreparedStatement stm = c.prepareStatement(countQuery.toString());
            countQuery.setPreparedStatement(stm);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppException("Can't get items count", e);
        }
        return count;
    }

    @Override
    public Toy getItem(Connection c, int id) throws AppException {
        QueryBuilder prodQuery = new QueryBuilder("products p");

        prodQuery.column("p.ProductID, p.ProductName, p.ProductPrice, p.ProductWeight, p.ProductImage, c.CategoryName, b.BrandName")
                .join("productcategories c on p.ProductCategoryID = c.CategoryID")
                .join("brands b ON p.ProductBrandID = b.BrandID")
                .where("p.ProductID=", id);


        QueryBuilder optQuery = new QueryBuilder("productoptions po");
        optQuery.column("po.ProductID, o.OptionName, og.OptionGroupName")
                .join("options o ON po.OptionID = o.OptionID")
                .join("optiongroups og ON po.OptionGroupID = og.OptionGroupID")
                .where("po.ProductID=", id);

        Toy toy = null;
        try {
            String query = prodQuery.toString();
            System.out.println(query);
            Statement stm = c.prepareStatement(query);
            prodQuery.setPreparedStatement((PreparedStatement) stm);

            ResultSet toysResSet = ((PreparedStatement) stm).executeQuery();

            query = optQuery.toString();
            stm = c.prepareStatement(query);
            optQuery.setPreparedStatement((PreparedStatement) stm);
            ResultSet optionsResSet = ((PreparedStatement)stm).executeQuery();

            Map<Integer, List<Option>> options = mapOptionsToProducts(optionsResSet);

            if (toysResSet.next()) {
                toy = toToy(toysResSet, options);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Can't get item", e);
        }
        System.out.println(toy);
        return toy;
    }

    private Toy toToy(ResultSet rs, Map<Integer, List<Option>> options) throws SQLException {
        int id = rs.getInt("ProductID");
        String name = rs.getString("ProductName");
        float price = rs.getFloat("ProductPrice");
        float weight = rs.getFloat("ProductWeight");
        String image = rs.getString("ProductImage");
        String category = rs.getString("CategoryName");
        String brand = rs.getString("BrandName");
        return new Toy(id, name, price, weight, image, category, brand, options.get(id));
    }

    private Map<Integer, List<Option>> mapOptionsToProducts(ResultSet rs) throws SQLException {
        Map<Integer, List<Option>> options = new HashMap<>();
        while (rs.next()) {
            int productId = rs.getInt("ProductID");
            String opName = rs.getString("OptionName");
            String opGroupName = rs.getString("OptionGroupName");
            Option op = new Option(opGroupName, opName);

            List<Option> currOpList = options.get(productId);

            if (currOpList == null) {
                currOpList = new ArrayList<>();
                options.put(productId, currOpList);
            }
            currOpList.add(op);
        }
        return options;
    }
}
