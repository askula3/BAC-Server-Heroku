package ba.bac.server.service;

import ba.bac.server.shared.dto.NotableIndividualDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NotableIndividualService {
    NotableIndividualDto createIndividual(String country, NotableIndividualDto individualDetails);
    NotableIndividualDto getIndividual(String country, long id);
    NotableIndividualDto updateIndividual(String country, long id, NotableIndividualDto notableIndividualDto);
//    List<NotableIndividualDto> getAllIndividualsFromCountry(String country, int page, int limit);
    Page<?> getAllIndividualsFromCountry(String country, int page, int size, String sort);
}
