package bdd.stepdefinitions;

import bdd.config.RestApiApplicationIT;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static bdd.utils.PedidoHelper.gerarAdicionaProdutoAoPedidoRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.greaterThan;

public class PedidoStepDefinitions extends RestApiApplicationIT {
    private Response response;
    private String cpf;
    private Long id;
    private String baseUrl = "http://localhost:8090/api/v1/pedidos";

    @Dado("que eu tenho um CPF para Iniciar um Pedido")
    public void queEuTenhoUmCpfParaIniciarUmPedido() {
        cpf = "91207746053";
    }

    @Quando("eu envio uma requisição para iniciar pedido com cpf")
    public void euEnvioUmaRequisicaoPOSTComCpf() {
        response = given()
                .contentType("application/json")
                .param("cpf", cpf)
                .when()
                .post(baseUrl);
    }

    @Então("o pedido com cpf deve ser criado com sucesso status 201")
    public void oPedidoDeveSerCriadoComCpf() {
        response.then().statusCode(201);
    }

    @Dado("que eu quero Iniciar um Pedido sem CPF")
    public void queEuQueroIniciarUmPedidoSemCpf() {
    }

    @Quando("eu envio uma requisição para iniciar pedido sem cpf")
    public void euEnvioUmaRequisicaoPOSTSemCpf() {
        response = given()
                .contentType("application/json")
                .when()
                .post(baseUrl);
    }

    @Então("o pedido sem cpf deve ser criado com sucesso status 201")
    public void oPedidoDeveSerCriadoSemCpf() {
        response.then().statusCode(201);
    }

    @Dado("que eu busco todos os pedidos")
    public void queEuBuscoTodosOsPedidos() {
        given()
                .contentType("application/json")
                .when()
                .post(baseUrl);
    }

    @Quando("eu envio uma requisição para listar todos os pedidos")
    public void euEnvioUmaRequisicaoGET() {
        response = when().get(baseUrl);
    }

    @Então("uma listagem de pedidos deve ser retornado com sucesso status 200")
    public void aListagemDePedidosDeveSerRetornadaComSucesso200() {
        response.then().statusCode(200).body("size()", greaterThan(0));
    }

    @Dado("que eu busco os pedidos na fila")
    public void queEuBuscoTodosOsPedidosNaFila() {
    }

    @Quando("eu envio uma requisição para listar os pedidos na fila")
    public void euEnvioUmaRequisicaoGETNaFila() {
        response = when().get(baseUrl + "/fila");
    }

    @Então("uma listagem de pedidos na fila deve ser retornado com sucesso status 200")
    public void aFilaDeveSerRetornadaComSucesso200() {
        response.then().statusCode(200).body("size()", greaterThan(0));
    }


    @Dado("que eu adiciono um produto a um pedido")
    public void queEuAdicionoUmProdutoAUmPedido() {
        Integer idInt = given()
                .contentType("application/json")
                .when()
                .post(baseUrl)
                .then()
                .extract()
                .path("id");
        id = idInt.longValue();
    }

    @Quando("eu envio uma requisição para adicionar produto")
    public void euEnvioUmaRequisicaoPATCHParaAdicionarProduto() {
        response = given()
                .contentType("application/json")
                .when()
                .body(gerarAdicionaProdutoAoPedidoRequest())
                .patch(baseUrl + "/" + id);
    }

    @Então("o produto deve ser adicionado com sucesso status 200")
    public void oProdutoDeveSerAdicionadoComSucesso200() {
        response.then().statusCode(200);
    }


    @Dado("que eu removo um produto de um pedido")
    public void queEuRemovoUmProdutoAUmPedido() {
        Integer idInt = given()
                .contentType("application/json")
                .when()
                .post(baseUrl)
                .then()
                .extract()
                .path("id");
        id = idInt.longValue();
        given()
                .contentType("application/json")
                .when()
                .body(gerarAdicionaProdutoAoPedidoRequest())
                .patch(baseUrl + "/" + id);
    }

    @Quando("eu envio uma requisição para remover produto")
    public void euEnvioUmaRequisicaoPATCHParaRemoverProduto() {
        response = given()
                .contentType("application/json")
                .when()
                .patch(baseUrl + "/" + id + "/produtos/" + 1);
    }

    @Então("o produto deve ser removido com sucesso status 200")
    public void oProdutoDeveSerRemovidoComSucesso200() {
        response.then().statusCode(200);
    }

    @Dado("que eu fecho um pedido")
    public void queEuFechoUmPedido() {
        Integer idInt = given()
                .contentType("application/json")
                .when()
                .post(baseUrl)
                .then()
                .extract()
                .path("id");
        id = idInt.longValue();
        given()
                .contentType("application/json")
                .when()
                .body(gerarAdicionaProdutoAoPedidoRequest())
                .patch(baseUrl + "/" + id);
    }

    @Quando("eu envio uma requisição para fechar pedido")
    public void euEnvioUmaRequisicaoPATCHParaFecharPedido() {
        response = given()
                .contentType("application/json")
                .when()
                .patch(baseUrl + "/" + id + "/fecha");
    }

    @Então("o pedido deve ser fechado com sucesso status 200")
    public void oPedidoDeveSerFechadoComSucesso200() {
        response.then().statusCode(200);
    }

    @Dado("que eu avanço o status de pedido")
    public void queEuAvancoUmPedido() {
        id = 1000L;
    }

    @Quando("eu envio uma requisição para avançar pedido")
    public void euEnvioUmaRequisicaoPATCHParaAvancarPedido() {
        response = given()
                .contentType("application/json")
                .when()
                .patch(baseUrl + "/" + id + "/avanca");
    }

    @Então("o status do pedido deve ser avançado com sucesso status 200")
    public void oStatusDeveSerAvancadoComSucesso200() {
        response.then().statusCode(200);
    }
}
