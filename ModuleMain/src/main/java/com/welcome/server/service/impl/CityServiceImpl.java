package com.welcome.server.service.impl;

import com.welcome.server.bot.FirebaseManager;
import com.welcome.server.entity.City;
import com.welcome.server.entity.Country;
import com.welcome.server.entity.User;
import com.welcome.server.repository.CityRepository;
import com.welcome.server.service.CityService;
import com.welcome.server.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by @mistreckless on 06.03.2017.!
 */

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    private final CountryService countryService;

    private final FirebaseManager firebaseManager;

    @Autowired
    public CityServiceImpl(CityRepository repository, CountryService countryService, FirebaseManager firebaseManager) {
        this.repository = repository;
        this.countryService = countryService;
        this.firebaseManager = firebaseManager;
    }

    @Override
    public City findOrCreatePlace(String countryName, String cityName) {
        if (repository.isCityExists(cityName)) {
            firebaseManager.addNewPlace(countryName, cityName);//mock this
            return repository.getCity(cityName);
        } else {
            Country country = countryService.findOrCreateCountry(countryName);
            City city = new City();
            city.setName(cityName);
            city.setCountry(country);

            firebaseManager.addNewPlace(countryName, cityName);
            return repository.save(city);
        }
    }

    @Override
    public long getUserCount(String cityName) {
        List<User> users = repository.getCity(cityName).getUsers();
        return users == null ? 0 : users.size();
    }
}
