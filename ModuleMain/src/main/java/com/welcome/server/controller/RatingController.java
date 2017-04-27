package com.welcome.server.controller;

import com.welcome.server.entity.firebase.Rating;
import com.welcome.server.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by @mistreckless on 15.01.2017.!
 */
@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @RequestMapping(value = "/get{id}",method = RequestMethod.GET)
    @ResponseBody
    public Rating getRating(@PathVariable long id){
        return ratingService.getRating(id);
    }
}
