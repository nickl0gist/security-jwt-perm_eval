package com.test.demo.service;

import com.test.demo.model.TPA;
import com.test.demo.repository.TpaRepo;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.WarehousePermissionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created on 29.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Slf4j
@Service
public class TpaService {
    
    @Autowired
    TpaRepo tpaRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehousePermissionRepo warehousePermissionRepo;

    @Secured({"ROLE_CCC", "ROLE_XDC", "ROLE_TXDC", "ROLE_TS", "ROLE_TC", "ROLE_GUEST", "ROLE_ADMIN"})
    public TPA findTpaById(long id) {
        return tpaRepo.findById(id).orElse(null);
    }

    @Secured({"ROLE_CCC", "ROLE_XDC", "ROLE_TXDC", "ROLE_ADMIN"})
    public void persist(TPA tpa) {
        tpaRepo.save(tpa);
    }
}
