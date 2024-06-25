package com.beca_backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.beca_backend.model.entity.Usuario;

/*
Interfaz UsuarioDto : es el repositorio CRUD que conecta la API con la base de datos
*/
public interface UsuarioDao extends CrudRepository <Usuario,Integer> {

}
