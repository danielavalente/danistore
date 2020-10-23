package com.store.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.domain.City;
import com.store.domain.State;
import com.store.dto.CityDTO;
import com.store.dto.StateDTO;
import com.store.services.CityService;
import com.store.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResources {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<StateDTO> findAllOrdered () {
		List<State> states = stateService.findAllByOrderByName();
		List<StateDTO> statesDto = states.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return statesDto;
	}

	@RequestMapping(value = "/{state_id}/cities")
	public List<CityDTO> findAllCitiesByState(@PathVariable Integer state_id) {
		List<City> cities = cityService.findAllByState(stateService.findById(state_id));
		List<CityDTO> citiesDto = cities.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return citiesDto;
	}
}
