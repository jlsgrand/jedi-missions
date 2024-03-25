package eu.epitech.model;

import java.util.UUID;

public record Starship(UUID id, String name, int capacity, int speed) implements Entity {
}
