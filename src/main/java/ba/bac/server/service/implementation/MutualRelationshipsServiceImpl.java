package ba.bac.server.service.implementation;

import ba.bac.server.exceptions.UserServiceException;
import ba.bac.server.io.entity.CountryEntity;
import ba.bac.server.io.entity.MutualRelationshipsEntity;
import ba.bac.server.io.entity.UserEntity;
import ba.bac.server.io.repository.CountryRepository;
import ba.bac.server.io.repository.MutualRelationshipsRepository;
import ba.bac.server.service.MutualRelationshipsService;
import ba.bac.server.shared.dto.MutualRelationshipsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mutualRelationshipsService")
public class MutualRelationshipsServiceImpl implements MutualRelationshipsService {
    @Autowired
    MutualRelationshipsRepository mutualRelationshipsRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public MutualRelationshipsDto getMutualRelationshipsByCountry(String country) {
        MutualRelationshipsEntity mutualRelationshipsEntity = mutualRelationshipsRepository.findByCountry(country);

        if (mutualRelationshipsEntity == null)
            throw new RuntimeException("Relationships not found!");

        MutualRelationshipsDto returnValue = new MutualRelationshipsDto();
        BeanUtils.copyProperties(mutualRelationshipsEntity, returnValue);

        return returnValue;
    }

    @Override
    public MutualRelationshipsDto updateMutualRelationships(String country, MutualRelationshipsDto mutualRelationshipsDto) {
        MutualRelationshipsDto returnValue = new MutualRelationshipsDto();

        MutualRelationshipsEntity mutualRelationshipsEntity = mutualRelationshipsRepository.findByCountry(country);
        CountryEntity countryEntity = countryRepository.findByCountry(country);

        if (countryEntity == null) {
            throw new RuntimeException("Country not found!");
        } else if (countryEntity != null && mutualRelationshipsEntity == null ) {
            mutualRelationshipsEntity = new MutualRelationshipsEntity();
            mutualRelationshipsEntity.setCountry(countryEntity.getCountry());
        }

        if (!countryEntity.isDisabled()) {
            mutualRelationshipsEntity.setDetails(mutualRelationshipsDto.getDetails());
            mutualRelationshipsEntity.setTimeline(mutualRelationshipsDto.getTimeline());

            MutualRelationshipsEntity updatedMutualRelationship = mutualRelationshipsRepository.save(mutualRelationshipsEntity);
            returnValue = new ModelMapper().map(updatedMutualRelationship, MutualRelationshipsDto.class);

        } else throw new RuntimeException("Country is disabled!");

        return returnValue;
    }
}
