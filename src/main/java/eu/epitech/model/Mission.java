package eu.epitech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "assignation",
            joinColumns = {@JoinColumn(name = "mission_id")},
            inverseJoinColumns = {@JoinColumn(name = "jedi_id")}
    )
    private Set<Jedi> jedis;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Jedi> getJedis() {
        return jedis;
    }

    public void setJedis(Set<Jedi> jedis) {
        this.jedis = jedis;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate end) {
        this.endDate = end;
    }
}
