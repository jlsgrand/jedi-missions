package eu.epitech.service;

import eu.epitech.dto.JediDto;
import eu.epitech.model.Jedi;
import eu.epitech.repository.JediRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Jedi createJedi(JediDto jedi) {
        Jedi jediToSave = new Jedi(null, jedi.getFirstName(), jedi.getLastName(), jedi.getRank(), jedi.isMemberOfCouncil(), jedi.getBirthDate());
        jediRepository.persist(jediToSave);
        return jediToSave;
    }

    public List<Jedi> getJedis() {
        return jediRepository.findAll().list();
    }

    public Jedi getJedi(UUID id) {
        Optional<Jedi> jedi = jediRepository.findByIdOptional(id);

        if (jedi.isPresent()) return jedi.get();
        else throw new NotFoundException("Le jedi : " + id + "n'existe pas");
    }

    @Transactional
    public Jedi updateJedi(UUID jediId, JediDto jedi) {
        Jedi jediToUpdate = jediRepository.findById(jediId);

        jediToUpdate.setBirthDate(jedi.getBirthDate());
        jediToUpdate.setFirstName(jedi.getFirstName());
        jediToUpdate.setLastName(jedi.getLastName());
        jediToUpdate.setRank(jedi.getRank());
        jediToUpdate.setMemberOfCouncil(jedi.isMemberOfCouncil());

        jediRepository.persist(jediToUpdate);
        return jediToUpdate;
    }

    @Transactional
    public void deleteJedi(UUID id) {
        jediRepository.deleteById(id);
    }
}
