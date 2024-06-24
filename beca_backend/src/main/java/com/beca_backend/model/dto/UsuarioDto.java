package com.beca_backend.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UsuarioDto implements Serializable{

    private int id;
    private String nombre;
    private String apellidos;
    private String curso;
    private int anoIngreso;
    private double notaMedia;

}
