package com.test.demo.model;

import com.test.demo.repository.*;
import com.test.demo.service.WarehouseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created on 26.07.2020
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private WarehouseRepo warehouseRepo;
    private TttRepo tttRepo;
    private TpaRepo tpaRepo;
    private ManifestRepo manifestRepo;
    private WarehousePermissionRepo warehousePermissionRepo;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, WarehouseRepo warehouseRepo, TttRepo tttRepo,
                  TpaRepo tpaRepo, ManifestRepo manifestRepo, WarehousePermissionRepo warehousePermissionRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.warehouseRepo = warehouseRepo;
        this.tttRepo = tttRepo;
        this.tpaRepo = tpaRepo;
        this.manifestRepo = manifestRepo;
        this.warehousePermissionRepo = warehousePermissionRepo;
    }

    @Override
    public void run(String... args) {
        User user1 = new User(1, "jan@example.com",       passwordEncoder.encode("boo"), "CCC");
        User user2 = new User(2, "stefan@example.com",    passwordEncoder.encode("boo"), "XDC");
        User user3 = new User(3, "kalina@example.com",    passwordEncoder.encode("boo"), "TXDC");
        User user4 = new User(4, "joanna@example.com",    passwordEncoder.encode("boo"), "TC");
        User user5 = new User(5, "marcelina@example.com", passwordEncoder.encode("boo"), "GUEST");
        User user6 = new User(6, "katarzyna@example.com", passwordEncoder.encode("boo"), "ADMIN");
        User user7 = new User(7, "foo@example.com",       passwordEncoder.encode("boo"), "TS");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);

        Warehouse warehouse1 = new Warehouse(1L, "Warehouse 1", "wh_cc",   WarehouseType.CC , new ArrayList<>(), new ArrayList<>());
        Warehouse warehouse2 = new Warehouse(2L, "Warehouse 2", "wh_xd",   WarehouseType.XD , new ArrayList<>(), new ArrayList<>());
        Warehouse warehouse3 = new Warehouse(3L, "Warehouse 3", "wh_txd1", WarehouseType.TXD, new ArrayList<>(), new ArrayList<>());
        Warehouse warehouse4 = new Warehouse(4L, "Warehouse 4", "wh_txd2", WarehouseType.TXD, new ArrayList<>(), new ArrayList<>());

        // Coordinators of Warehouses
        warehousePermissionRepo.persist(new WarehousePermission(user1, warehouse1, WarehousePermissionType.CCC));
        warehousePermissionRepo.persist(new WarehousePermission(user2, warehouse2, WarehousePermissionType.XDC));
        warehousePermissionRepo.persist(new WarehousePermission(user3, warehouse3, WarehousePermissionType.TXDC));

        // trade controllers
        warehousePermissionRepo.persist(new WarehousePermission(user4, warehouse1, WarehousePermissionType.GUEST));
        warehousePermissionRepo.persist(new WarehousePermission(user4, warehouse2, WarehousePermissionType.GUEST));
        warehousePermissionRepo.persist(new WarehousePermission(user4, warehouse3, WarehousePermissionType.TC));
        warehousePermissionRepo.persist(new WarehousePermission(user4, warehouse4, WarehousePermissionType.TC));

        // Traffic Schedulers
        warehousePermissionRepo.persist(new WarehousePermission(user7, warehouse1, WarehousePermissionType.TS));
        warehousePermissionRepo.persist(new WarehousePermission(user7, warehouse2, WarehousePermissionType.TS));

        // GUESTS
        warehousePermissionRepo.persist(new WarehousePermission(user5, warehouse1, WarehousePermissionType.GUEST));
        warehousePermissionRepo.persist(new WarehousePermission(user5, warehouse2, WarehousePermissionType.GUEST));
        warehousePermissionRepo.persist(new WarehousePermission(user5, warehouse3, WarehousePermissionType.GUEST));
        warehousePermissionRepo.persist(new WarehousePermission(user5, warehouse4, WarehousePermissionType.GUEST));

        // ADMIN
        warehousePermissionRepo.persist(new WarehousePermission(user6, warehouse1, WarehousePermissionType.ADMIN));
        warehousePermissionRepo.persist(new WarehousePermission(user6, warehouse2, WarehousePermissionType.ADMIN));
        warehousePermissionRepo.persist(new WarehousePermission(user6, warehouse3, WarehousePermissionType.ADMIN));
        warehousePermissionRepo.persist(new WarehousePermission(user6, warehouse4, WarehousePermissionType.ADMIN));

        warehouseRepo.save(warehouse1);
        warehouseRepo.save(warehouse2);
        warehouseRepo.save(warehouse3);
        warehouseRepo.save(warehouse4);

        TPA tpa1 = new TPA(1L , "TPA1", warehouseRepo.getOne(1L));
        TPA tpa2 = new TPA(2L , "TPA2", warehouseRepo.getOne(2L));
        TPA tpa3 = new TPA(3L , "TPA3", warehouseRepo.getOne(3L));
        TPA tpa4 = new TPA(4L , "TPA4", warehouseRepo.getOne(3L));
        TPA tpa5 = new TPA(5L , "TPA5", warehouseRepo.getOne(4L));
        TPA tpa6 = new TPA(6L , "TPA6", warehouseRepo.getOne(4L));

        TTT ttt1 = new TTT(1L, "TTT1", warehouseRepo.getOne(1L));
        TTT ttt2 = new TTT(2L, "TTT2", warehouseRepo.getOne(2L));
        TTT ttt3 = new TTT(3L, "TTT3", warehouseRepo.getOne(3L));
        TTT ttt4 = new TTT(4L, "TTT4", warehouseRepo.getOne(3L));
        TTT ttt5 = new TTT(5L, "TTT5", warehouseRepo.getOne(4L));
        TTT ttt6 = new TTT(6L, "TTT6", warehouseRepo.getOne(4L));

        tpaRepo.save(tpa1);
        tpaRepo.save(tpa2);
        tpaRepo.save(tpa3);
        tpaRepo.save(tpa4);
        tpaRepo.save(tpa5);
        tpaRepo.save(tpa6);

        tttRepo.save(ttt1);
        tttRepo.save(ttt2);
        tttRepo.save(ttt3);
        tttRepo.save(ttt4);
        tttRepo.save(ttt5);
        tttRepo.save(ttt6);

        Manifest manifest1  = new Manifest(1L,  "MAN-1",  tpaRepo.getOne(1L), tttRepo.getOne(1L));
        Manifest manifest2  = new Manifest(2L,  "MAN-2",  tpaRepo.getOne(2L), tttRepo.getOne(1L));
        Manifest manifest3  = new Manifest(3L,  "MAN-3",  tpaRepo.getOne(2L), tttRepo.getOne(2L));
        Manifest manifest4  = new Manifest(4L,  "MAN-4",  tpaRepo.getOne(3L), tttRepo.getOne(2L));
        Manifest manifest5  = new Manifest(5L,  "MAN-5",  tpaRepo.getOne(3L), tttRepo.getOne(3L));
        Manifest manifest6  = new Manifest(6L,  "MAN-6",  tpaRepo.getOne(2L), tttRepo.getOne(3L));
        Manifest manifest7  = new Manifest(7L,  "MAN-7",  tpaRepo.getOne(1L), tttRepo.getOne(3L));
        Manifest manifest8  = new Manifest(8L,  "MAN-8",  tpaRepo.getOne(4L), tttRepo.getOne(4L));
        Manifest manifest9  = new Manifest(9L,  "MAN-9",  tpaRepo.getOne(5L), tttRepo.getOne(5L));
        Manifest manifest10 = new Manifest(10L, "MAN-10", tpaRepo.getOne(6L), tttRepo.getOne(5L));
        Manifest manifest11 = new Manifest(11L, "MAN-11", tpaRepo.getOne(6L), tttRepo.getOne(6L));

        manifestRepo.save(manifest1);
        manifestRepo.save(manifest2);
        manifestRepo.save(manifest3);
        manifestRepo.save(manifest4);
        manifestRepo.save(manifest5);
        manifestRepo.save(manifest6);
        manifestRepo.save(manifest7);
        manifestRepo.save(manifest8);
        manifestRepo.save(manifest9);
        manifestRepo.save(manifest10);
        manifestRepo.save(manifest11);
    }
}
