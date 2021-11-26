package ba.bac.server.service.implementation;

import ba.bac.server.io.entity.CountryEntity;
import ba.bac.server.io.entity.UserEntity;
import ba.bac.server.io.repository.CountryRepository;
import ba.bac.server.service.CountryService;
import ba.bac.server.shared.dto.CountryDto;
import ba.bac.server.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<CountryDto> getAllCountries() {
        List<CountryDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        Iterable<CountryEntity> countries = countryRepository.findAll();
        for (CountryEntity countryEntity:countries) {
            returnValue.add(modelMapper.map(countryEntity, CountryDto.class));
        }

        return returnValue;
    }

    @Override
    public CountryDto getCountryDetails(String country) {
        CountryEntity countryEntity = countryRepository.findByCountry(country);

        if (countryEntity == null)
            throw new RuntimeException("Country does not exist!");

        CountryDto returnValue = new CountryDto();
        BeanUtils.copyProperties(countryEntity, returnValue);

        return returnValue;
    }

    @Override
    public List<CountryDto> getAllCountriesFromRegion(String region) {
        List<CountryDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        Iterable<CountryEntity> countries = countryRepository.findAllByRegion(region);
        if (countries == null)
            throw new RuntimeException("Countries from wanted region not found!");

        for (CountryEntity countryEntity:countries) {
            returnValue.add(modelMapper.map(countryEntity, CountryDto.class));
        }

        return returnValue;
    }

    @Override
    public CountryDto addNewCountry(CountryDto country) {
        if (countryRepository.findByCountry(country.getCountry()) != null)
            throw new RuntimeException("Record already exists!");

        ModelMapper modelMapper = new ModelMapper();
        CountryEntity countryEntity = modelMapper.map(country, CountryEntity.class);

        countryEntity.setCountry(country.getCountry());
        countryEntity.setDisabled(country.isDisabled());

        CountryEntity storedCountryDetails = countryRepository.save(countryEntity);

        CountryDto returnValue  = modelMapper.map(storedCountryDetails, CountryDto.class);

        return returnValue;
    }

    @Override
    public CountryDto updateCountryDetails(String country, CountryDto countryDetails) {
        CountryDto returnValue = new CountryDto();

        CountryEntity countryEntity = countryRepository.findByCountry(country);
        if (countryEntity == null)
            throw new RuntimeException("Country not found!");

        countryEntity.setDisabled(countryDetails.isDisabled());
        countryEntity.setRegion(countryDetails.getRegion());

        CountryEntity updatedCountryDetails = countryRepository.save(countryEntity);
        returnValue = new ModelMapper().map(updatedCountryDetails, CountryDto.class);

        return returnValue;
    }
}
