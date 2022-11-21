package com.ingseoft.swapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingseoft.swapp.Model.ElementoTrueque;

@Repository
public interface ElementoTruequeRepository extends JpaRepository<ElementoTrueque,Integer> {

}
