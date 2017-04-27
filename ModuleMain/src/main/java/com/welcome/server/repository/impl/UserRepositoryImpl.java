package com.welcome.server.repository.impl;

import com.welcome.server.entity.User;
import com.welcome.server.repository.UserRepositoryCustom;
import com.welcome.server.util.Constance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by @mistreckless on 12.10.2016.!
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean checkUserNameExists(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> from = cq.from(User.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        cq.select(from).where(cb.equal(from.get("nickname"), p));

        TypedQuery<User> query = entityManager.createQuery(cq);
        query.setParameter(p, name);
        return query.getResultList().size() > 0;
    }

    @Override
    public boolean checkCredentials(String imei) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> from = cq.from(User.class);
        ParameterExpression<String> p1 = cb.parameter(String.class);
        ParameterExpression<Long> p2 = cb.parameter(Long.class);
        //Predicate predicate=cb.and(cb.equal(from.get("imei"),p1),cb.equal(from.get("id"),p2));
        Predicate debugPredicate = cb.equal(from.get("imei"), p1);
        cq.select(from).where(debugPredicate);
        TypedQuery<User> query = entityManager.createQuery(cq);
        query.setParameter(p1, imei);
        //query.setParameter(p2,id);
        query.setMaxResults(1);
        return !query.getResultList().isEmpty();
    }

    @Override
    public List<User> getPaginUsers(int index) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> from = cq.from(User.class);

        cq.select(from);
        TypedQuery<User> query=entityManager.createQuery(cq);
        query.setFirstResult(index);
        query.setMaxResults(Constance.USER_PAGIN_COUNT);
        return query.getResultList();
    }

    @Override
    public List<User> searchUser(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> from = cq.from(User.class);
        ParameterExpression<String> p=cb.parameter(String.class);
        Predicate predicate = cb.like(from.get("nickname"),p);

        cq.select(from).where(predicate);
        TypedQuery<User> query=entityManager.createQuery(cq);
        query.setParameter(p,name);
        return query.getResultList();
    }
}
