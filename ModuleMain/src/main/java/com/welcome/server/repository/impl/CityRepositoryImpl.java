package com.welcome.server.repository.impl;

import com.welcome.server.entity.City;
import com.welcome.server.repository.CityRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
public class CityRepositoryImpl implements CityRepositoryCustom {
    @Autowired
    EntityManager em;

    @Override
    public boolean isCityExists(String name) {
        return selectCityByName(name).getResultList().size() == 1;
    }

    @Override
    public City getCity(String name) {
        return selectCityByName(name).getSingleResult();
    }

    private TypedQuery<City> selectCityByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> from = cq.from(City.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        cq.select(from).where(cb.equal(from.get("name"), p));
        TypedQuery<City> query = em.createQuery(cq);
        return query.setParameter(p, name);
    }
}
