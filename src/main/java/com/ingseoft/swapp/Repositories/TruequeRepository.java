package com.ingseoft.swapp.Repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.ingseoft.swapp.Model.Trueque;

public interface TruequeRepository extends JpaRepository<Trueque, Integer> {

    List<Trueque> findBySolicitanteId(Integer id);

    List<Trueque> findBySolicitadoId(Integer id);
    
}
