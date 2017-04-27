package com.welcome.server.service;

import com.welcome.server.entity.City;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public interface CityService {

    City findOrCreatePlace(String country, String city);

    long getUserCount(String city);
}
