package bdd.utils;

import com.leodelmiro.pedido.entrypoint.api.request.AdicionaProdutoAoPedidoRequest;
import com.leodelmiro.pedido.entrypoint.api.request.ItemPedidoRequest;

import java.util.List;

public abstract class PedidoHelper {
    public static AdicionaProdutoAoPedidoRequest gerarAdicionaProdutoAoPedidoRequest() {
        return new AdicionaProdutoAoPedidoRequest(List.of(new ItemPedidoRequest(1L, 2)));
    }
}
