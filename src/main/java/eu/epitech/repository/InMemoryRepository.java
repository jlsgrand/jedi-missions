package eu.epitech.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.epitech.model.Entity;

public class InMemoryRepository<E extends Entity> {
    List<E> entities = new ArrayList<>();

    public E save(E entityToSave) {
        Optional<E> entity = findById(entityToSave.id());

        if (entity.isPresent()) {
            entities.remove(entity.get());
        }
        entities.add(entityToSave);

        return entityToSave;
    }

    public void deleteById(UUID idToDelete) {
        Optional<E> entity = findById(idToDelete);
        if (entity.isPresent()) {
            entities.remove(entity.get());
        }
    }

    public List<E> findAll() {
        return entities;
    }

    public Optional<E> findById(UUID idToFind) {
        int i = 0;

        while (i < entities.size()) {
            E entity = entities.get(i);
            if (entity.id().equals(idToFind)) {
                return Optional.of(entity);
            }
            i++;
        }

        return Optional.empty();
    }
}
