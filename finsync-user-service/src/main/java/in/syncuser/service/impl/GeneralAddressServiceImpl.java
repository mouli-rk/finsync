package in.syncuser.service.impl;

import org.springframework.stereotype.Service;
import in.syncuser.entity.City;
import in.syncuser.entity.Country;
import in.syncuser.entity.District;
import in.syncuser.entity.State;
import in.syncuser.repository.CityRepository;
import in.syncuser.repository.CountryRepository;
import in.syncuser.repository.DistrictRepository;
import in.syncuser.repository.StateRepository;
import in.syncuser.service.GeneralAddressService;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@Service
public class GeneralAddressServiceImpl implements GeneralAddressService{

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
    
    @Override
    public List<Country> saveAllCountries(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }
    
    @Override
    public State saveState(String stateName, String code, Country country) {
        State state = new State(stateName, code, country);
        return stateRepository.save(state);
    }
    
    
    @Override
    public List<State> saveAllStates(List<State> states) {
        return stateRepository.saveAll(states);
    }
    
    @Override
    public District saveDistrict(String districtName, String code, State state) {
        District district = new District(districtName, code, state);
        return districtRepository.save(district);
    }
    
    @Override
    public List<District> saveAllDistricts(List<District> districts) {
        return districtRepository.saveAll(districts);
    }
    
    @Override
    public City saveCity(String cityName, District district) {
        City city = new City(cityName, district);
        return cityRepository.save(city);
    }
    @Override
    public List<City> saveAllCities(List<City> cities) {
        return cityRepository.saveAll(cities);
    }
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }
    @Override
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}

