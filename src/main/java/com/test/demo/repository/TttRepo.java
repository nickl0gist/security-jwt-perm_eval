package com.test.demo.repository;

import com.test.demo.model.TPA;
import com.test.demo.model.TTT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created on 25.07.2020
 *
 * @author Mykola Horkov
 * mykola.horkov@gmail.com
 */
@Component
public interface TttRepo extends JpaRepository<TTT, Long> {

}
