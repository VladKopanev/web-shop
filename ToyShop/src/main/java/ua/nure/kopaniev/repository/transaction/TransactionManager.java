package ua.nure.kopaniev.repository.transaction;

import ua.nure.kopaniev.AppException;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public interface TransactionManager {
    <R> R executeTransaction(Transaction<R> transaction) throws TransactionException;

    <R> R executeQuery(Query<R> query) throws AppException;
}
