import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Delet_Test extends BaseTest {

    @Test
    public void deve_Deletar_Usuario() {
        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete(Endpoints.DELETA_USUARIO)
                .then()
                .statusCode(204)
                .log().all()
                .extract()
                .response();
        assertEquals(204, response.getStatusCode());
    }
}