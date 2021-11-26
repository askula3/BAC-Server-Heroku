package ba.bac.server.service.implementation;

import ba.bac.server.exceptions.UserServiceException;
import ba.bac.server.io.entity.NotableIndividualEntity;
import ba.bac.server.io.entity.UserEntity;
import ba.bac.server.io.repository.NotableIndividualRepository;
import ba.bac.server.service.NotableIndividualService;
import ba.bac.server.shared.dto.NotableIndividualDto;
import ba.bac.server.shared.dto.UserDto;
import ba.bac.server.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("notableIndividualService")
public class NotableIndividualServiceImpl implements NotableIndividualService {
    @Autowired
    NotableIndividualRepository notableIndividualRepository;

    @Override
    public NotableIndividualDto createIndividual(String country, NotableIndividualDto individualDetails) {
        if (notableIndividualRepository.findByCountryAndFirstNameAndLastName(
                country, individualDetails.getFirstName(), individualDetails.getLastName()) != null)
            throw new RuntimeException("Record already exists!");

        ModelMapper modelMapper = new ModelMapper();
        NotableIndividualEntity notableIndividualEntity = modelMapper.map(individualDetails, NotableIndividualEntity.class);

        notableIndividualEntity.setCountry(country);
        notableIndividualEntity.setFirstName(individualDetails.getFirstName());
        notableIndividualEntity.setLastName(individualDetails.getLastName());
        notableIndividualEntity.setCurrentOccupation(individualDetails.getCurrentOccupation());
        notableIndividualEntity.setImportance(individualDetails.getImportance());
        notableIndividualEntity.setNotes(individualDetails.getNotes());
        notableIndividualEntity.setLastUpdated(new Date(Calendar.getInstance().getTimeInMillis()));


        NotableIndividualEntity storedIndividualDetails = notableIndividualRepository.save(notableIndividualEntity);

        NotableIndividualDto returnValue  = modelMapper.map(storedIndividualDetails, NotableIndividualDto.class);

        return returnValue;
    }

    @Override
    public NotableIndividualDto getIndividual(String country, long id) {
        NotableIndividualEntity notableIndividualEntity = notableIndividualRepository.findByCountryAndId(country, id);

        if (notableIndividualEntity == null)
            throw new RuntimeException("Individual does not exist!");


        NotableIndividualDto returnValue = new NotableIndividualDto();
        BeanUtils.copyProperties(notableIndividualEntity, returnValue);

        return returnValue;
    }

    @Override
    public NotableIndividualDto updateIndividual(String country, long id, NotableIndividualDto individual) {
        NotableIndividualDto returnValue = new NotableIndividualDto();

        NotableIndividualEntity notableIndividualEntity = notableIndividualRepository.findByCountryAndId(country, id);

        if (notableIndividualEntity == null)
            throw new RuntimeException("Individual does not exist!");

        notableIndividualEntity.setFirstName(individual.getFirstName());
        notableIndividualEntity.setLastName(individual.getLastName());
        notableIndividualEntity.setCurrentOccupation(individual.getCurrentOccupation());
        notableIndividualEntity.setImportance(individual.getImportance());
        notableIndividualEntity.setNotes(individual.getNotes());
        notableIndividualEntity.setLastUpdated(new Date(Calendar.getInstance().getTimeInMillis()));

        NotableIndividualEntity updatedIndividualDetails = notableIndividualRepository.save(notableIndividualEntity);
        returnValue = new ModelMapper().map(updatedIndividualDetails, NotableIndividualDto.class);

        return returnValue;
    }

    @Override
    public Page<NotableIndividualEntity> getAllIndividualsFromCountry(String country, int page, int limit, String sort) {
        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sort).ascending());

        return notableIndividualRepository.findAllByCountry(country, pageableRequest);
    }

//    @Override
//    public List<NotableIndividualDto> getAllIndividualsFromCountry(String country, int page, int limit) {
//        List<NotableIndividualDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//
//        Page<NotableIndividualEntity> usersPage = notableIndividualRepository.findAllByCountry(country, pageableRequest);
//        List<NotableIndividualEntity> individual = usersPage.getContent();
//
//        for (NotableIndividualEntity notableIndividualEntity : individual) {
//            NotableIndividualDto notableIndividualDto = new NotableIndividualDto();
//            BeanUtils.copyProperties(notableIndividualEntity, notableIndividualDto);
//            returnValue.add(notableIndividualDto);
//        }
//
//        return returnValue;
//    }
}
