package com.test.demo.repository;

import com.test.demo.model.WarehousePermission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 26.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Component
public class WarehousePermissionRepo {
    private List<WarehousePermission> warehousePermissions = new ArrayList<>();

    public void persist(WarehousePermission entity) {
        warehousePermissions.add(entity);
    }

    public List<WarehousePermission> findAll() {
        return new ArrayList<>(warehousePermissions);
    }
}
