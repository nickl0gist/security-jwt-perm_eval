package com.test.demo.service;

import com.test.demo.model.TPA;
import com.test.demo.model.TTT;
import com.test.demo.repository.TpaRepo;
import com.test.demo.repository.TttRepo;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.WarehousePermissionRepo;
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
public class TttService {
    @Autowired
    TttRepo tttRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehousePermissionRepo warehousePermissionRepo;

    //@Secured({"CCC", "ROLE_CCC", "ROLE_XDC", "ROLE_TXDC", "ROLE_TS", "ROLE_TC", "ROLE_GUEST", "ROLE_ADMIN"})
    public TTT findTttById(Long id) {
        return tttRepo.findById(id).orElse(null);
    }


    public void persist(TTT ttt) {
        tttRepo.save(ttt);
    }
}
