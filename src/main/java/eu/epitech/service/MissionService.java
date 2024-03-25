package eu.epitech.service;

import eu.epitech.model.Mission;
import eu.epitech.repository.MissionRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        else throw new NotFoundException("La mission : " + id + " n'existe pas");
    }

    public void assignJediToMission(UUID id, UUID jediId) {
        Mission missionToAssign = missionRepository.findById(id).orElseThrow(() -> new NotFoundException("La mission : " + id + " n'existe pas, impossible d'y assigner des jedis"));

        Log.debugv("La mission a assigner {0} a été trouvée", missionToAssign.name());

        List<Mission> missionsWithJedi = missionRepository.findAll().stream()
                .filter(mission -> mission.jedis().stream().anyMatch(jedi -> jedi.id().equals(jediId))).toList();

        Log.debugv("Les mission où le jedi {0} est déjà assigné sont : {1}", jediId, missionsWithJedi.toString());


        boolean noOverlap = missionsWithJedi.stream()
                .allMatch(mission -> mission.end().isBefore(missionToAssign.start())
                        || mission.start().isAfter(missionToAssign.end()));

        if (noOverlap) {
            var newJedis = new ArrayList<>(missionToAssign.jedis());
            newJedis.add(jediService.getJedi(jediId));
            missionRepository.save(new Mission(id, missionToAssign.name(), missionToAssign.description(), newJedis,
                    missionToAssign.start(), missionToAssign.end()));

            Log.info("Pas de contrainte de planning pour le jedi, il peut être assigné");
        } else {
            throw new BadRequestException("Le jedi est déjà assigné à une mission sur cette période");
        }
    }
}
