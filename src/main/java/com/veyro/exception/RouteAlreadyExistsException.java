package com.veyro.exception;

public class RouteAlreadyExistsException extends RuntimeException {

    public RouteAlreadyExistsException() {
        super("Já existe uma rota cadastrada com essa combinação de identificador do projeto, método e caminho.");
    }

}
