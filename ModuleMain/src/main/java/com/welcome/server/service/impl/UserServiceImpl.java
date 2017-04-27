package com.welcome.server.service.impl;

import com.google.firebase.internal.NonNull;
import com.welcome.server.entity.City;
import com.welcome.server.entity.Rating;
import com.welcome.server.entity.User;
import com.welcome.server.exception.InvalidNicknameException;
import com.welcome.server.helper.*;
import com.welcome.server.repository.UserRepository;
import com.welcome.server.service.CityService;
import com.welcome.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final CityService cityService;

    @Autowired
    public UserServiceImpl(UserRepository repository, CityService cityService) {
        this.repository = repository;
        this.cityService = cityService;
    }

    @Override
    @Transactional
    public RegistryResponse regNewUser(RegistryRequest registryRequest) throws NoSuchAlgorithmException {
        if (!registryRequest.getName().isEmpty()) {
            if (checkName(registryRequest.getName())) {
                User user = new User();
                user.setNickname(registryRequest.getName());
                user.setImei(new String(MessageDigest.getInstance("SHA-256").digest(registryRequest.getImei().getBytes(StandardCharsets.UTF_8))));
                user.setRating(new Rating(user, 0L, 0L, 0, 0,0,0));
                return generateRegistryResponse(repository.save(user));
            } else throw new InvalidNicknameException("Nickname already exists " + registryRequest.getName());
        }
        throw new InvalidNicknameException("Nickname is empty");
    }


    @Override
    @Transactional
    public AuthResponse authUser(@NonNull AuthRequest authRequest)
            throws Exception {
        if (checkCredentials(authRequest.getImei())) {
            String token = FirebaseHelper.createCustomToken(authRequest.getImei());
            User user = repository.findOne(authRequest.getId());
            user.setToken(token);
            user.setCity(cityService.findOrCreatePlace(authRequest.getCountry(), authRequest.getCity()));
            repository.saveAndFlush(user);
            return new AuthResponse(token);
        }
        throw new IllegalArgumentException("Imei or id are invalid");
    }

    @Override
    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest request) throws NoSuchAlgorithmException {
        if (request.getName() != null && request.getId() != 0 && checkCredentials(request.getImei())) {
            User user = repository.findOne(request.getId());
            if (user != null) {
                user.setEmail(request.getEmail());
                user.setNickname(request.getName());
                user.setPhotoRef(request.getPhotoRef());
                repository.saveAndFlush(user);
                return generateUpdateUserResponse(user);
            } else throw new IllegalArgumentException("This user doesn't exists");
        } else throw new IllegalArgumentException("nickname or id is empty");
    }

    @Override
    public List<UserResponse> getUsers(int index) {
        List<User> users = repository.getPaginUsers(index);
        return users.stream().map(this::generateUserResponse).collect(Collectors.toList());
    }

    @Override
    public boolean checkName(String name) {
        return !repository.checkUserNameExists(name) && !name.isEmpty();
    }

    @Override
    public User getUserById(long id) {
        return repository.findOne(id);
    }

    @Override
    public UserResponse getUserResponseById(long id) {
        return generateUserResponse(repository.findOne(id));
    }

    @Override
    public List<UserResponse> getUsers(String name) {
        List<User> users = repository.searchUser(name);
        return users.stream().map(this::generateUserResponse).collect(Collectors.toList());
    }

    private boolean checkCredentials(String imei) throws NoSuchAlgorithmException {
        if (imei != null) {
            String enImei = new String(MessageDigest.getInstance("SHA-256").digest(imei.getBytes(StandardCharsets.UTF_8)));
            return repository.checkCredentials(enImei);
        }
        return false;
    }

    private RegistryResponse generateRegistryResponse(User user) {
        RegistryResponse registryResponse = new RegistryResponse();
        registryResponse.setId(user.getId());
        registryResponse.setName(user.getNickname());
        registryResponse.setRating(generateResponseRating(user.getRating()));
        return registryResponse;
    }

    private UpdateUserResponse generateUpdateUserResponse(User user) {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setName(user.getNickname());
        response.setEmail(user.getEmail());
        response.setPhotoRef(user.getPhotoRef());
        return response;
    }

    private UserResponse generateUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        City city = user.getCity();
        userResponse.setId(user.getId());
        userResponse.setNickname(user.getNickname());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhotoRef(user.getPhotoRef());
        userResponse.setRating(generateResponseRating(user.getRating()));
        userResponse.setCity(city.getName());
        userResponse.setCountry(city.getCountry().getName());
        return userResponse;
    }

    private com.welcome.server.entity.firebase.Rating generateResponseRating(Rating rating) {
        return new com.welcome.server.entity.firebase.Rating(rating.getId(),
                rating.getLikeCount(), rating.getWillcomeCount(),
                rating.getPostCount(), rating.getVippostCount(),rating.getReportCount(),rating.getAdditionalPoints());
    }
}
