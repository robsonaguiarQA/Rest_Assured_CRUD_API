import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Delete_Test extends BaseTest{

        @Test
        public void deve_Deletar_Usuario() {
            Response response = RestAssured
                    .given()
                    .header("x-api-key", "reqres-free-v1")
                    .when()
                    .delete(Endpoints.DELETA_USUARIO) // endpoint de delete
                    .then()
                    .statusCode(204) // valida status 204 (No Content)
                    .log().all()
                    .extract()
                    .response();

            // Como a API retorna corpo vazio no 204, só validamos o status
            assertEquals(204, response.getStatusCode());
        }

}