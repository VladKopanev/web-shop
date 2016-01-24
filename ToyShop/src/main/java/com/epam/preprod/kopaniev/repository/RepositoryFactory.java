package com.epam.preprod.kopaniev.repository;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
public interface RepositoryFactory {

    UserRepository getUserRepository();

    ItemRepository getToyRepository();
}
