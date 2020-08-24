package com.test.demo.controller;

import com.test.demo.model.Warehouse;
import com.test.demo.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created on 25.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping //+ CCC +GUEST +ADMIN
    @PostFilter("hasPermission(filterObject, {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'GUEST', 'ADMIN'})")
    public List<Warehouse> getAllWh(){
        return warehouseService.findAllWarehouses();
    }

    @GetMapping("/{id}") //+ CCC +GUEST +ADMIN
    public Warehouse getOne(@PathVariable Long id){
        return warehouseService.findWarehouseById(id);
    }

    @PutMapping //-CCC -Guest +ADMIN
    public RedirectView updateById(@RequestBody Warehouse warehouse){
        warehouseService.update(warehouse);
        return new RedirectView("warehouse/" + warehouse.getWarehouseId());
    }

    @PutMapping("/{id}")  //-CCC -GUEST +ADMIN
    public RedirectView updateNameById(@PathVariable Long id, @RequestParam(defaultValue = "SOME_NAME") String name){
        warehouseService.updateNameById(id, name);
        return new RedirectView( id.toString());
    }

    @PostMapping //-CCC -GUEST +ADMIN
    public RedirectView createNew(@RequestBody Warehouse warehouse){
        warehouseService.persist(warehouse);
        return new RedirectView("warehouse/" + warehouse.getWarehouseId());
    }
}
