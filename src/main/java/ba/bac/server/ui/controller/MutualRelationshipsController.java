package ba.bac.server.ui.controller;

import ba.bac.server.service.MutualRelationshipsService;
import ba.bac.server.shared.dto.MutualRelationshipsDto;
import ba.bac.server.shared.dto.UserDto;
import ba.bac.server.ui.model.request.MutualRelationshipRequestModel;
import ba.bac.server.ui.model.response.MutualRelationshipsRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutual-relationships") //http://localhost:8080/mutual-relationships
public class MutualRelationshipsController {

    @Autowired
    MutualRelationshipsService mutualRelationshipsService;

    @GetMapping(path = "/{country}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public MutualRelationshipsRest getMutualRelationships(@PathVariable String country) {
        MutualRelationshipsRest returnValue = new MutualRelationshipsRest();

        MutualRelationshipsDto mutualRelationshipsDto = mutualRelationshipsService.getMutualRelationshipsByCountry(country);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(mutualRelationshipsDto, MutualRelationshipsRest.class);

        return  returnValue;
    }

    @PutMapping(path = "/{country}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                                        produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public MutualRelationshipsRest updateMutualRelationships(@PathVariable String country, @RequestBody MutualRelationshipRequestModel relationshipsDetails) {
        MutualRelationshipsRest returnValue = new MutualRelationshipsRest();

        MutualRelationshipsDto mutualRelationshipsDto = new MutualRelationshipsDto();
        mutualRelationshipsDto = new ModelMapper().map(relationshipsDetails, MutualRelationshipsDto.class);

        MutualRelationshipsDto updatedMutualRelationships = mutualRelationshipsService.updateMutualRelationships(country, mutualRelationshipsDto);
        returnValue = new ModelMapper().map(updatedMutualRelationships, MutualRelationshipsRest.class);

        return returnValue;
    }

}
