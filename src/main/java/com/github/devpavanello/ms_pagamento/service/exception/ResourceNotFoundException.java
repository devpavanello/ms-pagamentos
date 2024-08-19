package com.github.devpavanello.ms_pagamento.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    //RuntimeException não precisa de try-cath
    //Exception customizada para quando não encontrar o recurso
    public ResourceNotFoundException(String message){
        super(message);
    }
}
