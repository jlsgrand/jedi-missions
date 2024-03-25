package eu.epitech.resources;

import java.util.List;
import java.util.UUID;

import eu.epitech.model.Jedi;
import eu.epitech.service.JediService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/jedis")
public class JediResource {

    private JediService jediService;

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
    public Jedi postJedi(Jedi jedi) {
        return jediService.createJedi(jedi);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Jedi putJedi(@PathParam("id") UUID id, Jedi jedi) {
        // si j'ai différence entre id et jedi.id
        return jediService.updateJedi(jedi);
    }

    @DELETE
    @Path("/{id}")
    public void deletJedi(@PathParam("id") UUID id) {
        jediService.deleteJedi(id);
    }
}
