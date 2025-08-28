import io.restassured.RestAssured;
import org.junit.Test;

public class HealthCheckTest extends BaseTest {

    @Test
    public void healthCheckReqres() {
        RestAssured
                .when()
                .get(Endpoints.HEALTH)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();
    }
}