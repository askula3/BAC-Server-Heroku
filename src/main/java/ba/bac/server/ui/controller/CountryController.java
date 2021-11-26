package ba.bac.server.ui.controller;

import ba.bac.server.service.CountryService;
import ba.bac.server.shared.dto.CountryDto;
import ba.bac.server.shared.dto.MutualRelationshipsDto;
import ba.bac.server.ui.model.request.CountryRequestModel;
import ba.bac.server.ui.model.request.MutualRelationshipRequestModel;
import ba.bac.server.ui.model.response.CountriesRest;
import ba.bac.server.ui.model.response.CountryRest;
import ba.bac.server.ui.model.response.MutualRelationshipsRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    CountryService countryService;

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
                              MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public CountriesRest addNewCountry(@RequestBody CountryRequestModel countryDetails){
        CountriesRest returnValue = new CountriesRest();

        ModelMapper modelMapper = new ModelMapper();
        CountryDto countryDto = modelMapper.map(countryDetails, CountryDto.class);

        CountryDto newCountry = countryService.addNewCountry(countryDto);
        returnValue = modelMapper.map(newCountry, CountriesRest.class);

        return returnValue;
    }

    @GetMapping(path = "/{country}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public CountriesRest getCountryDetails(@PathVariable String country){
        CountriesRest returnValue = new CountriesRest();

        CountryDto countryDto = countryService.getCountryDetails(country);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(countryDto, CountriesRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{country}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public CountriesRest updateCountry(@PathVariable String country, @RequestBody CountryRequestModel countryDetails) {
        CountriesRest returnValue = new CountriesRest();

        CountryDto countryDto = new CountryDto();
        countryDto = new ModelMapper().map(countryDetails, CountryDto.class);

        CountryDto updatedCountryDetails = countryService.updateCountryDetails(country, countryDto);
        returnValue = new ModelMapper().map(updatedCountryDetails, CountriesRest.class);

        return returnValue;
    }

    @GetMapping(path = "/region/{region}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<CountryRest> getAllCountries(@PathVariable String region) {
        List<CountryRest> returnValue = new ArrayList<>();

        List<CountryDto> countriesDto = countryService.getAllCountriesFromRegion(region);

        if (countriesDto != null && !countriesDto.isEmpty()) {
            java.lang.reflect.Type listType = new TypeToken<List<CountryRest>>() {}.getType();
            returnValue = new ModelMapper().map(countriesDto, listType);
        }

        return returnValue;
    }

    @GetMapping(path = "/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<CountriesRest> getAllCountriesByRegion() {
        List<CountriesRest> returnValue = new ArrayList<>();

        List<CountryDto> countriesDto = countryService.getAllCountries();

        if (countriesDto != null && !countriesDto.isEmpty()) {
            java.lang.reflect.Type listType = new TypeToken<List<CountriesRest>>() {}.getType();
            returnValue = new ModelMapper().map(countriesDto, listType);
        }

        return returnValue;
    }
}
