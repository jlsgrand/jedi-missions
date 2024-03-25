package eu.epitech.resources;

import eu.epitech.model.Mission;
import eu.epitech.service.MissionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/missions")
public class MissionResource {

    private final MissionService missionService;

    @Inject
    public MissionResource(MissionService missionService) {
        this.missionService = missionService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mission> getMissions() {
        return missionService.getMissions();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mission getMission(@PathParam("id") UUID id) {
        return missionService.getMission(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Mission postMission(Mission mission) {
        return missionService.createMission(mission);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Mission putMission(@PathParam("id") UUID id, Mission mission) {
        return missionService.updateMission(mission);
    }

    @DELETE
    @Path("/{id}")
    public void deleteMission(@PathParam("id") UUID id) {
        missionService.deleteMission(id);
    }

    @PUT
    @Path("/{idMission}/jedis/{idJedi}")
    public void assignJediToMission(@PathParam("idMission") UUID idMission, @PathParam("idJedi") UUID idJedi) {
        missionService.assignJediToMission(idMission, idJedi);
    }

}
