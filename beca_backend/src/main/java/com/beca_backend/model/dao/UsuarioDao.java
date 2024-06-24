package com.beca_backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.beca_backend.model.entity.Usuario;

public interface UsuarioDao extends CrudRepository <Usuario,Integer> {

}
