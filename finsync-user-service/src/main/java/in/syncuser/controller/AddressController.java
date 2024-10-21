package in.syncuser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.syncuser.entity.City;
import in.syncuser.entity.Country;
import in.syncuser.entity.District;
import in.syncuser.entity.State;
import in.syncuser.service.GeneralAddressService;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

	private final GeneralAddressService generalAddressService;

	@PostMapping("/addCountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		Country savedCountry = generalAddressService.saveCountry(country);
		return ResponseEntity.ok(savedCountry);
	}
	
	@PostMapping("/addCountries")
	public ResponseEntity<List<Country>> addCountries(@RequestBody List<Country> countries) {
		List<Country> savedCountry = generalAddressService.saveAllCountries(countries);
		return ResponseEntity.ok(savedCountry);
	}

	@PostMapping("/addState")
	public ResponseEntity<State> addState(@RequestBody State state) {
		State savedState = generalAddressService.saveState(state.getStateName(), state.getCode(), state.getCountry());
		return ResponseEntity.ok(savedState);
	}
	
	@PostMapping("/addStates")
	public ResponseEntity<List<State>> addStates(@RequestBody List<State> states) {
	    List<State> savedStates = generalAddressService.saveAllStates(states);
	    return ResponseEntity.ok(savedStates);
	}
	
	@PostMapping("/addDistricts")
	public ResponseEntity<List<District>> addDistricts(@RequestBody List<District> districts) {
	    List<District> savedDistricts = generalAddressService.saveAllDistricts(districts);
	    return ResponseEntity.ok(savedDistricts);
	}

	@PostMapping("/addCities")
	public ResponseEntity<List<City>> addCities(@RequestBody List<City> cities) {
	    List<City> savedCities = generalAddressService.saveAllCities(cities);
	    return ResponseEntity.ok(savedCities);
	}

	@PostMapping("/addDistrict")
	public ResponseEntity<District> addDistrict(@RequestBody District district) {
		District savedDistrict = generalAddressService.saveDistrict(district.getDistrictName(), district.getCode(),
				district.getState());
		return ResponseEntity.ok(savedDistrict);
	}
	
	@PostMapping("/addCity")
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City savedCity = generalAddressService.saveCity(city.getCityName(), city.getDistrict());
        return ResponseEntity.ok(savedCity);
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = generalAddressService.getAllCities();
        return ResponseEntity.ok(cities);
    }

	@GetMapping("/getAllDistricts")
	public ResponseEntity<List<District>> getAllDistricts() {
		List<District> districts = generalAddressService.getAllDistricts();
		return ResponseEntity.ok(districts);
	}

	@GetMapping("/getAllStates")
	public ResponseEntity<List<State>> getAllStates() {
		List<State> states = generalAddressService.getAllStates();
		return ResponseEntity.ok(states);
	}

	@GetMapping("/getAllCountries")
	public ResponseEntity<List<Country>> getAllCountries() {
		List<Country> countries = generalAddressService.getAllCountries();
		return ResponseEntity.ok(countries);
	}
	
	

}
