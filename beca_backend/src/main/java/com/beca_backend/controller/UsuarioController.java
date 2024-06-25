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

/*
Clase UsuarioController : es el controlador de la API, luego maneja las solicitudes HTTP
Todas las rutas empiezan con /api
*/
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {

    // Servicio de la aplicación
    @Autowired
    private UsuarioService usuarioService;

    // Devuelve una lista de todos los registros existentes o null si no hay registros
    @GetMapping("showAll")
    public ResponseEntity<?> showAll() {
        List<Usuario> list = usuarioService.findAll();
        // Si no hay registros...
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

    // Guarda el usuario pasado como argumento y lo devuelve. Devolverá null ante cualquier error
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

    // Actualiza el usuario pasado como argumento y lo devuelve. Devolverá null ante cualquier error
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        Usuario usuario = null;
        try {
            // Si el id no está registrado...
            if (!usuarioService.existsById(id)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El usuario no existe")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            } else {
                // Si el id pasado en la url no coincide con el id del usuario pasado como argumento...
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

    // Elimina el usuario con el id pasado como argumento en la url. Ante cualquier error devolverá null
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

    // Devuelve el usuario con el id pasado como argumento, o null si no existe o hay algún error
    @GetMapping("showById/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);

        // Si el id no está registrado...
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
