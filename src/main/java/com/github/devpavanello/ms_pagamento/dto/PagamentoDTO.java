package com.github.devpavanello.ms_pagamento.dto;

import com.github.devpavanello.ms_pagamento.model.Pagamento;
import com.github.devpavanello.ms_pagamento.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    private Long id;

    @NotNull(message = "Campo obrigatorio")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @Size(max = 100, message = "Máximo de 100 caracteres")
    private String nome;

    @Size(max = 19, message = "Numero do cartao deve ter no maximo 19 caracteres")
    private String numeroDoCartao;

    @Size(max = 5, min = 5, message = "Validade do cartao deve ter no maximo 5 caracteres")
    private String validade;

    @Size(max = 3, min = 3, message = "Codigo do cartao deve ter no maximo 3 caracteres")
    private String codigoDeSeguranca;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @NotNull(message = "Pedido ID é obrigatorio")
    @Positive(message = "O ID do pedido deve ser um número positivo")
    private Long pedidoId;

    @NotNull(message = "Forma de pagamento ID é obrigatorio")
    @Positive(message = "O ID da forma de pagamento deve ser um número positivo")
    private Long formaDePagamentoId;

    public PagamentoDTO(Pagamento entity){
        id = entity.getId();
        valor = entity.getValor();
        nome = entity.getNome();
        numeroDoCartao = entity.getNumeroDoCartao();
        validade = entity.getValidade();
        codigoDeSeguranca = entity.getCodigoDeSeguranca();
        status = entity.getStatus();
        pedidoId = entity.getPedidoId();
        formaDePagamentoId = entity.getFormaDePagamentoId();
    }
}
