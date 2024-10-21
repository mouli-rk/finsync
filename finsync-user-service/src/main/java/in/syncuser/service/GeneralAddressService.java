package in.syncuser.service;

import java.util.List;

import in.syncuser.entity.City;
import in.syncuser.entity.Country;
import in.syncuser.entity.District;
import in.syncuser.entity.State;


public interface GeneralAddressService {
	
	
	
	State saveState(String stateName, String code, Country country);

	District saveDistrict(String districtName, String code, State state);

	City saveCity(String cityName, District district);

	List<State> saveAllStates(List<State> states);

	List<District> saveAllDistricts(List<District> districts);

	Country saveCountry(Country country);

	List<Country> saveAllCountries(List<Country> countries);

	List<City> saveAllCities(List<City> cities);

	List<City> getAllCities();

	List<District> getAllDistricts();

	List<State> getAllStates();

	List<Country> getAllCountries();

}
