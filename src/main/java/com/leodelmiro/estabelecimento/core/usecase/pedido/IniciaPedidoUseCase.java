package com.leodelmiro.estabelecimento.core.usecase.pedido;


import com.leodelmiro.estabelecimento.core.domain.CPF;
import com.leodelmiro.estabelecimento.core.domain.Pedido;

public interface IniciaPedidoUseCase {
    Pedido iniciar(CPF cpf);
}
