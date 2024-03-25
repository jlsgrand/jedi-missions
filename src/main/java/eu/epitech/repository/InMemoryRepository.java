package eu.epitech.repository;

import eu.epitech.model.Entity;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryRepository<E extends Entity> {
    List<E> entities = new ArrayList<>();

    public E save(E entityToSave) {
        Optional<E> entity = findById(entityToSave.id());

        entity.ifPresent(e -> entities.remove(e));
        entities.add(entityToSave);

        return entityToSave;
    }

    public void deleteById(UUID idToDelete) {
        Optional<E> entity = findById(idToDelete);
        if (entity.isPresent()) {
            entities.remove(entity.get());
        } else {
            throw new NotFoundException("L'entité : " + idToDelete + "n'existe pas et ne peux pas être supprimée");
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
