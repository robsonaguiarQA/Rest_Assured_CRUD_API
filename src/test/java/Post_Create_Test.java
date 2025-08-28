import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Post_Create_Test extends BaseTest {

    @Test
    public void deve_Criar_Usuario() {
        String corpo = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response =
                given()
                        .body(corpo)
                        .when()
                        .post(Endpoints.REGISTRANDO_USUARIO)
                        .then()
                        .statusCode(201)
                        .body("name", equalTo("morpheus"))
                        .body("job", equalTo("leader"))
                        .body("id", notNullValue())
                        .body("createdAt", notNullValue())
                        .log().all()
                        .extract()
                        .response();
        assertEquals("morpheus", response.jsonPath().getString("name"));
        assertEquals("leader", response.jsonPath().getString("job"));
    }
}