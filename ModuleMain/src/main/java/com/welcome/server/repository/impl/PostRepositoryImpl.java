package com.welcome.server.repository.impl;

import com.welcome.server.entity.Post;
import com.welcome.server.entity.User;
import com.welcome.server.repository.PostRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by @mistreckless on 14.04.2017.!
 */
@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final EntityManager em;

    @Autowired
    public PostRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Post> getAllUserPosts(User user) {
        CriteriaBuilder cb=em.getCriteriaBuilder();
        CriteriaQuery<Post> cq=cb.createQuery(Post.class);
        Root<Post> from=cq.from(Post.class);
        ParameterExpression<User> p=cb.parameter(User.class);
        cq.select(from).where(cb.equal(from.get("user"),p));
        TypedQuery<Post> query=em.createQuery(cq);
        query.setParameter(p,user);
        return query.getResultList();
    }
}
