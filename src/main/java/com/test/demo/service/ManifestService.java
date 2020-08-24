package com.test.demo.service;

import com.test.demo.model.Manifest;
import com.test.demo.repository.ManifestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created on 29.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@Service
public class ManifestService {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    private ManifestRepo manifestRepo;

    @Autowired
    TpaService tpaService;

    @Autowired
    TttService tttService;


    public Manifest findManifestById(long manId){
        return manifestRepo.findById(manId).orElse(null);
    }


    @Secured({"ROLE_CCC", "ROLE_XDC", "ROLE_TXDC", "ROLE_ADMIN", "ROLE_TS", "ROLE_TC"})
    public void persist(Manifest manifest) {
        manifestRepo.save(manifest);
    }

    public void addManifestToTpa(Manifest manifest, long tpaId) {
        persist(manifest);
        tpaService.findTpaById(tpaId).getManifestList().add(findManifestById(manifest.getManifestId()));
    }

    public void addManifestToTtt(Manifest manifest, long tttId) {
        persist(manifest);
        tttService.findTttById(tttId).getManifestList().add(findManifestById(manifest.getManifestId()));
    }
}
