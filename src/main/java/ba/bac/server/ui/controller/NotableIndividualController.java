package ba.bac.server.ui.controller;

import ba.bac.server.service.NotableIndividualService;
import ba.bac.server.shared.dto.NotableIndividualDto;
import ba.bac.server.shared.dto.UserDto;
import ba.bac.server.ui.model.request.NotableIndividualRequestModel;
import ba.bac.server.ui.model.request.UserDetailsRequestModel;
import ba.bac.server.ui.model.response.NotableIndividualRest;
import ba.bac.server.ui.model.response.NotableIndividualsRest;
import ba.bac.server.ui.model.response.UserDetailsRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notable-individuals")
public class NotableIndividualController {
    @Autowired
    NotableIndividualService notableIndividualService;

    @PostMapping(path = "/{country}",
            consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public NotableIndividualRest createIndividual(@PathVariable String country, @RequestBody NotableIndividualRequestModel individualDetails){
        NotableIndividualRest returnValue = new NotableIndividualRest();

        ModelMapper modelMapper = new ModelMapper();
        NotableIndividualDto notableIndividualDto = modelMapper.map(individualDetails, NotableIndividualDto.class);

        NotableIndividualDto createdIndividual = notableIndividualService.createIndividual(country, notableIndividualDto);
        returnValue = modelMapper.map(createdIndividual, NotableIndividualRest.class);

        return returnValue;
    }

    @GetMapping(path = "/{country}/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public NotableIndividualRest getIndividual(@PathVariable String country, @PathVariable long id) {
        NotableIndividualRest returnValue = new NotableIndividualRest();

        NotableIndividualDto notableIndividualDto = notableIndividualService.getIndividual(country, id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(notableIndividualDto, NotableIndividualRest.class);

        return returnValue;
    }

    @PutMapping(path = "/{country}/{id}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                                             produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public NotableIndividualRest updateUser(@PathVariable String country, @PathVariable long id,
                                            @RequestBody NotableIndividualRequestModel individualDetails) {
        NotableIndividualRest returnValue = new NotableIndividualRest();

        NotableIndividualDto notableIndividualDto = new NotableIndividualDto();
        notableIndividualDto = new ModelMapper().map(individualDetails, NotableIndividualDto.class);

        NotableIndividualDto updateIndividual = notableIndividualService.updateIndividual(country, id, notableIndividualDto);
        returnValue = new ModelMapper().map(updateIndividual, NotableIndividualRest.class);

        return returnValue;
    }

//    @GetMapping(path = "/{country}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    public List<NotableIndividualsRest> getUsers(@PathVariable String country,
//                                                 @RequestParam(value = "page", defaultValue = "0") int page,
//                                                 @RequestParam(value = "size", defaultValue = "5") int size) {
//        List<NotableIndividualsRest> returnValue = new ArrayList<>();
//
//        List<NotableIndividualDto> users = notableIndividualService.getAllIndividualsFromCountry(country, page, size);
//
//        Type listType = new TypeToken<List<NotableIndividualsRest>>() {
//        }.getType();
//        returnValue = new ModelMapper().map(users, listType);
//
//        return returnValue;
//    }

    @GetMapping(path = "/{country}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Page<?> getIndividuals(@PathVariable String country,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "5") int size,
                                  @RequestParam(value = "sort", defaultValue = "lastUpdated") String sort) {
        return notableIndividualService.getAllIndividualsFromCountry(country, page, size, sort);
    }
}
