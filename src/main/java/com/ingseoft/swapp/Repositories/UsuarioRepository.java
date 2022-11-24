package com.ingseoft.swapp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ingseoft.swapp.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByCorreo(String correo);

    List<Usuario> findByCelular(Long celular);

    List<Usuario> findByDocumentoIdentificacion(Integer documento);

    List<Usuario> findByElementosTrueque(Integer elementosTrueque);
}
