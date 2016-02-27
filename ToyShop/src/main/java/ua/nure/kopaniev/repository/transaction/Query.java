package ua.nure.kopaniev.repository.transaction;

import ua.nure.kopaniev.AppException;

import java.sql.Connection;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
@FunctionalInterface
public interface Query<E> {
    E execute(Connection c) throws AppException;
}