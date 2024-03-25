package eu.epitech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.epitech.model.Mission;
import eu.epitech.repository.MissionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MissionService {
    private final MissionRepository missionRepository;
    private final JediService jediService;

    @Inject
    public MissionService(MissionRepository missionRepository, JediService jediService) {
        this.missionRepository = missionRepository;
        this.jediService = jediService;
    }

    public void deleteMission(UUID id) {
        this.missionRepository.deleteById(id);
    }

    public List<Mission> getMissions() {
        return missionRepository.findAll();
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission updateMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission getMission(UUID id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isPresent()) return mission.get();
        else return null;
    }

    public void assignJediToMission(UUID id, UUID jediId) {
        final Optional<Mission> missionOpt = missionRepository.findById(id);

        if (!missionOpt.isPresent()) return;

        Mission missionToAssign = missionOpt.get();

        List<Mission> missionsWithJedi = missionRepository.findAll().stream()
                .filter(mission -> mission.jedis().stream().anyMatch(jedi -> jedi.id().equals(jediId))).toList();

        boolean noOverlap = missionsWithJedi.stream()
                .allMatch(mission -> mission.end().isBefore(missionToAssign.start())
                        || mission.start().isAfter(missionToAssign.end()));
        
        if (noOverlap) {
            var newJedis = new ArrayList<>(missionToAssign.jedis());
            newJedis.add(jediService.getJedi(jediId));
            missionRepository.save(new Mission(id, missionToAssign.name(), missionToAssign.description(), newJedis,
                    missionToAssign.start(), missionToAssign.end()));
        } else {
            // Exception
        }
    }
}
