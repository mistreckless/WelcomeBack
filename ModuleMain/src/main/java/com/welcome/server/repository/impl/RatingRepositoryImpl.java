package com.welcome.server.repository.impl;

import com.welcome.server.entity.Rating;
import com.welcome.server.repository.RatingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * Created by @mistreckless on 15.01.2017.!
 */
@Repository
public class RatingRepositoryImpl implements RatingRepositoryCustom {
    final
    EntityManager em;

    @Autowired
    public RatingRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Rating findRatingByUserId(long userId) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Rating> cq=cb.createQuery(Rating.class);
        Root<Rating> from=cq.from(Rating.class);
        ParameterExpression<Long> p=cb.parameter(Long.class);
        cq.select(from).where(cb.equal(from.get("user"),p));
        TypedQuery<Rating> query=em.createQuery(cq);
        query.setParameter(p,userId);
        return query.getSingleResult();
    }
}
