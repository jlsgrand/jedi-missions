package eu.epitech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class Jedi {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50)
    private String lastName;
    private Rank rank;

    private boolean isMemberOfCouncil;

    private LocalDate birthDate;

    @ManyToMany(mappedBy = "jedis", fetch = FetchType.EAGER)
    private Set<Mission> missions;

    public Jedi() {
    }

    public Jedi(UUID id, String firstName, String lastName, Rank rank, boolean isMemberOfCouncil, LocalDate birthDate) {
        this.uuid = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.isMemberOfCouncil = isMemberOfCouncil;
        this.birthDate = birthDate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean isMemberOfCouncil() {
        return isMemberOfCouncil;
    }

    public void setMemberOfCouncil(boolean memberOfCouncil) {
        isMemberOfCouncil = memberOfCouncil;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }
}