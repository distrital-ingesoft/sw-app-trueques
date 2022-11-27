package com.ingseoft.swapp.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ingseoft.swapp.Model.Categoria;
import com.ingseoft.swapp.Model.ElementoTrueque;

public interface ElementoTruequeRepository extends JpaRepository<ElementoTrueque, Integer> {

    Iterable<ElementoTrueque> findByCategoria(Categoria categoria);
   
}
