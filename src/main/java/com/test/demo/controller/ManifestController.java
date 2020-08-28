package com.test.demo.controller;

import com.test.demo.model.Manifest;
import com.test.demo.model.TPA;
import com.test.demo.model.TTT;
import com.test.demo.service.ManifestService;
import com.test.demo.service.TpaService;
import com.test.demo.service.TttService;
import com.test.demo.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@RestController
@RequestMapping({"/warehouse/{warehouseId}/tpa/{tpaId}/manifests",
                 "/warehouse/{warehouseId}/ttt/{tttId}/manifests"})
public class ManifestController {


    private ManifestService manifestService;
    private WarehouseService warehouseService;
    private TpaService tpaService;
    private TttService tttService;

    @Autowired
    public ManifestController(ManifestService manifestService, WarehouseService warehouseService, TpaService tpaService, TttService tttService) {
        this.manifestService = manifestService;
        this.warehouseService = warehouseService;
        this.tpaService = tpaService;
        this.tttService = tttService;
    }

    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    @GetMapping(headers = "truck=tpa")
    public List<Manifest> getManifestsFromTpa(@PathVariable("warehouseId") long warehouseId,
                                              @PathVariable("tpaId") long tpaId) {
        List<TPA> tpaList = warehouseService.findWarehouseById(warehouseId).getTpaList();
        TPA tpa = tpaService.findTpaById(tpaId);
        if (!tpaList.contains(tpa) || tpa == null) {
            return new ArrayList<>();
        }
        return tpa.getManifestList();
    }

    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    @GetMapping(headers = "truck=ttt")
    public List<Manifest> getManifestsFromTtt(@PathVariable("warehouseId") long warehouseId,
                                              @PathVariable("tttId") long tttId) {
        List<TTT> tttList = warehouseService.findWarehouseById(warehouseId).getTttList();
        TTT ttt = tttService.findTttById(tttId);
        if (!tttList.contains(ttt) || ttt == null) {
            return new ArrayList<>();
        }
        return ttt.getManifestList();
    }

    @GetMapping(value = "/{idM}", headers = "truck=ttt")
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    public Manifest getManifestFromTTT(@PathVariable long idM,  @PathVariable("tttId") long tttId, @PathVariable("warehouseId") long warehouseId) {
        List<TTT> tttList = warehouseService.findWarehouseById(warehouseId).getTttList();
        TTT ttt = tttService.findTttById(tttId);
        Manifest manifest = manifestService.findManifestById(idM);
        if (!tttList.contains(ttt) || ttt == null || manifest == null || !ttt.getManifestList().contains(manifest)) {
            return null;
        }
        return manifest;
    }

    @GetMapping(value = "/{idM}", headers = "truck=tpa")
    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN', 'GUEST'})")
    public Manifest getManifestFromTPA(@PathVariable long idM,  @PathVariable("tpaId") long tpaId, @PathVariable("warehouseId") long warehouseId) {
        List<TPA> tpaList = warehouseService.findWarehouseById(warehouseId).getTpaList();
        TPA tpa = tpaService.findTpaById(tpaId);
        Manifest manifest = manifestService.findManifestById(idM);
        if (!tpaList.contains(tpa) || tpa == null || manifest == null || !tpa.getManifestList().contains(manifest)) {
            return null;
        }
        return manifest;
    }

    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN'})")
    @PostMapping(headers = "truck=tpa")
    public RedirectView addNewManifestToTpa(@PathVariable("tpaId") long tpaId, @PathVariable("warehouseId") long warehouseId,
                                            @RequestBody Manifest manifest, HttpServletRequest request) {
        List<TPA> tpaList = warehouseService.findWarehouseById(warehouseId).getTpaList();
        TPA tpa = tpaService.findTpaById(tpaId);
        if (!tpaList.contains(tpa) || tpa == null) {
            return null;
        }
        manifestService.addManifestToTpa(manifest, tpaId);
        return new RedirectView(request.getRequestURL().toString());
    }

    @PreAuthorize("hasPermission(#warehouseId, 'com.test.demo.model.Warehouse', {'CCC', 'XDC', 'TXDC', 'TS', 'TC', 'ADMIN'})")
    @PostMapping(headers = "truck=ttt")
    public RedirectView addNewManifestToTTT(@PathVariable("tttId") long tttId, @PathVariable("warehouseId") long warehouseId,
                                            @RequestBody Manifest manifest, HttpServletRequest request) {
        List<TTT> tttList = warehouseService.findWarehouseById(warehouseId).getTttList();
        TTT ttt = tttService.findTttById(tttId);
        if (!tttList.contains(ttt) || ttt == null) {
            return null;
        }
        manifestService.addManifestToTtt(manifest, tttId);
        return new RedirectView(request.getRequestURL().toString());
    }
}
