package com.ingseoft.swapp.Repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ingseoft.swapp.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

}
