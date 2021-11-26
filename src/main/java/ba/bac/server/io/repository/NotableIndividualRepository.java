package ba.bac.server.io.repository;

import ba.bac.server.io.entity.CountryEntity;
import ba.bac.server.io.entity.NotableIndividualEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NotableIndividualRepository extends PagingAndSortingRepository<NotableIndividualEntity, Long> {
    NotableIndividualEntity findByCountry(String country);
    NotableIndividualEntity findByCountryAndId(String country, long id);
    NotableIndividualEntity findByCountryAndFirstNameAndLastName(String country, String firstName, String lastName);
    Page<NotableIndividualEntity> findAllByCountry(String country, Pageable pageable);

}
