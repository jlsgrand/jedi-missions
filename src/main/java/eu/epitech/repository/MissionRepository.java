package eu.epitech.repository;

import eu.epitech.model.Mission;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class MissionRepository implements PanacheRepositoryBase<Mission, UUID> {
}
