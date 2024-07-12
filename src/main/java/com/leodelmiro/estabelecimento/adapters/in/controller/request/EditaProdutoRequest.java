package com.leodelmiro.estabelecimento.adapters.in.controller.request;

import com.leodelmiro.estabelecimento.application.core.domain.Categoria;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Set;

public record EditaProdutoRequest(
        @Nullable String nome,
        @Nullable Categoria categoria,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer = 10, fraction = 2)
        @Nullable
        BigDecimal preco,
        @Min(0) @Nullable Long tempoDePreparoEmSegundos,
        @Nullable String descricao,
        Set<EditaProdutoImagemRequest> imagens
) {
}
