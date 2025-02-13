package bdd.config;

import com.leodelmiro.pedido.PedidoApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
        "server.port=8090"
})
@CucumberContextConfiguration
@ContextConfiguration(classes = {PedidoApplication.class})
@ActiveProfiles("test")
@Testcontainers
public class RestApiApplicationIT {

    @Container
    private static final LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack"))
            .withExposedPorts(4567)
            .withServices(LocalStackContainer.Service.SQS)
            .withCopyToContainer(MountableFile.forClasspathResource("init-aws", 0744), "/etc/localstack/init/ready.d")
            .waitingFor(Wait.forLogMessage(".*Ready\\.\n", 1));

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13-alpine")
            .withExposedPorts(5432)
            .withDatabaseName("pedido")
            .withUsername("postgres")
            .withPassword("1234")
            .withInitScript("data.sql");

    @Container
    private static final WireMockContainer wiremockServer = new WireMockContainer(DockerImageName.parse("wiremock/wiremock"))
            .withExposedPorts(8888)
            .withMappingFromResource("get-cliente.json")
            .withMappingFromResource("get-produto.json")
            .withMappingFromResource("post-pagamentos.json");


    @LocalServerPort
    private int port;

    static {
        localStack.start();
        wiremockServer.start();
        postgres.start();
    }

    @BeforeEach
    public void setupBefore() throws Exception {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    public void setupAfter() {
        localStack.stop();
        wiremockServer.stop();
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("amazon.sqs.endpoint", localStack::getEndpoint);
        registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
        registry.add("spring.cloud.aws.region.static", localStack::getRegion);
        registry.add("external-apis.cliente.url", wiremockServer::getBaseUrl);
        registry.add("external-apis.produto.url", wiremockServer::getBaseUrl);
        registry.add("external-apis.pagamento.url", wiremockServer::getBaseUrl);
    }


    @Test
    void deveIniciarAplicacaoCorretamente() {
        given()
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("status", equalTo("UP"));
    }
}