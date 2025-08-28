import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.requestSpecification = RestAssured.given()
                .header("x-api-key", "reqres-free-v1")
                .contentType("application/json");
    }
}