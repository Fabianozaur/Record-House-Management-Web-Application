package recordhouse.shared.repository.jpa;

import design.domain.BaseEntity;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class EntityRepository<ID, E extends BaseEntity<ID>> implements
        recordhouse.shared.repository.Repository<ID, E> {

    protected EntityRepository(CrudRepository<EntityModelOf<E>, ID> repository, EntityModelConverter<E> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    protected CrudRepository<EntityModelOf<E>, ID> repository;
    protected EntityModelConverter<E> converter;

    @Override
    public Optional<E> findOne(ID id) {
        return repository.findById(id)
                .map(tm -> converter.ConvertFrom(tm));
    }

    @Override
    public Iterable<E> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map((tm) -> converter.ConvertFrom(tm))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<E> save(E entity) {
        var model = converter.ConvertTo(entity);
        return Optional.of(repository.save(model))
                .map((tm) -> converter.ConvertFrom(tm));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<E> delete(ID id) {
        var result = repository.findById(id);
        repository.delete(result.orElseThrow(
                () -> new EntityRepositoryError(String.format("Entity with id %s not found", id))
        ));
        return result
                .map((tm) -> converter.ConvertFrom(tm));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<E> update(E entity) {
        return Optional.of(repository.save(converter.ConvertTo(entity)))
                .map((tm) -> converter.ConvertFrom(tm));
    }
}
