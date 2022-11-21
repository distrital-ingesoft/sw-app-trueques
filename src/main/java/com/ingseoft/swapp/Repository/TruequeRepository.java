package com.ingseoft.swapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingseoft.swapp.Model.Trueque;

public interface TruequeRepository extends JpaRepository<Trueque,Integer> {
    List<Trueque> findByIdTrueque(Long IdTrueque);
}
