package eu.epitech.resources;

import eu.epitech.model.Jedi;
import eu.epitech.model.Mission;
import eu.epitech.model.Rank;
import eu.epitech.service.JediService;
import eu.epitech.service.MissionService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MissionResourceTest {
    @Inject
    JediService jediService;

    @Inject
    MissionService missionService;

    @Test
    void assignMissionNotExistingShouldBeKo() {
        Jedi yoda = new Jedi(null, "yoda", "?", Rank.GRAND_MASTER, true, LocalDate.of(100, 2, 1));
        Jedi yodaSaved = jediService.createJedi(yoda);

        given()
                .when().put("/api/v1/missions/" + yodaSaved.id() + "/jedis/" + yodaSaved.id())
                .then()
                .statusCode(404);
    }

    @Test
    void assignMissionWithJediNotExistingShouldBeKo() {
        Mission killPalpatine = new Mission(null, "Kill Palpatine", "", null, LocalDate.now().minusYears(10), LocalDate.now().minusYears(2));
        Mission killPalpatineSave = missionService.createMission(killPalpatine);

        given()
                .when().put("/api/v1/missions/" + killPalpatineSave.id() + "/jedis/" + killPalpatineSave.id())
                .then()
                .statusCode(404);
    }

    @Test
    void assignMissionWithJediNotAvailableExistingShouldBeKo() {
        Jedi yoda = new Jedi(null, "yoda", "?", Rank.GRAND_MASTER, true, LocalDate.of(100, 2, 1));
        Jedi yodaSaved = jediService.createJedi(yoda);

        Mission killPalpatine = new Mission(null, "Kill Palpatine", "", null, LocalDate.now().minusYears(10), LocalDate.now().minusYears(2));
        Mission killPalpatineSave = missionService.createMission(killPalpatine);

        given()
                .when().put("/api/v1/missions/" + killPalpatineSave.id() + "/jedis/" + yodaSaved.id())
                .then()
                .statusCode(204);

        given()
                .when().put("/api/v1/missions/" + killPalpatineSave.id() + "/jedis/" + yodaSaved.id())
                .then()
                .statusCode(400);
    }

    @Test
    void assignMissionShouldBeOk() {
        Jedi yoda = new Jedi(null, "yoda", "?", Rank.GRAND_MASTER, true, LocalDate.of(100, 2, 1));
        Jedi yodaSaved = jediService.createJedi(yoda);

        Mission killPalpatine = new Mission(null, "Kill Palpatine", "", null, LocalDate.now().minusYears(10), LocalDate.now().minusYears(2));
        Mission killPalpatineSave = missionService.createMission(killPalpatine);

        given()
                .when().put("/api/v1/missions/" +killPalpatineSave.id() + "/jedis/" + yodaSaved.id())
                .then()
                .statusCode(204);
    }
}
