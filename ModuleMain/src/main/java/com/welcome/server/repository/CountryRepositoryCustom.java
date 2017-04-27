package com.welcome.server.repository;

import com.welcome.server.entity.Country;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public interface CountryRepositoryCustom {

    boolean isCountryExists(String name);

    Country getCountryByName(String name);

}
