package ba.bac.server.io.repository;

import ba.bac.server.io.entity.MediaMonitoringEntity;
import ba.bac.server.io.entity.NotableIndividualEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MediaMonitoringRepository extends PagingAndSortingRepository<MediaMonitoringEntity, Long> {
    MediaMonitoringEntity findByCategory(String category);
    MediaMonitoringEntity findByCategoryAndId(String category, long id);
    MediaMonitoringEntity findByCategoryAndLink(String category, String link);
    Page<MediaMonitoringEntity> findAllByCategory(String country, Pageable pageable);

}
