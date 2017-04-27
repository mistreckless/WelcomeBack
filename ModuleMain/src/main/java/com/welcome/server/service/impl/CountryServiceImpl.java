package com.welcome.server.service.impl;

import com.welcome.server.entity.Country;
import com.welcome.server.repository.CountryRepository;
import com.welcome.server.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country findOrCreateCountry(String name) {
        if (repository.isCountryExists(name))
            return repository.getCountryByName(name);
        else {
            Country country = new Country();
            country.setName(name);
            return repository.save(country);
        }
    }
}
