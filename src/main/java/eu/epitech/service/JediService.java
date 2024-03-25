package eu.epitech.service;

import eu.epitech.model.Jedi;
import eu.epitech.repository.JediRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class JediService {

    private final JediRepository jediRepository;

    @Inject
    public JediService(JediRepository jediRepository) {
        this.jediRepository = jediRepository;
    }

    public Jedi createJedi(Jedi jedi) {
        return jediRepository.save(jedi);
    }

    public List<Jedi> getJedis() {
        return jediRepository.findAll();
    }

    public Jedi getJedi(UUID id) {
        Optional<Jedi> jedi = jediRepository.findById(id);

        if (jedi.isPresent()) return jedi.get();
        else throw new NotFoundException("Le jedi : " + id + "n'existe pas");
    }

    public Jedi updateJedi(Jedi jedi) {
        return jediRepository.save(jedi);
    }

    public void deleteJedi(UUID id) {
        jediRepository.deleteById(id);
    }
}
