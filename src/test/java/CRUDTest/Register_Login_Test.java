package CRUDTest;

import base.BaseTest;
import endpoits.Endpoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Register_Login_Test extends BaseTest {

    @Test
    public void deve_Registrar_Usuario_Sucesso() {
        String corpo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        Response response = given()
                .body(corpo)
                .when()
                .post(Endpoints.REGISTRO_SUCESSO)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue())
                .log().all()
                .extract()
                .response();
        assertNotNull(response.jsonPath().getString("id"), "ID não deveria ser nulo");
        assertNotNull(response.jsonPath().getString("token"), "Token não deveria ser nulo");
    }

    @Test
    public void deve_Retornar_Erro_Registro_Sem_Senha() {
        String corpo = "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";
        Response response =
                given()
                        .body(corpo)
                        .when()
                        .post(Endpoints.REGISTRO_INVALIDO)
                        .then()
                        .statusCode(400)
                        .body("error", equalTo("Missing password"))
                        .log().all()
                        .extract()
                        .response();
        assertEquals("Missing password", response.jsonPath().getString("error"));
    }

    @Test
    public void deve_Fazer_Login_Com_Sucesso() {
        String corpo = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
        Response response =
                given()
                        .body(corpo)
                        .when()
                        .post(Endpoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .body("token", notNullValue())
                        .log().all()
                        .extract()
                        .response();
        assertNotNull(response.jsonPath().getString("token"), "Token não deveria ser nulo");
    }

    @Test
    public void deve_Falhar_No_Login() {
        String corpo = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        Response response =
                given()
                        .body(corpo)
                        .when()
                        .post(Endpoints.LOGIN)
                        .then()
                        .statusCode(400)
                        .body("error", equalTo("Missing password"))
                        .log().all()
                        .extract()
                        .response();
        assertEquals("Missing password", response.jsonPath().getString("error"));
    }
}