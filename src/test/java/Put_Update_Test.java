import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put_Update_Test extends BaseTest {

    @Test
    public void deve_Atualizar_Usuario() {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        Response response =
                given()
                        .body(body)
                        .when()
                        .put(Endpoints.ATUALIZAR_USUARIO)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        assertEquals("morpheus", response.jsonPath().getString("name"));
        assertEquals("zion resident", response.jsonPath().getString("job"));
    }

    @Test
    public void deve_Atualizar_Usuario_PATCH() {
        String body = "{\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        Response response =
                given()
                        .body(body)
                        .when()
                        .patch(Endpoints.ATUALIZAR_USUARIO)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response();
        String updatedAt = response.jsonPath().getString("updatedAt");
        assertEquals(true, updatedAt != null && !updatedAt.isEmpty());
    }
}