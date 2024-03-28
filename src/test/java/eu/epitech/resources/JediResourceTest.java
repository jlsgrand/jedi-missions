package eu.epitech.resources;

import eu.epitech.dto.JediDto;
import eu.epitech.model.Rank;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class JediResourceTest {

    private final static String UUID_JEDI_EXISTING = "070629b7-43e8-4ef3-b41f-1243f1710869";
    private final static String UUID_JEDI_NOT_EXISTING = "ff40705b-4322-44ed-a3f9-81cc03c13a60";

    @Test
    void getJedisShouldBeOk() {
        given()
                .when().get("/api/v1/jedis")
                .then()
                .statusCode(200);
    }

    @Test
    void getJediByIdNotExistingShouldBeKo() {
        given()
                .when().get("/api/v1/jedis/" + UUID_JEDI_NOT_EXISTING)
                .then()
                .statusCode(404);
    }

    @Test
    void getJediByIdExistingShouldBeOk() {
        given()
                .when().get("/api/v1/jedis/" + UUID_JEDI_EXISTING)
                .then()
                .statusCode(200)
                .body("firstName", is("Obi Wan"));
    }

    @Test
    void putJediWithNoIdInPathShouldBeKO() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().put("/api/v1/jedis")
                .then()
                .statusCode(405);
    }

    @Test
    void putJediWithIdButJediNullShouldBeKo() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when().put("/api/v1/jedis/" + UUID_JEDI_NOT_EXISTING)
                .then()
                .statusCode(400);
    }

    @Test
    void putJediWithIdAndJediShouldBeOk() {
        String maceId = "0227a505-4a53-467b-9d19-498865330e1a";
        JediDto maceWindu = new JediDto("Mace", "Windu", Rank.MASTER, true, LocalDate.of(100, 2, 1));
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(maceWindu)
                .when().put("/api/v1/jedis/" + maceId)
                .then()
                .statusCode(200);
    }
}
