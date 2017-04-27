package com.welcome.server.repository;

import com.welcome.server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by @mistreckless on 07.03.2017.!
 */
public interface PostRepository extends JpaRepository<Post,Long>, PostRepositoryCustom {
}
