package eu.epitech.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record Mission(UUID id, String name, String description, List<Jedi> jedis, LocalDate start,
                      LocalDate end) implements Entity {
}
