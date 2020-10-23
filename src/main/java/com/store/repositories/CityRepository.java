package com.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.domain.City;
import com.store.domain.State;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
	List<City> findAllByState(State state);
	
}
