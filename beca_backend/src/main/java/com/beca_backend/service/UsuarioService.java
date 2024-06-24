package com.beca_backend.service;

import java.util.List;

import com.beca_backend.model.dto.UsuarioDto;
import com.beca_backend.model.entity.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario save(UsuarioDto usuario);

    Usuario findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(Integer id);
    
}
