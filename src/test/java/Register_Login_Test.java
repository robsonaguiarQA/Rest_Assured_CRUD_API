import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Register_Login_Test extends BaseTest {

    @Test
    public void deve_Registrar_Usuario_Sucesso() {
        // Corpo da requisição
        String corpo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        // Executa a requisição
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .contentType("application/json")
                .body(corpo)
                .when()
                .post(Endpoints.REGISTRO_SUCESSO) // endpoint de registro
                .then()
                .statusCode(200) // valida que registrou com sucesso
                .body("id", notNullValue())     // garante que retornou um id
                .body("token", notNullValue())  // garante que retornou um token
                .log().all()
                .extract()
                .response();

        // Validações extras com JUnit
        assertNotNull(response.jsonPath().getString("id"), "ID não deveria ser nulo");
        assertNotNull(response.jsonPath().getString("token"), "Token não deveria ser nulo");
    }


        @Test
        public void deve_Retornar_Erro_Registro_Sem_Senha() {
            // Corpo da requisição sem password
            String corpo = "{\n" +
                    "    \"email\": \"sydney@fife\"\n" +
                    "}";

            // Executa a requisição
            Response response = RestAssured
                    .given()
                    .header("x-api-key", "reqres-free-v1") // header da API
                    .contentType("application/json")
                    .body(corpo)
                    .when()
                    .post(Endpoints.REGISTRO_INVALIDO) // mesmo endpoint de registro
                    .then()
                    .statusCode(400) // valida que retornou Bad Request
                    .body("error", equalTo("Missing password")) // valida a mensagem de erro
                    .log().all()
                    .extract()
                    .response();

            // Validações extras com JUnit
            assertEquals("Missing password", response.jsonPath().getString("error"));
        }

    @Test
    public void deve_Fazer_Login_Com_Sucesso() {
        // Corpo da requisição
        String corpo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";

        // Executa a requisição
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .contentType("application/json")
                .body(corpo)
                .when()
                .post(Endpoints.LOGIN) // endpoint de login
                .then()
                .statusCode(200) // valida que logou com sucesso
                .body("token", notNullValue()) // garante que retornou um token
                .log().all()
                .extract()
                .response();

        // Validações extras com JUnit
        assertNotNull(response.jsonPath().getString("token"), "Token não deveria ser nulo");
    }

  @Test
    public void deve_Falhar_No_Login() {
      // Corpo da requisição
      String corpo = "{\n" +
              "    \"email\": \"peter@klaven\"\n" +
              "}";
      // Executa a requisição
      Response response = RestAssured
              .given()
              .header("x-api-key", "reqres-free-v1") // header da API
              .contentType("application/json")
              .body(corpo)
              .when()
              .post(Endpoints.LOGIN) // endpoint de login
              .then()
              .statusCode(400) // valida que logou com sucesso
              .body("error", equalTo("Missing password")) // valida a mensagem de erro
              .log().all()
              .extract()
              .response();
      // Validações extras com JUnit
      assertEquals("Missing password", response.jsonPath().getString("error"));
  }
}