package com.ingseoft.swapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ingseoft.swapp.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
