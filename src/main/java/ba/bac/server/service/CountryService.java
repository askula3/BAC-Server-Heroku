package ba.bac.server.service;

import ba.bac.server.io.entity.CountryEntity;
import ba.bac.server.shared.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<CountryDto> getAllCountries();
    CountryDto getCountryDetails(String country);
    CountryDto addNewCountry(CountryDto country);
    CountryDto updateCountryDetails(String country, CountryDto countryDetails);
    List<CountryDto> getAllCountriesFromRegion(String region);
}
