import io.restassured.response.Response;
import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Post_Create_Test extends BaseTest {

    @Test
    public void deve_Criar_Usuario() {
        String corpo = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1") // header da API
                .contentType("application/json")
                .body(corpo)
                .when()
                .post("/api/user") // endpoint de criação
                .then()
                .statusCode(201) // valida que criou o usuário
                .body("name", equalTo("morpheus")) // valida o campo name
                .body("job", equalTo("leader")) // valida o campo job
                .body("id", notNullValue()) // garante que retornou um id
                .body("createdAt", notNullValue()) // garante que retornou createdAt
                .log().all()
                .extract()
                .response();
        // Validações extras com JUnit (opcional)
        assertEquals("morpheus", response.jsonPath().getString("name"));
        assertEquals("leader", response.jsonPath().getString("job"));
    }
}