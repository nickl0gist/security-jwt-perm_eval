package com.test.demo.controller;

import com.test.demo.model.TPA;
import com.test.demo.model.TTT;
import com.test.demo.repository.TttRepo;
import com.test.demo.repository.WarehouseRepo;
import com.test.demo.service.TttService;
import com.test.demo.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/warehouse/{warehouseId}/ttt")
public class TttController {

    @Autowired
    TttService tttService;

    @Autowired
    WarehouseService warehouseService;

    @GetMapping //+CCC +GUEST +ADMIN
    public List<TTT> getAllTtt(@PathVariable("warehouseId") long id) {
        return warehouseService.findWarehouseById(id).getTttList();
    }

    @GetMapping("/{id}") //+CCC +GUEST +ADMIN
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    public TTT getTtt(@PathVariable("warehouseId") long warehouseId, @PathVariable Long id) {
        TTT ttt = tttService.findTttById(id);
        if(!warehouseService.findWarehouseById(warehouseId).getTttList().contains(ttt)){
            return null;
        }
        return ttt;
    }

    @PostMapping //+CCC -GUEST +ADMIN
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN'})")
    public RedirectView postNewTtt(@PathVariable("warehouseId") long warehouseId, @RequestBody TTT ttt) {
        tttService.persist(ttt);
        warehouseService.findWarehouseById(warehouseId).getTttList().add(tttService.findTttById(ttt.getTttId()));
        return new RedirectView("/warehouse/" + warehouseId + "/ttt");
    }
}
