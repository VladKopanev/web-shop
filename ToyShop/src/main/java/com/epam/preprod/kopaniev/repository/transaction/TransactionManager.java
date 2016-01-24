package com.epam.preprod.kopaniev.repository.transaction;

import com.epam.preprod.kopaniev.AppException;

import java.sql.SQLException;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public interface TransactionManager {
    <R> R executeTransaction(Transaction<R> transaction) throws TransactionException;

    <R> R executeQuery(Query<R> query) throws AppException;
}
