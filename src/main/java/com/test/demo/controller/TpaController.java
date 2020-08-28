package com.test.demo.controller;

import com.test.demo.model.TPA;
import com.test.demo.model.TTT;
import com.test.demo.repository.TpaRepo;
import com.test.demo.repository.WarehouseRepo;
import com.test.demo.service.TpaService;
import com.test.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created on 25.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@RestController
@RequestMapping("/warehouse/{warehouseId}/tpa")
public class TpaController {

    @Autowired
    TpaService tpaService;

    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public List<TPA> getAllTpa(@PathVariable("warehouseId") long id) {
        return warehouseService.findWarehouseById(id).getTpaList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    public TPA getTpa(@PathVariable("warehouseId") long warehouseId, @PathVariable Long id) {
        TPA tpa = tpaService.findTpaById(id);
        if(!warehouseService.findWarehouseById(warehouseId).getTpaList().contains(tpa)){
            return null;
        }
        return tpa;
    }

    @PostMapping
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'ADMIN'})")
    public RedirectView postNewTpa(@PathVariable("warehouseId") long warehouseId, @RequestBody TPA tpa) {
        warehouseService.addTpaToWarehouse(warehouseId, tpa);
        return new RedirectView("/warehouse/" + warehouseId + "/tpa");
    }
}
