package eu.epitech.resources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MissionResourceTest {

    private final static String UUID_JEDI_EXISTING_1 = "070629b7-43e8-4ef3-b41f-1243f1710869";
    private final static String UUID_JEDI_EXISTING_2 = "0227a505-4a53-467b-9d19-498865330e1a";
    private final static String UUID_NOT_EXISTING = "ff40705b-4322-44ed-a3f9-81cc03c13a60";
    private final static String UUID_MISSION_EXISTING_1 = "ab1ee492-7c48-4df3-9767-e2b0c73f3864";
    private final static String UUID_MISSION_EXISTING_2 = "0227a505-7c48-4df3-9767-498865330e1a";

    @Test
    void assignMissionNotExistingShouldBeKo() {
        given()
                .when().put("/api/v1/missions/" + UUID_NOT_EXISTING + "/jedis/" + UUID_JEDI_EXISTING_1)
                .then()
                .statusCode(404);
    }

    @Test
    void assignMissionWithJediNotExistingShouldBeKo() {
        given()
                .when().put("/api/v1/missions/" + UUID_MISSION_EXISTING_1 + "/jedis/" + UUID_NOT_EXISTING)
                .then()
                .statusCode(404);
    }

    @Test
    void assignMissionWithJediNotAvailableExistingShouldBeKo() {
        given()
                .when().put("/api/v1/missions/" + UUID_MISSION_EXISTING_1 + "/jedis/" + UUID_JEDI_EXISTING_1)
                .then()
                .statusCode(204);

        given()
                .when().put("/api/v1/missions/" + UUID_MISSION_EXISTING_1 + "/jedis/" + UUID_JEDI_EXISTING_1)
                .then()
                .statusCode(400);
    }

    @Test
    void assignMissionShouldBeOk() {
        given()
                .when().put("/api/v1/missions/" + UUID_MISSION_EXISTING_2 + "/jedis/" + UUID_JEDI_EXISTING_2)
                .then()
                .statusCode(204);
    }
}
