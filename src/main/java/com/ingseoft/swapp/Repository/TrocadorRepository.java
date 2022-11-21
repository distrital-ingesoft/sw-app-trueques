package com.ingseoft.swapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingseoft.swapp.Model.Trocador;

@Repository
public interface TrocadorRepository extends JpaRepository<Trocador,Integer> {

}
