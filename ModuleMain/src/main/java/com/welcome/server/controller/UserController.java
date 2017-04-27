package com.welcome.server.controller;

import com.welcome.server.exception.InvalidNicknameException;
import com.welcome.server.helper.*;
import com.welcome.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by mistreckless on 12.10.2016.!
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    @ExceptionHandler(InvalidNicknameException.class)
    public RegistryResponse registration(@RequestBody RegistryRequest registryRequest) throws NoSuchAlgorithmException {
        return userService.regNewUser(registryRequest);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public AuthResponse auth(@RequestBody AuthRequest authRequest) throws Exception {
        return userService.authUser(authRequest);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public UpdateUserResponse update(@RequestBody UpdateUserRequest updateUserRequest) throws NoSuchAlgorithmException {
        return userService.updateUser(updateUserRequest);
    }

    @RequestMapping(value = "/getUsers{index}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserResponse> getUsers(@PathVariable int index) {
        return userService.getUsers(index);
    }

    @RequestMapping(value = "/getQueryUsers{name}",method = RequestMethod.GET)
    @ResponseBody
    public List<UserResponse> getUsers(@PathVariable String name){
        return userService.getUsers(name);
    }

    @RequestMapping(value = "/checkname{name}", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkName(@PathVariable String name) {
        return userService.checkName(name);
    }

    @RequestMapping(value = "/getUser{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserResponse getUser(@PathVariable long id) {
        return userService.getUserResponseById(id);
    }

    @RequestMapping(value = "/connection", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkConnection() {
        return true;
    }


    @ExceptionHandler(InvalidNicknameException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionJSONInfo handleInvalidNicknameEx(Exception ex) {
        return new ExceptionJSONInfo(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionJSONInfo handleIllegalArgumentException(Exception e) {
        return new ExceptionJSONInfo(e.getMessage());
    }
}
