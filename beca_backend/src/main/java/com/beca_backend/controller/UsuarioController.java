package com.beca_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beca_backend.model.dto.UsuarioDto;
import com.beca_backend.model.entity.Usuario;
import com.beca_backend.model.payload.MensajeResponse;
import com.beca_backend.service.UsuarioService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("showAll")
    public ResponseEntity<?> showAll() {
        List<Usuario> list = usuarioService.findAll();
        if (list == null) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros")
                            .object(null)
                            .build(),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(list)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = null;
        try {
            usuario = usuarioService.save(usuarioDto);
            usuarioDto = UsuarioDto.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellidos(usuario.getApellidos())
                    .anoIngreso(usuario.getAnoIngreso())
                    .curso(usuario.getCurso())
                    .notaMedia(usuario.getNotaMedia())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioDto)
                    .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        Usuario usuario = null;
        try {
            if (!usuarioService.existsById(id)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El usuario no existe")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            } else {
                if (usuarioDto.getId() != id) {
                    return new ResponseEntity<>(MensajeResponse.builder()
                            .mensaje("El usuario enviado no tiene el id enviado")
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
                }
                usuario = usuarioService.save(usuarioDto);
                usuarioDto = UsuarioDto.builder()
                        .id(usuario.getId())
                        .nombre(usuario.getNombre())
                        .apellidos(usuario.getApellidos())
                        .anoIngreso(usuario.getAnoIngreso())
                        .curso(usuario.getCurso())
                        .notaMedia(usuario.getNotaMedia())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado correctamente")
                        .object(usuarioDto)
                        .build(), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            usuarioService.delete(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("showById/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El usuario no existe")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .anoIngreso(usuario.getAnoIngreso())
                .curso(usuario.getCurso())
                .notaMedia(usuario.getNotaMedia())
                .build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje(null)
                .object(usuarioDto)
                .build(), HttpStatus.OK);
    }

}
