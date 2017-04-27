package com.welcome.server.service;

import com.welcome.server.entity.Country;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public interface CountryService {

    Country findOrCreateCountry(String name);
}
