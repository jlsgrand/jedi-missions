package eu.epitech.repository;

import eu.epitech.model.Mission;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class MissionRepository extends InMemoryRepository<Mission> {
    @Override
    public Mission save(Mission mission) {
        if (mission.id() == null) {
            mission = new Mission(UUID.randomUUID(), mission.name(), mission.description(), new ArrayList<>(), mission.start(), mission.end());
        }

        return super.save(mission);
    }
}
