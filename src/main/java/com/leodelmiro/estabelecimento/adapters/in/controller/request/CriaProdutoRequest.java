package com.leodelmiro.estabelecimento.adapters.in.controller.request;

import com.leodelmiro.estabelecimento.application.core.domain.Categoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record CriaProdutoRequest(
        @NotBlank String nome,
        @NotNull Categoria categoria,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 10, fraction = 2)
        BigDecimal preco,
        @NotBlank String descricao,
        @NotNull Set<CriaProdutoImagemRequest> imagens
) {
}
