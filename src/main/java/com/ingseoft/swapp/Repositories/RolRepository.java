package com.ingseoft.swapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ingseoft.swapp.Model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    
}
