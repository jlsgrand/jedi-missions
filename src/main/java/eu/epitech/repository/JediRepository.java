package eu.epitech.repository;

import eu.epitech.model.Jedi;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class JediRepository extends InMemoryRepository<Jedi> {
    @Override
    public Jedi save(Jedi jedi) {
        if (jedi.id() == null) {
            jedi = new Jedi(UUID.randomUUID(), jedi.firstName(), jedi.lastName(), jedi.rank(), jedi.isMemberOfCouncil(), jedi.birthDate());
        }
        return super.save(jedi);
    }
}
