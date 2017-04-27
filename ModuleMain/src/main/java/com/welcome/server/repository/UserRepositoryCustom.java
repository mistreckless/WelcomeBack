package com.welcome.server.repository;

import com.welcome.server.entity.User;

import java.util.List;

/**
 * Created by @mistreckless on 12.10.2016.!
 */
public interface UserRepositoryCustom {

    boolean checkUserNameExists(String name);

    boolean checkCredentials(String imei);

    List<User> getPaginUsers(int index);

    List<User> searchUser(String name);
}
