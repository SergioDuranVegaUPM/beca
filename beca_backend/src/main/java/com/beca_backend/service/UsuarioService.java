package com.beca_backend.service;

import java.util.List;

import com.beca_backend.model.dto.UsuarioDto;
import com.beca_backend.model.entity.Usuario;

/*
Intefaz UsuarioService : interfaz del servicio de la aplicación encargado de la lógica de negocio
*/
public interface UsuarioService {

    // Devuelve una lista de todos los registros existentes
    List<Usuario> findAll();

    // Guarda el usuario pasado como argumento y lo devuelve
    Usuario save(UsuarioDto usuario);

    // Devuelve el usuario con el id pasado como argumento o null si no existe
    Usuario findById(Integer id);

    // Elimina el usuario pasado como argumento
    void delete(Usuario usuario);

    // Devuelve true si existe un usuario con el id pasado como argumento o false en caso contrario
    boolean existsById(Integer id);
    
}
