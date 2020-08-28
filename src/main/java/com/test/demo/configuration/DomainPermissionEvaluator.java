package com.test.demo.configuration;

import com.test.demo.model.Warehouse;
import com.test.demo.model.WarehousePermissionType;
import com.test.demo.model.WarehouseType;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.WarehousePermissionRepo;
import com.test.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 26.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@Component
public class DomainPermissionEvaluator implements PermissionEvaluator {

    WarehousePermissionRepo warehousePermissionRepo;
    UserService userService;

    @Autowired
    public DomainPermissionEvaluator(WarehousePermissionRepo warehousePermissionRepo, UserService userService) {
        this.warehousePermissionRepo = warehousePermissionRepo;
        this.userService = userService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {
        UserPrincipal principal = new UserPrincipal(userService.findByEmail(authentication.getName()));

        if (domainObject instanceof Warehouse) {
            return hasPermissionToWarehouse(principal.getUsername(), ((Warehouse) domainObject).getWarehouseId(), (List<WarehousePermissionType>)permission);
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable domainObjectId, String domainObjectType, Object permission) {
        UserPrincipal principal = new UserPrincipal(userService.findByEmail(authentication.getName()));
        if (domainObjectType.equals(Warehouse.class.getName())) {
            return hasPermissionToWarehouse(principal.getUsername(),
                    (Long) domainObjectId,
                    (List<WarehousePermissionType>)permission);
        }
        return false;
    }

    private boolean hasPermissionToWarehouse(String email, long id, List<WarehousePermissionType> permissionList) {
        return   warehousePermissionRepo
                .findAll()
                .stream()
                .filter(warehousePermission -> warehousePermission.getWarehouse().getWarehouseId() == id)
                .filter(warehousePermission -> warehousePermission.getUser().getEmail().equals(email))
                .anyMatch(warehousePermission -> permissionList.contains(warehousePermission.getWarehousePermissionType().toString()));
    }
}

