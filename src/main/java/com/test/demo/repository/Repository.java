package com.test.demo.repository;

import java.util.List;
import java.util.Optional;

/**
 * Created on 25.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
public interface Repository<E> {

    void persist(E entity);

    List<E> findAll();

    Optional<E> findById(long id);

}
