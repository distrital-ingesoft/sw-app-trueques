package com.ingseoft.swapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingseoft.swapp.Model.ElementoDeseado;

@Repository
public interface ElementoDeseadoRepository extends JpaRepository<ElementoDeseado,Integer> {

}
