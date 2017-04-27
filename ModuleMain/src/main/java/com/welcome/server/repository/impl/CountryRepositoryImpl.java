package com.welcome.server.repository.impl;

import com.welcome.server.entity.Country;
import com.welcome.server.repository.CountryRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
@Repository
public class CountryRepositoryImpl implements CountryRepositoryCustom {
    private final EntityManager em;

    @Autowired
    public CountryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean isCountryExists(String name) {
        return selectCountryByName(name).getResultList().size() == 1;
    }

    @Override
    public Country getCountryByName(String name) {
        return selectCountryByName(name).getSingleResult();
    }

    private TypedQuery<Country> selectCountryByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> from = cq.from(Country.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        cq.select(from).where(cb.equal(from.get("name"), p));
        TypedQuery<Country> query = em.createQuery(cq);
        return query.setParameter(p, name);
    }
}
