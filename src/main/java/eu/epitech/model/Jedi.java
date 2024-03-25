package eu.epitech.model;

import java.time.LocalDate;
import java.util.UUID;

public record Jedi(UUID id, String firstName, String lastName, Rank rank, boolean isMemberOfCouncil,
                   LocalDate birthDate) implements Entity {
}