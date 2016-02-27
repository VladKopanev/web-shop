package ua.nure.kopaniev.repository.transaction;

import ua.nure.kopaniev.AppException;

import java.sql.Connection;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
@FunctionalInterface
public interface Transaction<E> {
    E execute(Connection c) throws AppException;
}
