package com.ingseoft.swapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingseoft.swapp.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
