package eu.epitech.resources;

import eu.epitech.dto.JediDto;
import eu.epitech.model.Jedi;
import eu.epitech.service.JediService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/jedis")
public class JediResource {

    private final JediService jediService;

    @Inject
    public JediResource(JediService jediService) {
        this.jediService = jediService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Jedi> getJedis() {
        return jediService.getJedis();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Jedi getJedi(@PathParam("id") UUID id) {
        return jediService.getJedi(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Jedi postJedi(@Valid JediDto jedi) {
        return jediService.createJedi(jedi);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Jedi putJedi(@PathParam("id") UUID id, JediDto jedi) {
        if (id != null && jedi != null) {
            return jediService.updateJedi(id, jedi);
        } else {
            throw new BadRequestException("L'id et le Jedi DTO doivent être spécifiés'");
        }
    }

    @DELETE
    @Path("/{id}")
    public void deleteJedi(@PathParam("id") UUID id) {
        jediService.deleteJedi(id);
    }
}
