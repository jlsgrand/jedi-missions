package eu.epitech.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.epitech.model.Jedi;
import eu.epitech.repository.JediRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JediService {

    private JediRepository jediRepository;

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
        else return null;
    }

    public Jedi updateJedi(Jedi jedi) {
        return jediRepository.save(jedi);
    }

    public void deleteJedi(UUID id) {
        jediRepository.deleteById(id);
    }
}
