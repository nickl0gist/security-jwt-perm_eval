package com.test.demo.service;

import com.test.demo.model.*;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.WarehousePermissionRepo;
import com.test.demo.repository.WarehouseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 26.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@Service
public class WarehouseService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    WarehousePermissionRepo warehousePermissionRepo;

    @Autowired
    TpaService tpaService;


    public List<Warehouse> findAllWarehouses() {
        return warehouseRepo.findAll();
    }

    @PreAuthorize("hasPermission(#id, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'GUEST', 'ADMIN'})")
    public Warehouse findWarehouseById(Long id) {
        return warehouseRepo.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void persist(Warehouse warehouse) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(userName).orElseThrow(IllegalAccessError::new);
        warehouseRepo.save(warehouse);
        warehousePermissionRepo.persist(new WarehousePermission(user, warehouse, WarehousePermissionType.ADMIN));
    }

    @PreAuthorize("hasPermission(#warehouse, {'ADMIN'})")
    public void update(Warehouse warehouse) {
        warehouseRepo.findById(warehouse.getWarehouseId()).ifPresent(p -> {
            p.setName(warehouse.getName());
        });
    }

    @PreAuthorize("hasPermission(#id, 'com.test.demo.model.Warehouse', {'ADMIN'})")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Secured("ROLE_ADMIN")
    public void updateNameById(Long id, String name) {
        warehouseRepo.findById(id).ifPresent(p -> {
            p.setName(name);
        });
    }

    //@PreAuthorize("hasPermission(#id, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN'})")
    public void addTpaToWarehouse(long id, TPA tpa) {
        tpaService.persist(tpa);
        findWarehouseById(id).getTpaList().add(tpaService.findTpaById(tpa.getId()));
    }

    public void save(Warehouse warehouse){
        warehouseRepo.save(warehouse);
    }
}
