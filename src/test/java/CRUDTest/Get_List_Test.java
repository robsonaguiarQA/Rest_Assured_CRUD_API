package CRUDTest;

import base.BaseTest;
import endpoits.Endpoints;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get_List_Test extends BaseTest {

    @Test
    public void deve_Listar_Todos_Usuarios() {
        Response response =
                given()
                        .when()
                        .get(Endpoints.TODOS_USUARIO) // endpoint
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        int qtdUsuarios = response.jsonPath().getList("data").size();
        assertEquals("Quantidade de usuários diferente de 6!", 6, qtdUsuarios);
        assertEquals("michael.lawson@reqres.in", response.jsonPath().getString("data[0].email"));
    }

    @Test
    public void deve_Listar_Usuario_Especifico_Id() {
        Response response =
                given()
                        .when()
                        .get(Endpoints.USUARIO_ID)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        String email = response.jsonPath().getString("data.email");
        assertEquals("Email incorreto!", "janet.weaver@reqres.in", email);
    }

    @Test
    public void deve_Listar_Usuario_Inexistente() {
        Response response =
                given()
                        .when()
                        .get(Endpoints.USUARIO_INEXISTENTE)
                        .then()
                        .statusCode(404)
                        .log().all()
                        .extract()
                        .response();
        String body = response.getBody().asString();
        assertEquals("{}", body);
    }

    @Test
    public void deve_Listar_Recursos() {
        given()
                .when()
                .get(Endpoints.RECURSOS)
                .then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("total", equalTo(12))
                .body("data[0].name", equalTo("cerulean"))
                .body("data[1].id", equalTo(2))
                .log().all()
                .extract()
                .response();
    }

    @Test
    public void deve_Listar_Recursos_Especifico() {
        given()
                .when()
                .get(Endpoints.RECURSO_ID)
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.name", equalTo("fuchsia rose"))
                .body("data.year", equalTo(2001))
                .log().all()
                .extract()
                .response();
    }
}