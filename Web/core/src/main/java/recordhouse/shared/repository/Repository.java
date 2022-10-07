package recordhouse.shared.repository;

import design.domain.BaseEntity;
import design.validation.ValidatorException;

import java.util.Optional;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @author radu.
 */
public interface Repository<ID,
        T extends BaseEntity<ID>> {
    // ID is a TEMPLATE argument, it will represent the KEY for the entity
    // T is a BaseEntity. It's ID template is the same as the first template argumetn
    // ID from repository will be same ID as for the BaseEntity

    /**
     * Find the entity with the given {@code id}.
     *
     * @param id must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException if the given id is null.
     */
    Optional<T> findOne(ID id);
    // Optional, means that it can be null

    /**
     * @return all entities.
     */
    Iterable<T> findAll();
    // Iterable is a collection

    /**
     * Saves the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ValidatorException       if the entity is not valid.
     */
    Optional<T> save(T entity) throws ValidatorException;

    /**
     * Removes the entity with the given id.
     *
     * @param id must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException if the given id is null.
     */
    Optional<T> delete(ID id);

    /**
     * Updates the given entity.
     *
     * @param entity must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     * entity.
     * @throws IllegalArgumentException if the given entity is null.
     * @throws ValidatorException       if the entity is not valid.
     */
    Optional<T> update(T entity) throws ValidatorException;
}
