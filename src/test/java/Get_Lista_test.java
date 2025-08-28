import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get_Lista_test extends BaseTest {

    @Test
    public void deve_Listar_Todos_Usuarios() {
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .when()
                .get(Endpoints.TODOS_USUARIO) // endpoint
                .then()
                .statusCode(200) // valida status 200
                .log().all()     // log completo da resposta
                .extract()
                .response();
        int qtdUsuarios = response.jsonPath().getList("data").size();
        assertEquals("Quantidade de usuários diferente de 6!", 6, qtdUsuarios);
        assertEquals("michael.lawson@reqres.in", response.jsonPath().getString("data[0].email"));
    }

    @Test
    public void deve_Listar_Usuario_Especifico_Id() {
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .when()
                .get(Endpoints.USUARIO_ID) // endpoint
                .then()
                .statusCode(200) // valida status 200
                .log().all()     // log completo da resposta
                .extract()
                .response();
        // 🔹 Valida se o email é exatamente o esperado
        String email = response.jsonPath().getString("data.email");
        assertEquals("Email incorreto!", "janet.weaver@reqres.in", email);
    }


    @Test
    public void deve_Listar_Usuario_Inexistente() {
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .when()
                .get(Endpoints.USUARIO_INEXISTENTE) // endpoint
                .then()
                .statusCode(404) // valida status 404
                .log().all()     // log completo da resposta
                .extract()
                .response();
        String body = response.getBody().asString();
        assertEquals("{}", body);
    }

    @Test
    public void deve_Listar_Recursos() {
        RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .when()
                .get(Endpoints.RECURSOS) // endpoint
                .then()
                .statusCode(200) // valida status 200
                .body("page", equalTo(1)) // valida página
                .body("total", equalTo(12)) // valida total
                .body("data[0].name", equalTo("cerulean")) // valida nome do primeiro recurso
                .body("data[1].id", equalTo(2))
                .log().all()     // log completo da resposta
                .extract()
                .response();
    }

    @Test
    public void deve_Listar_Recursos_Especifico() {
        RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .when()
                .get(Endpoints.RECURSO_ID) // endpoint
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2)) // valida o ID
                .body("data.name", equalTo("fuchsia rose")) // valida o nome
                .body("data.year", equalTo(2001))// valida status 200
                .log().all()     // log completo da resposta
                .extract()
                .response();
    }
}