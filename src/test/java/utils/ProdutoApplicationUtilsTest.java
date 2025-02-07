package utils;


import com.leodelmiro.pedido.core.domain.Categoria;
import com.leodelmiro.pedido.core.domain.Produto;
import com.leodelmiro.pedido.dataprovider.client.response.ProdutoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoApplicationUtilsTest {
    public static Produto getProduto(long id, String Produto_Teste, String Descrição_Teste) {
        return new Produto(id,
                Produto_Teste,
                Categoria.BEBIDA,
                BigDecimal.ONE,
                Descrição_Teste,
                1L,
                LocalDateTime.now());
    }

    public static ProdutoResponse getProdutoResponse(long tempoDePreparoEmSegundos) {
        return new ProdutoResponse(1L,
                "Produto Teste",
                BigDecimal.ONE,
                "Descrição Teste",
                tempoDePreparoEmSegundos
        );
    }
}
