package com.welcome.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by @mistreckless on 06.03.2017.!
 */
@Entity
@Table(name = "city")
public class City implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city_name", unique = true,nullable = false)
    private String name;

    @JsonManagedReference
    @ManyToOne
    @JoinColumns(value = {
        @JoinColumn(name = "country_id",referencedColumnName = "id",nullable = false),
        @JoinColumn(name = "country_name",referencedColumnName = "name",nullable = false)
    })
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id")
    private List<User> users;

    public City(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
