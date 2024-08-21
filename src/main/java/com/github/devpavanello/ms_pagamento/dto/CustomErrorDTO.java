package com.github.devpavanello.ms_pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomErrorDTO {

    //Atributos
    private String timestamp;
    private Integer status;
    private String error;
    private String path;
}
