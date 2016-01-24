package com.epam.preprod.kopaniev.repository.transaction;

import com.epam.preprod.kopaniev.AppException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
@FunctionalInterface
public interface Transaction<E> {
    E execute(Connection c) throws AppException;
}
