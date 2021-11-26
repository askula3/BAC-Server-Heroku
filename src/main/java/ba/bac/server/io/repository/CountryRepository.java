package ba.bac.server.io.repository;

import ba.bac.server.io.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    CountryEntity findByCountry(String country);
    List<CountryEntity> findAll();
    List<CountryEntity> findAllByRegion(String region);
}
