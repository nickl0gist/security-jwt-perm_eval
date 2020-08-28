package com.test.demo.repository;

import com.test.demo.model.Warehouse;
import com.test.demo.model.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created on 25.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

}
