package com.beca_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beca_backend.model.dao.UsuarioDao;
import com.beca_backend.model.dto.UsuarioDto;
import com.beca_backend.model.entity.Usuario;
import com.beca_backend.service.UsuarioService;

import java.util.List;

/*
Clase UsuarioService : implementación del servicio de la aplicación encargado de la lógica de negocio
Se empleará a través de su interfaz mediante @Autowired
*/
@Service
public class UsuarioServiceImpl implements UsuarioService{

    // Nos permitirá acceder a la base de datos
    @Autowired
    private UsuarioDao usuarioDao;

    // Guarda el usuario pasado como argumento y lo devuelve
    @Override
    @Transactional
    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                                .id(usuarioDto.getId())
                                .nombre(usuarioDto.getNombre())
                                .apellidos(usuarioDto.getApellidos())
                                .anoIngreso(usuarioDto.getAnoIngreso())
                                .curso(usuarioDto.getCurso())
                                .notaMedia(usuarioDto.getNotaMedia())
                                .build();
        return usuarioDao.save(usuario);
    }

    // Devuelve el usuario con el id pasado como argumento o null si no existe
    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    // Elimina el usuario pasado como argumento
    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    // Devuelve true si existe un usuario con el id pasado como argumento o false en caso contrario
    @Override
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }

    // Devuelve una lista de todos los registros existentes
    @Override
    public List<Usuario> findAll() {
        return (List) usuarioDao.findAll();
    }

}
