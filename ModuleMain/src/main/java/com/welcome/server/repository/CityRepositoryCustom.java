package com.welcome.server.repository;

import com.welcome.server.entity.City;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public interface CityRepositoryCustom {
    boolean isCityExists(String name);

    City getCity(String name);
}
