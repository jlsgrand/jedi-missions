package eu.epitech.repository;

import eu.epitech.model.Jedi;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class JediRepository implements PanacheRepositoryBase<Jedi, UUID> {
}
