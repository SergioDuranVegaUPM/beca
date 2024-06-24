package com.beca_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beca_backend.model.dao.UsuarioDao;
import com.beca_backend.model.dto.UsuarioDto;
import com.beca_backend.model.entity.Usuario;
import com.beca_backend.service.UsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

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

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return (List) usuarioDao.findAll();
    }

}
