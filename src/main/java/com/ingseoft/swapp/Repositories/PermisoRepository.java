package com.ingseoft.swapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;



import com.ingseoft.swapp.Model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    
}
