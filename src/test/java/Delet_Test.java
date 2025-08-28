import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delet_Test extends BaseTest {

    @Test
    public void deve_Deletar_Usuario() {
        Response response =
                 given()
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