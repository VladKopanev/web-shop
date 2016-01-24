package com.epam.preprod.kopaniev.repository.transaction;


import com.epam.preprod.kopaniev.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public class TransactionManagerImpl implements TransactionManager {
    private DataSource ds;

    public TransactionManagerImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public <R> R executeTransaction(Transaction<R> transaction) throws TransactionException {
        R res;
        try (Connection con = ds.getConnection()) {
            con.setAutoCommit(false);
            try {
                res = transaction.execute(con);
            } catch (Exception e) {
                con.rollback();
                throw e;
            }
            con.commit();
        } catch (AppException | SQLException ex) {
            ex.printStackTrace();
            throw new TransactionException("Transaction failed", ex);
        }
        return res;
    }

    @Override
    public <R> R executeQuery(Query<R> query) throws AppException {
        R res;
        try (Connection con = ds.getConnection()) {
            res = query.execute(con);
        } catch (AppException | SQLException ex) {
            ex.printStackTrace();
            throw new QueryException("Query failed", ex);
        }
        return res;
    }
}
