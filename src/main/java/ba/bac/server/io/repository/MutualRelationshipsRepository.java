package ba.bac.server.io.repository;

import ba.bac.server.io.entity.MutualRelationshipsEntity;
import org.springframework.data.repository.CrudRepository;

public interface MutualRelationshipsRepository extends CrudRepository<MutualRelationshipsEntity, Long> {
    MutualRelationshipsEntity findByCountry(String country);
}
