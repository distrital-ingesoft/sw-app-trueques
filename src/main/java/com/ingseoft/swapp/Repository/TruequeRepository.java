package com.ingseoft.swapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingseoft.swapp.Model.Trueque;

@Repository
public interface TruequeRepository extends JpaRepository<Trueque,Integer> {
}
