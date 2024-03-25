package eu.epitech.resources;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import eu.epitech.model.Jedi;
import eu.epitech.model.Rank;
import eu.epitech.repository.JediRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/jedis")
public class JediResource {

    private JediRepository jediRepository;

    @Inject
    public JediResource(JediRepository jediRepository) {
        this.jediRepository = jediRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Jedi> test() {
        UUID anakinID = UUID.randomUUID();
        Jedi anakin = new Jedi(anakinID, "Anakin", "Skywalker", Rank.KNIGHT, false, LocalDate.now());
        jediRepository.save(anakin);

        Jedi luke = new Jedi(UUID.randomUUID(), "Luke", "Skywalker", Rank.KNIGHT, false, LocalDate.now());
        jediRepository.save(luke);

        jediRepository.deleteById(anakinID);

        return jediRepository.findAll();
    }
}
