package com.epam.preprod.kopaniev.repository.builder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Vladyslav_Kopaniev on 11/19/2015.
 */
public class QueryBuilder {
    private List<String> columns = new ArrayList<>();

    private List<String> tables = new ArrayList<>();

    private Map<String, Object> wheres = new LinkedHashMap<>();

    private Map<String, Set<Object>> in = new LinkedHashMap<>();

    private List<String> orderBys = new ArrayList<>();

    private List<String> joins = new ArrayList<>();

    private String limit = "";

    private String selectDirective = "";


    public QueryBuilder() {
    }

    public QueryBuilder(String table) {
        tables.add(table);
    }

    public QueryBuilder selectDirective(String directive) {
        selectDirective = directive;
        return this;
    }

    public QueryBuilder column(String name) {
        columns.add(name);
        return this;
    }


    public QueryBuilder from(String table) {
        tables.add(table);
        return this;
    }

    public QueryBuilder orderBy(String name) {
        orderBys.add(name);
        return this;
    }

    public QueryBuilder join(String join) {
        joins.add(join);
        return this;
    }

    public void limit(int from, int number) {
        if (from >= 0) {
            limit = from + ", " + number;
        } else {
            limit = "" + number;
        }
    }

    @Override
    public String toString() {

        StringBuilder sql = new StringBuilder("select " + selectDirective);

        if (columns.size() == 0) {
            sql.append("*");
        } else {
            appendList(sql, columns, "", ", ");
        }

        appendList(sql, tables, " from ", ", ");
        appendList(sql, joins, " join ", " join ");
        appendListWhere(sql, " and ");
        appendList(sql, orderBys, " order by ", ", ");

        if (!limit.isEmpty()) {
            sql.append(" limit ").append(limit);
        }

        return sql.toString();
    }

    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
        int i = 1;
        for (Object o : wheres.values()) {
            ps.setObject(i++, o);
        }

        for (Set<Object> params : in.values()) {
            for (Object param : params) {
                ps.setObject(i++, param);
            }
        }
    }

    public QueryBuilder where(String expr, Object object) {
        if (object != null && !"".equals(object)) {
            wheres.put(expr, object);
        }
        return this;
    }

    public QueryBuilder in(String expr, Object object) {
        if (object != null) {
            Set<Object> set;
            if (in.containsKey(expr)) {
                set = in.get(expr);
            } else {
                set = new LinkedHashSet<>();
                in.put(expr, set);
            }
            set.add(object);
        }
        return this;
    }

    private void appendList(StringBuilder sql, List<String> list, String init,
                            String sep) {
        boolean first = true;
        for (String s : list) {
            if (first) {
                sql.append(init);
            } else {
                sql.append(sep);
            }
            sql.append(s);
            first = false;
        }
    }

    private void appendListWhere(StringBuilder sql, String sep) {
        String initWhere = " where ";
        boolean first = true;

        for (Object o : wheres.keySet()) {
            if (first) {
                sql.append(initWhere);
            } else {
                sql.append(sep);
            }

            sql.append(o).append("?");
            first = false;
        }

        String sepAnd = "";
        if (!wheres.isEmpty()) {
            sepAnd = " and ";
        } else {
            if (!in.isEmpty())
                sql.append(initWhere);
        }

        String initIn = " in (";
        for (Object key : in.keySet()) {
            first = true;

            for (int i = 0; i < in.get(key).size(); i++) {
                if (first) {
                    sql.append(sepAnd).append(key).append(initIn);
                } else {
                    sql.append(", ");
                }
                sql.append(" ? ");
                first = false;
            }
            if (initIn.contains("("))
                sql.append(")");
            sepAnd = " and ";
        }
    }
}
