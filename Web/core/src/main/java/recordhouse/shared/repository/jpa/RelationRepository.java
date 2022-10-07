package recordhouse.shared.repository.jpa;

import design.domain.Composite;
import design.domain.Relation;
import design.domain.RelationConverter;
import design.persistance.jpa.EntityModelConverter;
import design.persistance.jpa.EntityModelOf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import recordhouse.shared.repository.Repository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class RelationRepository<R extends Relation<String, String>> implements Repository<Composite<String, String>, R> {

    private final CrudRepository<EntityModelOf<R>, EntityModelOf<R>> repository;
    private final EntityModelConverter<R> converter;
    private final RelationConverter<R> relationConverter;
    protected RelationRepository(CrudRepository<EntityModelOf<R>, EntityModelOf<R>> repository, EntityModelConverter<R> converter, RelationConverter<R> relationConverter){
        this.repository = repository;
        this.converter = converter;
        this.relationConverter = relationConverter;
    }

    @Override
    public Optional<R> findOne(Composite<String, String> id) {
        return repository.findById(converter.ConvertTo(relationConverter.ConvertTo(id)))
                .map(tm -> converter.ConvertFrom(tm));
    }

    @Override
    public Iterable<R> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map((tm) -> converter.ConvertFrom(tm))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<R> save(R entity) {
        return Optional.of(repository.save(converter.ConvertTo(entity)))
                .map((tm) -> converter.ConvertFrom(tm));
    }

    @Override
    public Optional<R> delete(Composite<String, String> id) {
        var result = repository.findById(converter.ConvertTo(relationConverter.ConvertTo(id)));
        repository.delete(result.orElseThrow(
                () -> new EntityRepositoryError(String.format("Entity with id %s not found", id))
        ));
        return result
                .map((tm) -> converter.ConvertFrom(tm));
    }

    @Override
    public Optional<R> update(R entity) {
        return Optional.of(repository.save(converter.ConvertTo(entity)))
                .map((tm) -> converter.ConvertFrom(tm));
    }
}
