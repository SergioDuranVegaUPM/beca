package com.beca_backend.model.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/*
Clase MensajeResponse : objeto que representa la respuesta a enviar al cliente.
Se envía un mensaje y un objeto (que será normalmente un UsuarioDto o una lista de estos)
*/
@Data
@ToString
@Builder
public class MensajeResponse implements Serializable{
    private String mensaje;
    private Object object;
}
