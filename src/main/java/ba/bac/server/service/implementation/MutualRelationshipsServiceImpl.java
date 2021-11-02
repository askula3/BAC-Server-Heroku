package ba.bac.server.service.implementation;

import ba.bac.server.exceptions.UserServiceException;
import ba.bac.server.io.entity.MutualRelationshipsEntity;
import ba.bac.server.io.entity.UserEntity;
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

    @Override
    public MutualRelationshipsDto getMutualRelationshipsByCountry(String country) {
        MutualRelationshipsEntity mutualRelationshipsEntity = mutualRelationshipsRepository.findByCountry(country);

        if (mutualRelationshipsEntity == null)
            throw new RuntimeException("Relationships error!");

        MutualRelationshipsDto returnValue = new MutualRelationshipsDto();
        BeanUtils.copyProperties(mutualRelationshipsEntity, returnValue);

        return returnValue;
    }

    @Override
    public MutualRelationshipsDto updateMutualRelationships(String country, MutualRelationshipsDto mutualRelationshipsDto) {
        MutualRelationshipsDto returnValue = new MutualRelationshipsDto();

        MutualRelationshipsEntity mutualRelationshipsEntity = mutualRelationshipsRepository.findByCountry(country);

        if (mutualRelationshipsEntity == null) {
            createNewCountry(country);
            mutualRelationshipsEntity = mutualRelationshipsRepository.findByCountry(country);
        }

        mutualRelationshipsEntity.setDetails(mutualRelationshipsDto.getDetails());
        mutualRelationshipsEntity.setTimeline(mutualRelationshipsDto.getTimeline());

        MutualRelationshipsEntity updatedMutualRelationship = mutualRelationshipsRepository.save(mutualRelationshipsEntity);
        returnValue = new ModelMapper().map(updatedMutualRelationship, MutualRelationshipsDto.class);

        return returnValue;
    }

    public void createNewCountry(String country) {
        if (mutualRelationshipsRepository.findByCountry(country) != null)
            throw new RuntimeException("Record already exists");

        MutualRelationshipsEntity mutualRelationshipsEntity = new ModelMapper().map(country, MutualRelationshipsEntity.class);
        mutualRelationshipsEntity.setCountry(country);

        MutualRelationshipsEntity storedMutualRelationship = mutualRelationshipsRepository.save(mutualRelationshipsEntity);
    }
}
