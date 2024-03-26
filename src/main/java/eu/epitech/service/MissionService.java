package eu.epitech.service;

import eu.epitech.model.Jedi;
import eu.epitech.model.Mission;
import eu.epitech.repository.MissionRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

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
        return missionRepository.findAll().list();
    }

    public Mission createMission(Mission mission) {
        missionRepository.persist(mission);
        return mission;
    }

    public Mission updateMission(Mission mission) {
        missionRepository.persist(mission);
        return mission;
    }

    public Mission getMission(UUID id) {
        Optional<Mission> mission = missionRepository.findByIdOptional(id);
        if (mission.isPresent()) return mission.get();
        else throw new NotFoundException("La mission : " + id + " n'existe pas");
    }

    public void assignJediToMission(UUID id, UUID jediId) {
        Mission missionToAssign = missionRepository.findByIdOptional(id).orElseThrow(() -> new NotFoundException("La mission : " + id + " n'existe pas, impossible d'y assigner des jedis"));

        Log.debugv("La mission a assigner {0} a été trouvée", missionToAssign.getName());

        List<Mission> missionsWithJedi = missionRepository.findAll().stream()
                .filter(mission -> mission.getJedis().stream().anyMatch(jedi -> jedi.getUuid().equals(jediId))).toList();

        Log.debugv("Les mission où le jedi {0} est déjà assigné sont : {1}", jediId, missionsWithJedi.toString());


        boolean noOverlap = missionsWithJedi.stream()
                .allMatch(mission -> mission.getEndDate().isBefore(missionToAssign.getStartDate())
                        || mission.getStartDate().isAfter(missionToAssign.getEndDate()));

        if (noOverlap) {
            Jedi jediToAssign = jediService.getJedi(jediId);
            missionToAssign.getJedis().add(jediToAssign);
            missionRepository.persist(missionToAssign);
            Log.info("Pas de contrainte de planning pour le jedi, il peut être assigné");
        } else {
            throw new BadRequestException("Le jedi est déjà assigné à une mission sur cette période");
        }
    }
}
