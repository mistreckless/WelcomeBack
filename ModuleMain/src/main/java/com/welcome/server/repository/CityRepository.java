package com.welcome.server.repository;

import com.welcome.server.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public interface CityRepository extends JpaRepository<City,Integer>, CityRepositoryCustom {
}
